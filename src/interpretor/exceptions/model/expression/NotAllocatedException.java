package interpretor.exceptions.model.expression;

/**
 * Created by nicu on 12/3/2016.
 */
public class NotAllocatedException extends ExpressionException {
    public NotAllocatedException() {
        super();
    }

    public NotAllocatedException(String s) {
        super(s);
    }
}
