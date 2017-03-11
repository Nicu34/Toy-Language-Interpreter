package interpretor.exceptions.repository;

/**
 * Created by nicu on 12/8/2016.
 */
public class RepositoryClassNotFoundException extends RepositoryException {
    public RepositoryClassNotFoundException() {
        super();
    }

    public RepositoryClassNotFoundException(String s) {
        super(s);
    }
}
