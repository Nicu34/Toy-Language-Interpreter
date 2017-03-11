package interpretor.model.statement;

import interpretor.collection.list.IList;
import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.StatementExecutionException;
import interpretor.model.ProgramState;
import interpretor.model.expression.IExpression;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class PrintStatement implements IStatement {
    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    public IExpression getExpression() {
        return expression;
    }

    public void setExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "print( " + expression + " )";
    }

    public ProgramState execute(ProgramState state) throws StatementExecutionException{
        IList<Integer> outputList = state.getOutputList();

        try {
            outputList.add(expression.evaluate(state.getSymbolTable(), state.getHeapTable()));
        }
        catch (ExpressionException e) {
            throw new StatementExecutionException(e.getMessage());
        }
        state.setOutputList(outputList);

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrintStatement that = (PrintStatement) o;

        return expression != null ? expression.equals(that.expression) : that.expression == null;

    }

    @Override
    public int hashCode() {
        return expression != null ? expression.hashCode() : 0;
    }
}
