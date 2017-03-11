package interpretor.model.statement;

import interpretor.collection.stack.IStack;
import interpretor.model.ProgramState;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class CompoundStatement implements IStatement {
    IStatement  statement1;
    IStatement  statement2;

    public CompoundStatement(IStatement statement1, IStatement statement2) {
        this.statement1 = statement1;
        this.statement2 = statement2;
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

    public ProgramState    execute(ProgramState state) {
        IStack<IStatement> stack = state.getExecutionStack();
        stack.push(statement2);
        stack.push(statement1);
        state.setExecutionStack(stack);

        return null;
    }

    @Override
    public String toString() {
        return "( " + statement1 + " ; " + statement2 + " )";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompoundStatement that = (CompoundStatement) o;

        if (statement1 != null ? !statement1.equals(that.statement1) : that.statement1 != null) return false;
        return statement2 != null ? statement2.equals(that.statement2) : that.statement2 == null;

    }

    @Override
    public int hashCode() {
        int result = statement1 != null ? statement1.hashCode() : 0;
        result = 31 * result + (statement2 != null ? statement2.hashCode() : 0);
        return result;
    }
}
