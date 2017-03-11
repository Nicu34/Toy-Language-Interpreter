package interpretor.model.statement;

import interpretor.collection.dictionary.IDictionary;
import interpretor.collection.stack.IStack;
import interpretor.exceptions.model.statement.StatementException;
import interpretor.model.ProgramState;

/**
 * Created by nicu on 1/26/2017.
 */
public class AwaitStatement implements IStatement {
    private String variable;

    public AwaitStatement(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AwaitStatement that = (AwaitStatement) o;

        return variable != null ? variable.equals(that.variable) : that.variable == null;

    }

    @Override
    public int hashCode() {
        return variable != null ? variable.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "await (" + variable + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException {
        IDictionary<String, Integer> symbolTable = state.getSymbolTable();
        IDictionary<Integer, Integer> latchTable = state.getLatchTable();
        IStack<IStatement> stack = state.getExecutionStack();

        if (!symbolTable.containsKey(variable)) {
            throw new StatementException("Await Statement Exception. No variable called " + variable + " foudn in symbol talbe");
        }
        else {
            Integer foundIndexSymbolTable = symbolTable.get(variable);

            if (!latchTable.containsKey(foundIndexSymbolTable)) {
                throw new StatementException("Await Statement Exception. No variable called " + variable + " foudn in latch talbe");
            }
            else {
                Integer foundIndexLatchTable = latchTable.get(foundIndexSymbolTable);

                if (!foundIndexLatchTable.equals(0)) {
                    stack.push(this);
                }
            }
        }

        state.setExecutionStack(stack);
        return null;
    }
}
