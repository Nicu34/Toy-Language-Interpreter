package interpretor.exceptions.model.expression;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class DivisionByZeroException extends ExpressionException {
    public DivisionByZeroException() {
        super();
    }

    public DivisionByZeroException(String s) {
        super(s);
    }
}
