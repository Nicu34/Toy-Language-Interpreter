package interpretor.model.statement;

import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.StatementException;
import interpretor.exceptions.model.statement.StatementExecutionException;
import interpretor.model.ProgramState;
import interpretor.model.expression.IExpression;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class IfStatement implements IStatement {
    IExpression expression;
    IStatement statement1;
    IStatement statement2;

    public IfStatement(IExpression expression, IStatement statement1, IStatement statement2) {
        this.expression = expression;
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    public IExpression getExpression() {
        return expression;
    }

    public void setExpression(IExpression expression) {
        this.expression = expression;
    }

    public IStatement getStatement1() {
        return statement1;
    }

    public void setStatement1(IStatement statement1) {
        this.statement1 = statement1;
    }

    public IStatement getStatement2() {
        return statement2;
    }

    public void setStatement2(IStatement statement2) {
        this.statement2 = statement2;
    }

    @Override
    public String toString() {
        return "IF (" + expression + ") THEN (" + statement1 + ") ELSE (" + statement2 + ")";
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        try {
            if (expression.evaluate(state.getSymbolTable(), state.getHeapTable()) == 1)
                state = statement1.execute(state);
            else
                state = statement2.execute(state);
        }
        catch (ExpressionException e) {
            throw new StatementExecutionException(e.getMessage());
        }
        catch (StatementException e) {
            throw new StatementException(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IfStatement that = (IfStatement) o;

        if (expression != null ? !expression.equals(that.expression) : that.expression != null) return false;
        if (statement1 != null ? !statement1.equals(that.statement1) : that.statement1 != null) return false;
        return statement2 != null ? statement2.equals(that.statement2) : that.statement2 == null;

    }

    @Override
    public int hashCode() {
        int result = expression != null ? expression.hashCode() : 0;
        result = 31 * result + (statement1 != null ? statement1.hashCode() : 0);
        result = 31 * result + (statement2 != null ? statement2.hashCode() : 0);
        return result;
    }
}
