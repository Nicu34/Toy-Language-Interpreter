package interpretor.exceptions.model.expression;

import com.sun.org.apache.xpath.internal.operations.Mod;
import interpretor.exceptions.model.ModelException;
import interpretor.exceptions.model.statement.StatementException;

/**
 * Created by nicu on 11/20/2016.
 */
public class ExpressionException extends ModelException{
    public ExpressionException() {
        super();
    }

    public ExpressionException(String s) {
        super(s);
    }
}
