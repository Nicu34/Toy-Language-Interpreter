package interpretor.model.statement;

import interpretor.collection.stack.IStack;
import interpretor.exceptions.model.statement.StatementException;
import interpretor.model.ProgramState;
import interpretor.model.expression.IExpression;

/**
 * Created by nicu on 1/26/2017.
 */
public class RepeatStatement implements IStatement {
    private IStatement statement;
    private IExpression expression;

    public RepeatStatement(IStatement statement, IExpression expression) {
        this.statement = statement;
        this.expression = expression;
    }

    public IStatement getStatement() {
        return statement;
    }

    public void setStatement(IStatement statement) {
        this.statement = statement;
    }

    public IExpression getExpression() {
        return expression;
    }

    public void setExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {
        IStack<IStatement> stack = programState.getExecutionStack();

        stack.push(new WhileStatement(expression, statement, true));
        stack.push(statement);
        programState.setExecutionStack(stack);

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepeatStatement that = (RepeatStatement) o;

        if (statement != null ? !statement.equals(that.statement) : that.statement != null) return false;
        return expression != null ? expression.equals(that.expression) : that.expression == null;

    }

    @Override
    public int hashCode() {
        int result = statement != null ? statement.hashCode() : 0;
        result = 31 * result + (expression != null ? expression.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "repeat " + this.statement + " until " + this.expression;
    }
}
