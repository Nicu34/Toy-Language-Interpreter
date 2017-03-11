package interpretor.exceptions.model.statement.io;

/**
 * Created by nicu on 11/20/2016.
 */
public class IOFileAlreadyExistsException extends IOStatementException {
    public IOFileAlreadyExistsException() {
        super();
    }

    public IOFileAlreadyExistsException(String s) {
        super(s);
    }
}
