package interpretor.model.expression;

import interpretor.collection.dictionary.IDictionary;
import interpretor.collection.dictionary.Dictionary;
import interpretor.exceptions.model.expression.ExpressionException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class ArithmeticExpressionTest {

    @Test(expected = ExpressionException.class)
    public void test() throws ExpressionException{
        IDictionary<String, Integer> map = new Dictionary<>();

        assertEquals(new ArithmeticExpression(new NumberExpression(10), new NumberExpression(5), "+").evaluate(map, null), new Integer(15));
        assertEquals(new ArithmeticExpression(new NumberExpression(10), new NumberExpression(5), "/").evaluate(map, null), new Integer(2));
        assertEquals(new ArithmeticExpression(new NumberExpression(10), new NumberExpression(5), "*").evaluate(map, null), new Integer(50));
        assertEquals(new ArithmeticExpression(new NumberExpression(10), new NumberExpression(5), "-").evaluate(map, null), new Integer(5));
        new ArithmeticExpression(new NumberExpression(10), new NumberExpression(0), "/").evaluate(map, null);
    }
}