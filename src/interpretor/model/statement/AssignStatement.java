package interpretor.model.statement;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.StatementExecutionException;
import interpretor.exceptions.runtime.ClassCastException;
import interpretor.model.ProgramState;
import interpretor.model.expression.IExpression;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class AssignStatement implements IStatement {
    String  variable;
    IExpression expression;

    public AssignStatement(Object variable, IExpression expression) throws ClassCastException {
        if (! (variable instanceof String ))
            throw new ClassCastException("Invalid assign statement. " + variable + " is not a String.");
        this.variable = (String)variable;
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
    public String  toString() {
        return variable + " = " + expression;
    }

    public ProgramState    execute(ProgramState state) throws StatementExecutionException {
        IDictionary<String, Integer> symbolTable = state.getSymbolTable();
        IDictionary<Integer, Integer> heap = state.getHeapTable();

        try {
            Integer value = expression.evaluate(symbolTable, heap);
            symbolTable.put(variable, value);
        }
        catch (ExpressionException e) {
            throw new StatementExecutionException(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignStatement that = (AssignStatement) o;

        if (variable != null ? !variable.equals(that.variable) : that.variable != null) return false;
        return expression != null ? expression.equals(that.expression) : that.expression == null;

    }

    @Override
    public int hashCode() {
        int result = variable != null ? variable.hashCode() : 0;
        result = 31 * result + (expression != null ? expression.hashCode() : 0);
        return result;
    }
}
