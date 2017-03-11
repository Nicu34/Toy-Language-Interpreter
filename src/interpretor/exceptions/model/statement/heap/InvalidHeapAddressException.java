package interpretor.exceptions.model.statement.heap;

/**
 * Created by nicu on 12/3/2016.
 */
public class InvalidHeapAddressException extends HeapStatementException {
    public InvalidHeapAddressException() {
        super();
    }

    public InvalidHeapAddressException(String s) {
        super(s);
    }
}
