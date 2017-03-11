package interpretor.model.expression;

import interpretor.collection.dictionary.IDictionary;
import interpretor.collection.dictionary.Dictionary;
import interpretor.exceptions.model.expression.VariableNotFoundException;
import interpretor.exceptions.runtime.ClassCastException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class VariableExpressionTest {

    @Test
    public void test() throws VariableNotFoundException {
        IDictionary<String, Integer> map = new Dictionary<>();

        map.put("positive", 10);
        map.put("negative", -10);
        map.put("zero", 0);

        assertEquals(new VariableExpression("positive").evaluate(map, null), new Integer(10));
        assertEquals(new VariableExpression("negative").evaluate(map, null), new Integer(-10));
        assertEquals(new VariableExpression("zero").evaluate(map, null), new Integer(0));
    }

    @Test(expected = VariableNotFoundException.class)
    public void testVarNotFoundEx() throws VariableNotFoundException{
        new VariableExpression("not found").evaluate(new Dictionary<>(), null);
    }

    @Test(expected = ClassCastException.class)
    public void testClassCastEx() {
        new VariableExpression(10);
    }

}