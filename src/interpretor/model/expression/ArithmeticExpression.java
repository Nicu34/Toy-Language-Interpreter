package interpretor.model.expression;
import interpretor.exceptions.model.expression.DivisionByZeroException;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.model.expression.ExpressionException;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class ArithmeticExpression implements IExpression {
    private IExpression exp1;

    private IExpression exp2;

    private String operation;

    public ArithmeticExpression(IExpression exp1, IExpression exp2, String operation) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operation = operation;
    }

    public Integer evaluate(IDictionary<String, Integer> table, IDictionary<Integer, Integer> heapTable) throws ExpressionException {
        if (operation.equals("/") && exp2.evaluate(table, heapTable) == 0)
            throw new DivisionByZeroException("Division by zero exception occurred at " + this + " expression.");
        if (operation.equals("+"))
            return exp1.evaluate(table, heapTable) + exp2.evaluate(table, heapTable);
        else if (operation.equals("-"))
            return exp1.evaluate(table, heapTable) - exp2.evaluate(table, heapTable);
        else if (operation.equals("/"))
            return exp1.evaluate(table, heapTable) / exp2.evaluate(table, heapTable);
        else if (operation.equals("*"))
            return exp1.evaluate(table, heapTable) * exp2.evaluate(table, heapTable);
        return exp1.evaluate(table, heapTable) % exp2.evaluate(table, heapTable);
    }

    public IExpression getExp1() {
        return exp1;
    }

    public void setExp1(IExpression exp1) {
        this.exp1 = exp1;
    }

    public IExpression getExp2() {
        return exp2;
    }

    public void setExp2(IExpression exp2) {
        this.exp2 = exp2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return exp1 + operation + exp2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArithmeticExpression that = (ArithmeticExpression) o;

        if (exp1 != null ? !exp1.equals(that.exp1) : that.exp1 != null) return false;
        if (exp2 != null ? !exp2.equals(that.exp2) : that.exp2 != null) return false;
        return operation != null ? operation.equals(that.operation) : that.operation == null;

    }

    @Override
    public int hashCode() {
        int result = exp1 != null ? exp1.hashCode() : 0;
        result = 31 * result + (exp2 != null ? exp2.hashCode() : 0);
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        return result;
    }
}
