package interpretor.model.expression;

import interpretor.collection.dictionary.IDictionary;
import interpretor.collection.dictionary.Dictionary;
import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.expression.NotAllocatedException;
import interpretor.exceptions.model.expression.VariableNotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 12/3/2016.
 */
public class ReadHeapExpressionTest {
    private IDictionary<Integer, Integer> heapTable = new Dictionary<>();

    private IDictionary<String, Integer> symbolTable = new Dictionary<>();

    @Test
    public void test() throws ExpressionException {
        IExpression readHeapExpression = new ReadHeapExpression("heap");

        heapTable.put(1, 34);
        symbolTable.put("heap", 1);

        assertEquals(((Integer) 34), readHeapExpression.evaluate(symbolTable, heapTable));
    }

    @Test(expected = VariableNotFoundException.class)
    public void test_variableNotFoundEx() throws ExpressionException{
        new ReadHeapExpression("heap nu exista").evaluate(symbolTable, heapTable);
    }

    @Test(expected = NotAllocatedException.class)
    public void test_notAllocatedEx() throws ExpressionException{
        symbolTable.put("seg fault", 33);
        new ReadHeapExpression("seg fault").evaluate(symbolTable, heapTable);
    }

}