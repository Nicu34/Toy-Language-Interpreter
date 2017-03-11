package interpretor.model.statement;

import interpretor.collection.dictionary.IDictionary;
import interpretor.collection.list.IList;
import interpretor.collection.list.List;
import interpretor.exceptions.model.statement.StatementException;
import interpretor.model.ProgramState;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nicu on 1/26/2017.
 */
public class CountdownStatement implements IStatement {
    private String variable;

    public CountdownStatement(String variable) {
        this.variable = variable;
    }

    public String getVar() {
        return variable;
    }

    public void setVar(String var) {
        this.variable = var;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountdownStatement that = (CountdownStatement) o;

        return variable != null ? variable.equals(that.variable) : that.variable == null;

    }

    @Override
    public int hashCode() {
        return variable != null ? variable.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "countdown (" + variable + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException {
        IDictionary<String, Integer> symbolTable = state.getSymbolTable();
        IDictionary<Integer, Integer> latchTable = state.getLatchTable();
        IList<Integer> outList = state.getOutputList();
        ReentrantLock reentrantLock = new ReentrantLock();

        if (!symbolTable.containsKey(variable)) {
            throw new StatementException("Await Statement Exception. No variable called " + variable + " foudn in symbol talbe");
        }
        else {
            Integer foundIndexSymbolTable = symbolTable.get(variable);

            reentrantLock.lock();
            try {
                if (latchTable.containsKey(foundIndexSymbolTable)) {
                    Integer foundIndexLatchTable = latchTable.get(foundIndexSymbolTable);

                    if (foundIndexLatchTable > 0) {
                        latchTable.put(foundIndexSymbolTable, foundIndexLatchTable - 1);
                        outList.add(state.getId());
                    }
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        state.setOutputList(outList);
        state.setLatchTable(latchTable);

        return null;
    }
}
