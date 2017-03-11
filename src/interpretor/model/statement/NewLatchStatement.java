package interpretor.model.statement;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.StatementException;
import interpretor.model.ProgramState;
import interpretor.model.expression.IExpression;
import interpretor.utils.FreeLocationLatchBuilder;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nicu on 1/26/2017.
 */
public class NewLatchStatement implements IStatement {
    private String variable;
    private IExpression expression;

    public NewLatchStatement(String variable, IExpression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public IExpression getExpression() {
        return expression;
    }

    public void setExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException {
        IDictionary<String, Integer> symbolTable = state.getSymbolTable();
        IDictionary heapTable = state.getHeapTable();
        IDictionary<Integer, Integer> latchTable = state.getLatchTable();
        ReentrantLock reentrantLock = new ReentrantLock();

        try {
            int expressionResult = expression.evaluate(symbolTable, heapTable);
            reentrantLock.lock();
            try {
                int freeAddress = FreeLocationLatchBuilder.getFreeAddress();
                latchTable.put(freeAddress, expressionResult);
                symbolTable.put(variable, freeAddress);
            }
            finally {
                reentrantLock.unlock();
            }
        }
        catch (ExpressionException e) {
            throw new StatementException(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewLatchStatement newLatchStatement = (NewLatchStatement) o;

        if (variable != null ? !variable.equals(newLatchStatement.variable) : newLatchStatement.variable != null) return false;
        return expression != null ? expression.equals(newLatchStatement.expression) : newLatchStatement.expression == null;

    }

    @Override
    public int hashCode() {
        int result = variable != null ? variable.hashCode() : 0;
        result = 31 * result + (expression != null ? expression.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "newLatch (" + variable + ", " + expression + ")";
    }
}
