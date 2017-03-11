package interpretor.repository;

import interpretor.collection.list.IList;
import interpretor.exceptions.repository.RepositoryClassNotFoundException;
import interpretor.exceptions.repository.RepositoryIOException;
import interpretor.exceptions.repository.RepositoryProgramStateException;
import interpretor.model.ProgramState;
import interpretor.model.statement.IStatement;

import java.util.List;

/**
 * Created by MuresanN on 11/20/2016.
 */
public interface IProgramStateRepository {
    ProgramState getCurrentProgramState() throws RepositoryProgramStateException;

    void setMainProgramStatement(IStatement programStatement);

    void logProgramState(ProgramState programState) throws RepositoryIOException, RepositoryProgramStateException;

    String getFilePath();

    void setFilePath(String filePath);

    void serializeMainProgramState() throws RepositoryIOException, RepositoryProgramStateException;

    void deserializeMainProgramState() throws RepositoryIOException, RepositoryProgramStateException, RepositoryClassNotFoundException;

    List<ProgramState> getProgramStateList();

    void setProgramStateList(List<ProgramState> list);
}
