package interpretor.exceptions.model.expression;

/**
 * Created by nicu on 11/20/2016.
 */
public class VariableNotFoundException extends ExpressionException {
    public VariableNotFoundException() {
        super();
    }

    public VariableNotFoundException(String s) {
        super(s);
    }
}
