package interpretor.exceptions.runtime;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class NullPointerException extends RuntimeException {
    public NullPointerException() {
        super();
    }

    public NullPointerException(String message) {
        super(message);
    }
}
