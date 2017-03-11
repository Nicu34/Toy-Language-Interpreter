package interpretor.model.expression;

import interpretor.exceptions.model.expression.ComparisonException;
import interpretor.exceptions.model.expression.ExpressionException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by MuresanN on 12/6/2016.
 */
public class BooleanExpressionTest {

    @Test
    public void test() throws ExpressionException{
        BooleanExpression booleanExpression1 = new BooleanExpression(new NumberExpression(10), new NumberExpression(2), ">");
        BooleanExpression booleanExpression2= new BooleanExpression(new NumberExpression(10), new NumberExpression(2), ">=");
        BooleanExpression booleanExpression3 = new BooleanExpression(new NumberExpression(10), new NumberExpression(2), "<");
        BooleanExpression booleanExpression4 = new BooleanExpression(new NumberExpression(10), new NumberExpression(2), "<=");
        BooleanExpression booleanExpression5 = new BooleanExpression(new NumberExpression(10), new NumberExpression(2), "==");
        BooleanExpression booleanExpression6 = new BooleanExpression(new NumberExpression(10), new NumberExpression(2), "!=");
        
        assertEquals(((Integer) 1), booleanExpression1.evaluate(null, null));
        assertEquals(((Integer) 1), booleanExpression2.evaluate(null, null));
        assertEquals(((Integer) 0), booleanExpression3.evaluate(null, null));
        assertEquals(((Integer) 0), booleanExpression4.evaluate(null, null));
        assertEquals(((Integer) 0), booleanExpression5.evaluate(null, null));
        assertEquals(((Integer) 1), booleanExpression6.evaluate(null, null));
    }

    @Test(expected = ComparisonException.class)
    public void test_ComparisonException() throws ExpressionException{
       new BooleanExpression(new NumberExpression(10),
                new NumberExpression(2), "invalid").evaluate(null, null);
    }

}