package interpretor.exceptions.repository;

import interpretor.exceptions.controller.ControllerException;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class RepositoryExecutionException extends RepositoryException{
    public RepositoryExecutionException(String s) {
        super(s);
    }

    public RepositoryExecutionException() {
        super();
    }
}
