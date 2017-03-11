package interpretor.model.expression;

import interpretor.collection.dictionary.IDictionary;
import interpretor.collection.dictionary.Dictionary;
import interpretor.exceptions.runtime.ClassCastException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class NumberExpressionTest {
    @Test(expected = ClassCastException.class)
    public void     test() {
        IDictionary<String, Integer> map = new Dictionary<>();

        assertEquals(new NumberExpression(10).evaluate(map, null), new Integer(10));
        assertEquals(new NumberExpression(0).evaluate(map, null), new Integer(0));
        assertEquals(new NumberExpression(-20).evaluate(map, null), new Integer(-20));
        new NumberExpression("I hope it cracks with class cast exception.");
    }
}