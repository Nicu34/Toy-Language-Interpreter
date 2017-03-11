package interpretor.model.statement;

import interpretor.collection.dictionary.IDictionary;
import interpretor.collection.stack.IStack;
import interpretor.model.ProgramState;
import interpretor.utils.ProgramStateIdBuilder;

/**
 * Created by MuresanN on 12/14/2016.
 */
public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    public IStatement getStatement() {
        return statement;
    }

    public ForkStatement setStatement(IStatement statement) {
        this.statement = statement;
        return this;
    }

    public ProgramState execute(ProgramState programState) {
        ProgramState forkProgramState = new ProgramState(statement);
        IDictionary<String, Integer> symbolTable = forkProgramState.getSymbolTable();

        symbolTable.setContent(programState.getSymbolTable().entrySet());
        forkProgramState.setOutputList(programState.getOutputList());
        forkProgramState.setFileTable(programState.getFileTable());
        forkProgramState.setHeapTable(programState.getHeapTable());
        forkProgramState.setId(ProgramStateIdBuilder.getProgramStateId());
        forkProgramState.setLatchTable(programState.getLatchTable());

        return forkProgramState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForkStatement that = (ForkStatement) o;

        return statement != null ? statement.equals(that.statement) : that.statement == null;

    }

    @Override
    public int hashCode() {
        return statement != null ? statement.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ForkStatement{" +
                "statement=" + statement +
                '}';
    }
}
