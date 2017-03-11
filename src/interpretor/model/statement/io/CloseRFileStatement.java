package interpretor.model.statement.io;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.StatementExecutionException;
import interpretor.exceptions.model.statement.io.IOFileNotFoundException;
import interpretor.exceptions.model.statement.io.IOUnknownException;
import interpretor.model.ProgramState;
import interpretor.model.expression.IExpression;
import interpretor.utils.FileDescriptorBuilder;
import interpretor.utils.FilePair;

import java.io.IOException;

/**
 * Created by nicu on 11/20/2016.
 */
public class CloseRFileStatement implements IOStatement {
    IExpression varFileId;

    public CloseRFileStatement(IExpression varFileId) {
        this.varFileId = varFileId;
    }

    public ProgramState execute(ProgramState programState) throws StatementExecutionException, IOFileNotFoundException, IOUnknownException {
        IDictionary<String, Integer> symbolTable = programState.getSymbolTable();
        IDictionary<Integer, Integer> heapTable = programState.getHeapTable();
        IDictionary<Integer, FilePair> fileTable = programState.getFileTable();
        Integer value;

        try {
            value = varFileId.evaluate(symbolTable, heapTable);
        } catch (ExpressionException e) {
            throw new StatementExecutionException(e.getMessage());
        }
        if (!fileTable.containsKey(value))
            throw new IOFileNotFoundException();
        try {
            fileTable.get(value).getBufferedReader().close();
        } catch (IOException e) {
            throw new IOUnknownException(e.getMessage());
        }
        fileTable.remove(value);
        FileDescriptorBuilder.freeFileDescriptor(value);

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CloseRFileStatement that = (CloseRFileStatement) o;

        return varFileId != null ? varFileId.equals(that.varFileId) : that.varFileId == null;

    }

    @Override
    public int hashCode() {
        return varFileId != null ? varFileId.hashCode() : 0;
    }

    public String toString() {
        return "Close R File " + varFileId;
    }

}
