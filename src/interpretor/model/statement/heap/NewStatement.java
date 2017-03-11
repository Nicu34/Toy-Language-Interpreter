package interpretor.model.statement.heap;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.heap.HeapStatementException;
import interpretor.model.ProgramState;
import interpretor.model.expression.IExpression;
import interpretor.utils.HeapAddressBuilder;

/**
 * Created by nicu on 12/3/2016.
 */
public class NewStatement implements HeapStatement{
    private String variableName;

    private IExpression expression;

    private Integer address;

    public NewStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
        this.address = HeapAddressBuilder.getFreeAddress();
    }

    public ProgramState execute(ProgramState programState) throws HeapStatementException {
        IDictionary<String, Integer> symbolTable = programState.getSymbolTable();
        IDictionary<Integer, Integer> heapTable = programState.getHeapTable();

        try {
            heapTable.put(address, expression.evaluate(symbolTable, heapTable));
        }
        catch (ExpressionException e) {
            throw new HeapStatementException();
        }
        symbolTable.put(variableName, address);
        programState.setSymbolTable(symbolTable);
        programState.setHeapTable(heapTable);

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewStatement that = (NewStatement) o;

        if (variableName != null ? !variableName.equals(that.variableName) : that.variableName != null) return false;
        if (expression != null ? !expression.equals(that.expression) : that.expression != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;

    }

    @Override
    public int hashCode() {
        int result = variableName != null ? variableName.hashCode() : 0;
        result = 31 * result + (expression != null ? expression.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public void setExpression(IExpression expression) {
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public IExpression getExpression() {
        return expression;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String toString() {
        return "new ( " + variableName + ", " + expression + " )";
    }
}