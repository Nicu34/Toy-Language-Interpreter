package interpretor.model.expression;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.model.expression.ExpressionException;

import java.io.Serializable;

/**
 * Created by MuresanN on 11/20/2016.
 */
public interface IExpression extends Serializable {
    Integer     evaluate(IDictionary<String, Integer> symbolTable, IDictionary<Integer, Integer> heapTable) throws ExpressionException;
}
