package interpretor.exceptions.repository;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class RepositoryProgramStateException extends RepositoryException{
    public RepositoryProgramStateException(String s) {
        super(s);
    }

    public RepositoryProgramStateException() {
        super();
    }
}
