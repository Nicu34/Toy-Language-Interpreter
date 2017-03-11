package interpretor.repository;

import interpretor.exceptions.repository.RepositoryClassNotFoundException;
import interpretor.exceptions.repository.RepositoryIOException;
import interpretor.exceptions.repository.RepositoryProgramStateException;
import interpretor.exceptions.runtime.IndexOutOfBoundsException;
import interpretor.model.ProgramState;
import interpretor.model.statement.IStatement;
import interpretor.utils.FileUtils;
import interpretor.utils.SerializationUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class ProgramStateRepository implements IProgramStateRepository {
    private List<ProgramState> programStateList;
    private int programIndex;
    private String filePath;

    public ProgramStateRepository() {
        programStateList = new ArrayList<>();
    }

    public ProgramStateRepository(String filePath) {
        this();
        this.filePath = filePath;
    }

    @Override
    public void setMainProgramStatement(IStatement programStatement) {
        if (programStateList.size() != 0) {
            programStateList.clear();
        }
        programStateList.add(new ProgramState(programStatement));
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void logProgramState(ProgramState programState) throws RepositoryIOException, RepositoryProgramStateException {
        PrintWriter logFileWriter;

        try {
            logFileWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
        } catch (IOException ex) {
            throw new RepositoryIOException(ex.toString());
        }
        logFileWriter.write(programState.toString());
        logFileWriter.close();
    }

    public ProgramState getCurrentProgramState() throws RepositoryProgramStateException {
        ProgramState ret;

        try {
            ret = programStateList.get(programIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new RepositoryProgramStateException("No program state defined.");
        }
        return ret;
    }

    @Override
    public void serializeMainProgramState() throws RepositoryIOException, RepositoryProgramStateException {
        try {
            ProgramState programState = getCurrentProgramState();
            SerializationUtil.serialize(programState, FileUtils.replaceFileExtension(filePath, "ser"));
        }
        catch (FileNotFoundException e) {
            throw new RepositoryIOException("Cannot find file to serialize" + filePath);
        }
        catch (IOException e) {
            throw new RepositoryIOException(e.getStackTrace().toString());
        }
    }

    @Override
    public void deserializeMainProgramState() throws RepositoryIOException, RepositoryProgramStateException, RepositoryClassNotFoundException {
        try {
            ProgramState programState = ((ProgramState) SerializationUtil.deserialize(FileUtils.replaceFileExtension(filePath, "ser")));

            this.setMainProgramStatement(programState.getOriginalProgram());
        }
        catch (FileNotFoundException e) {
            throw new RepositoryIOException("Cannot find file to deserialize" + filePath);
        }
        catch (IOException e) {
            throw new RepositoryIOException(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            throw new RepositoryClassNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<ProgramState> getProgramStateList() {
        return this.programStateList;
    }

    @Override
    public void setProgramStateList(List<ProgramState> programStateList) {
        this.programStateList = programStateList;
    }
}
