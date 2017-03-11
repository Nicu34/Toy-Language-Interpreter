package interpretor.model.statement.heap;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.StatementExecutionException;
import interpretor.exceptions.model.statement.heap.InvalidHeapAddressException;
import interpretor.model.ProgramState;
import interpretor.model.expression.IExpression;

/**
 * Created by nicu on 12/3/2016.
 */
public class WriteHeapStatement implements HeapStatement {

    private String variableName;

    private IExpression expression;

    public WriteHeapStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public IExpression getExpression() {
        return expression;
    }

    public void setExpression(IExpression expression) {
        this.expression = expression;
    }

    public ProgramState execute(ProgramState programState) throws InvalidHeapAddressException, StatementExecutionException{
        IDictionary<String, Integer> symbolTable = programState.getSymbolTable();
        IDictionary<Integer, Integer> heapTable = programState.getHeapTable();
        Integer address;

        if (!symbolTable.containsKey(variableName))
            throw new StatementExecutionException("Variable " + variableName + " not found.");

        address = symbolTable.get(variableName);

        if (!heapTable.containsKey(address))
            throw new InvalidHeapAddressException("Invalid address " + address);

        try {
            heapTable.put(address, expression.evaluate(symbolTable, heapTable));
        }
        catch (ExpressionException e) {
            throw new StatementExecutionException(e.getMessage());
        }

        programState.setHeapTable(heapTable);

        return null;
    }

    public String toString() {
        return "Write Heap ( " + variableName + " -> " + expression + " )";
    }
}
