package interpretor.exceptions.model.statement.io;

import interpretor.exceptions.model.ModelException;
import interpretor.exceptions.model.statement.StatementException;

/**
 * Created by nicu on 11/20/2016.
 */
public class IOStatementException extends StatementException{
    public IOStatementException() {
        super();
    }

    public IOStatementException(String s) {
        super(s);
    }
}
