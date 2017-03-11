package interpretor.exceptions.runtime;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class IndexOutOfBoundsException extends RuntimeException {
    public IndexOutOfBoundsException() {
        super();
    }

    public IndexOutOfBoundsException(String message) {
        super(message);
    }
}
