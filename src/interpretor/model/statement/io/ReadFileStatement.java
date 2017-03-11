package interpretor.model.statement.io;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.StatementExecutionException;
import interpretor.exceptions.model.statement.io.IOFileNotFoundException;
import interpretor.exceptions.model.statement.io.IOFileNotOpened;
import interpretor.exceptions.model.statement.io.IOInvalidFileFormat;
import interpretor.exceptions.model.statement.io.IOUnknownException;
import interpretor.model.ProgramState;
import interpretor.model.expression.IExpression;
import interpretor.utils.FilePair;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by nicu on 11/20/2016.
 */
public class ReadFileStatement implements IOStatement {
    IExpression varFileId;
    String variableName;

    public ReadFileStatement(IExpression varFileId, String variableName) {
        this.varFileId = varFileId;
        this.variableName = variableName;
    }

    public ProgramState execute(ProgramState programState)
            throws IOFileNotFoundException, IOFileNotOpened, StatementExecutionException, IOUnknownException, IOInvalidFileFormat {
        IDictionary<String, Integer> symbolTable = programState.getSymbolTable();
        IDictionary<Integer, Integer> heapTable = programState.getHeapTable();
        IDictionary<Integer, FilePair> fileTable = programState.getFileTable();
        Integer fileDescriptor;
        Integer finalResult = 0;

        try {
            fileDescriptor = varFileId.evaluate(symbolTable, heapTable);
        } catch (ExpressionException e) {
            throw new StatementExecutionException(e.getMessage());
        }

        if (!fileTable.containsKey(fileDescriptor))
            throw new IOFileNotFoundException();
        BufferedReader bufferedReader = fileTable.get(fileDescriptor).getBufferedReader();
        if (bufferedReader == null)
            throw new IOFileNotOpened();

        try {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                try {
                    Integer number = Integer.parseInt(line);

                    if (number == 0)
                        break;
                    else if (number < 0)
                        throw new IOInvalidFileFormat();
                    finalResult = number;
                } catch (NumberFormatException e) {
                    throw new IOInvalidFileFormat(e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new IOUnknownException(e.getMessage());
        }

        if (finalResult != 0)
            symbolTable.put(variableName, finalResult);
        programState.setSymbolTable(symbolTable);

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadFileStatement that = (ReadFileStatement) o;

        if (varFileId != null ? !varFileId.equals(that.varFileId) : that.varFileId != null) return false;
        return variableName != null ? variableName.equals(that.variableName) : that.variableName == null;

    }

    @Override
    public int hashCode() {
        int result = varFileId != null ? varFileId.hashCode() : 0;
        result = 31 * result + (variableName != null ? variableName.hashCode() : 0);
        return result;
    }

    public String toString () {
        return "Read file having varFileId " + varFileId + " saving in variable " + variableName;
    }
}
