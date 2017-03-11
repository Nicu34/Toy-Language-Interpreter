package interpretor.exceptions.repository;

import interpretor.exceptions.controller.ControllerException;

/**
 * Created by nicu on 11.11.2016.
 */
public class RepositoryIOException extends RepositoryException{
    public RepositoryIOException() {
        super();
    }

    public RepositoryIOException(String s) {
        super(s);
    }
}
