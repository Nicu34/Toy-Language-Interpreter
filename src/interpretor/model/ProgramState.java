package interpretor.model;

import interpretor.collection.dictionary.Dictionary;
import interpretor.collection.dictionary.IDictionary;
import interpretor.collection.list.IList;
import interpretor.collection.list.List;
import interpretor.collection.stack.IStack;
import interpretor.collection.stack.Stack;
import interpretor.exceptions.model.statement.NoStatementException;
import interpretor.exceptions.model.statement.StatementException;
import interpretor.model.statement.IStatement;
import interpretor.utils.FilePair;
import interpretor.utils.ProgramStateIdBuilder;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class ProgramState implements Serializable{
    private Integer id = ProgramStateIdBuilder.getProgramStateId();
    private IStack<IStatement> executionStack;
    private IDictionary<String, Integer> symbolTable;
    private IList<Integer> outputList;
    private IStatement originalProgram;
    private IDictionary<Integer, FilePair> fileTable;
    private IDictionary<Integer, Integer> heapTable;
    private IDictionary<Integer, Integer> latchTable;

    public ProgramState(IStatement originalProgram) {
        executionStack = new Stack<>();
        symbolTable = new Dictionary<>();
        outputList = new List<>();
        fileTable = new Dictionary<>();
        heapTable = new Dictionary<>();
        latchTable = new Dictionary<>();
        this.originalProgram = originalProgram;
        executionStack.push(originalProgram);
    }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(IStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public IDictionary<String, Integer> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(IDictionary<String, Integer> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public IList<Integer> getOutputList() {
        return outputList;
    }

    public void setOutputList(IList<Integer> outputList) {
        this.outputList = outputList;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public IDictionary<Integer, FilePair> getFileTable() {
        return fileTable;
    }

    public void setFileTable(IDictionary<Integer, FilePair> fileTable) {
        this.fileTable = fileTable;
    }

    public IDictionary<Integer, Integer> getHeapTable() {
        return heapTable;
    }

    public void setHeapTable(IDictionary<Integer, Integer> heapTable) {
        this.heapTable = heapTable;
    }

    public IDictionary<Integer, Integer> getLatchTable() {
        return latchTable;
    }

    public void setLatchTable(IDictionary<Integer, Integer> latchTable) {
        this.latchTable = latchTable;
    }

    public Integer getId() {
        return id;
    }

    public ProgramState setId(Integer id) {
        this.id = id;
        return this;
    }

    public boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws StatementException {
        if (!executionStack.isEmpty()) {
            IStatement statement = executionStack.pop();

            return statement.execute(this);
        }
        throw new NoStatementException("No statement defined in program state: " + this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgramState that = (ProgramState) o;

        if (executionStack != null ? !executionStack.equals(that.executionStack) : that.executionStack != null)
            return false;
        if (symbolTable != null ? !symbolTable.equals(that.symbolTable) : that.symbolTable != null) return false;
        if (outputList != null ? !outputList.equals(that.outputList) : that.outputList != null) return false;
        return originalProgram != null ? originalProgram.equals(that.originalProgram) : that.originalProgram == null;

    }

    @Override
    public int hashCode() {
        int result = executionStack != null ? executionStack.hashCode() : 0;
        result = 31 * result + (symbolTable != null ? symbolTable.hashCode() : 0);
        result = 31 * result + (outputList != null ? outputList.hashCode() : 0);
        result = 31 * result + (originalProgram != null ? originalProgram.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\n" +
                "Original program: "
                + originalProgram + "\n"
                + executionStack + "\n"
                + "Symbol Table:\n"
                + symbolTable + "\n"
                + outputList + "\n"
                + "File Table:\n"
                + fileTable + "\n"
                + "Heap Table:\n"
                + heapTable + "\n"
                + "Latch table:\n"
                + latchTable +
                "\n=================================================================================\n\n";
    }
}
