package interpretor.controller;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.controller.ControllerExecutionException;
import interpretor.exceptions.controller.ControllerIOException;
import interpretor.exceptions.controller.ControllerProgramStateException;
import interpretor.exceptions.repository.RepositoryClassNotFoundException;
import interpretor.exceptions.repository.RepositoryIOException;
import interpretor.exceptions.repository.RepositoryProgramStateException;
import interpretor.exceptions.runtime.ProgramStateControllerRuntimeException;
import interpretor.model.ProgramState;
import interpretor.model.statement.IStatement;
import interpretor.repository.IProgramStateRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class ProgramStateController {
    private IProgramStateRepository programStateRepository;
    private String logFileContent = "";
    private ExecutorService executorService;

    public ProgramStateController(IProgramStateRepository programStateRepository) {
        this.programStateRepository = programStateRepository;
    }

    @SuppressWarnings("unchecked")
    private Set conservativeGarbageCollector(Collection<Integer> symTableValues,
                                             IDictionary<Integer, Integer> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableValues.contains(e.getKey()))
                .collect(Collectors.toSet());
    }

//    public ProgramState oneStepEvaluation(ProgramState state) throws ControllerExecutionException {
//        IStack<IStatement> stack = state.getExecutionStack();
//        IStatement currentStatement;
//        ProgramState programState;
//
//        try {
//            currentStatement = stack.pop();
//            programState = currentStatement.execute(state);
//        } catch (StatementException e) {
//            throw new ControllerExecutionException(e.getMessage());
//        }
//        return programState;
//    }

//    public void allStepEvaluation() throws ControllerIOException, ControllerProgramStateException, ControllerExecutionException {
//        ProgramState program;
//
//        try {
//            program = programStateRepository.getCurrentProgramState();
//        } catch (RepositoryProgramStateException e) {
//            throw new ControllerProgramStateException("No program state defined. Cannot evaluate.");
//        }
//        while (!program.getExecutionStack().isEmpty()) {
////            oneStepEvaluation(program);
//            program.getHeapTable().setContent(conservativeGarbageCollector(program.getSymbolTable().values(), program.getHeapTable()));
//            try {
//                setCurrentProgramState();
//                if (programStateRepository.getFilePath() != null)
//                    programStateRepository.logProgramState(programStateRepository.getCurrentProgramState());
//            } catch (RepositoryIOException e) {
//                throw new ControllerIOException(e.getMessage());
//            } catch (RepositoryProgramStateException e) {
//                throw new ControllerProgramStateException(e.getMessage());
//            }
//        }
//    }

//    public void allStep() throws ControllerExecutionException {
//        try {
//            ProgramState programState = programStateRepository.getCurrentProgramState();
//            programState.oneStep();
//        }
//        catch (RepositoryProgramStateException e) {
//            throw new ControllerExecutionException(e.getMessage());
//        }
//        catch (StatementException e) {
//            throw new ControllerExecutionException(e.getMessage());
//        }
//    }

    public void allStep() throws ControllerExecutionException {
        executorService = Executors.newFixedThreadPool(2);
        while (true) {
            List<ProgramState> programStateList = removeCompletedPrograms(programStateRepository.getProgramStateList());

            if (programStateList.size() == 0) {
                break;
            }
            try {
                oneStepForAllProgram(programStateList);

            } catch (InterruptedException e) {
                throw new ControllerExecutionException("Thread exception: " + e.getMessage());
            } catch (ProgramStateControllerRuntimeException e) {
                throw new ControllerExecutionException(e.getMessage());
            }
        }
        executorService.shutdown();
    }

    public void allStepGUI() throws ControllerExecutionException {
        executorService = Executors.newFixedThreadPool(2);
        List<ProgramState> programStateList = removeCompletedPrograms(programStateRepository.getProgramStateList());

        if (programStateList.size() == 0) {
            throw new ControllerExecutionException("List of program states is empty.");
        }
        try {
            oneStepForAllProgram(programStateList);
        } catch (InterruptedException e) {
            throw new ControllerExecutionException("Thread exception: " + e.getMessage());
        } catch (ProgramStateControllerRuntimeException e) {
            throw new ControllerExecutionException(e.getMessage());
        }
        executorService.shutdown();
    }

    public int getProgramStatesNumber() {
        return removeCompletedPrograms(programStateRepository.getProgramStateList()).size();
    }

    private void safeLogProgramState(ProgramState programState) throws ProgramStateControllerRuntimeException {
        try {
            if (programStateRepository.getFilePath() != null) {
                this.logFileContent += programState + "\n";
                programStateRepository.logProgramState(programState);
            }
        } catch (RepositoryIOException e) {
            throw new ProgramStateControllerRuntimeException(e.getMessage());
        } catch (RepositoryProgramStateException e) {
            throw new ProgramStateControllerRuntimeException(e.getMessage());
        }
    }

    public void oneStepForAllProgram(List<ProgramState> programList) throws ProgramStateControllerRuntimeException,
            InterruptedException {
        programList.forEach(program -> this.safeLogProgramState(program));
        List<Callable<ProgramState>> callableList = programList.stream()
                .map((ProgramState programState) -> (Callable<ProgramState>) (() ->
                        programState.oneStep())).collect(Collectors.toList());
        List<ProgramState> newProgramList = executorService.invokeAll(callableList).stream()
                .map(programStateFuture -> {
                    try {
                        return programStateFuture.get();
                    } catch (ExecutionException e) {
                        throw new ProgramStateControllerRuntimeException("Thread exception: " + e.getMessage());
                    } catch (InterruptedException e) {
                        throw new ProgramStateControllerRuntimeException("Thread exception: " + e.getMessage());
                    }
                })
                .filter(programState -> programState != null)
                .collect(Collectors.toList());
        programList.addAll(newProgramList);
        programList.forEach(programState -> safeLogProgramState(programState));
        programStateRepository.setProgramStateList(programList);
    }

    public void addProgramStatement(IStatement statement) {
        programStateRepository.setMainProgramStatement(statement);
    }

    private void setCurrentProgramState() throws RepositoryProgramStateException {
        ProgramState programState = programStateRepository.getCurrentProgramState();
        logFileContent = programState.toString();
    }

    public void serializeCurrentProgramState() throws ControllerIOException, ControllerProgramStateException {
        try {
            programStateRepository.serializeMainProgramState();
        } catch (RepositoryIOException e) {
            throw new ControllerIOException(e.getMessage());
        } catch (RepositoryProgramStateException e) {
            throw new ControllerProgramStateException(e.getMessage());
        }
    }

    public void deserializeProgramState() throws ControllerProgramStateException, ControllerIOException, ControllerExecutionException {
        try {
            programStateRepository.deserializeMainProgramState();
        } catch (RepositoryIOException e) {
            throw new ControllerIOException(e.getMessage());
        } catch (RepositoryProgramStateException e) {
            throw new ControllerProgramStateException(e.getMessage());
        } catch (RepositoryClassNotFoundException e) {
            throw new ControllerExecutionException(e.getMessage());
        }
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programStateList) {
        return programStateList
                .stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public ProgramState getCurrentProgramState() {
        try {
            return programStateRepository.getCurrentProgramState();
        } catch (RepositoryProgramStateException e) {
            return null;
        }
    }

    public String getLogFileContent() {
        return logFileContent;
    }

    public List<ProgramState> getProgramStateList() {
        return programStateRepository.getProgramStateList();
    }
}
