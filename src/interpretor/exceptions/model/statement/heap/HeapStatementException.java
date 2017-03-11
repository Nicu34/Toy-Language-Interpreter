package interpretor.exceptions.model.statement.heap;

import interpretor.exceptions.model.statement.StatementException;
import interpretor.model.statement.heap.HeapStatement;
import interpretor.utils.HeapAddressBuilder;

/**
 * Created by nicu on 12/3/2016.
 */
public class HeapStatementException extends StatementException {
    public HeapStatementException() {
        super();
    }

    public HeapStatementException(String s) {
        super(s);
    }
}
