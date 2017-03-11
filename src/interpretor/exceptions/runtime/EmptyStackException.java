package interpretor.exceptions.runtime;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class EmptyStackException extends RuntimeException {
    public EmptyStackException () {
        super();
    }

    public EmptyStackException (String message) {
        super(message);
    }
}
