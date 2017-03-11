package interpretor.exceptions.runtime;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class ClassCastException extends RuntimeException {
    public ClassCastException() {
        super();
    }

    public ClassCastException(String message) {
        super(message);
    }
}
