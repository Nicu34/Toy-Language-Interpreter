package interpretor.model.expression;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.expression.NotAllocatedException;
import interpretor.exceptions.model.expression.VariableNotFoundException;

/**
 * Created by nicu on 12/3/2016.
 */
public class ReadHeapExpression implements IExpression {
    private String variableName;

    public ReadHeapExpression(String variableName) {
        this.variableName = variableName;
    }

    public Integer evaluate(IDictionary<String, Integer> symbolTable, IDictionary<Integer, Integer> heapTable) throws ExpressionException {
        if (!symbolTable.containsKey(variableName))
            throw new VariableNotFoundException(variableName + " not found in Symbol Table");

        Integer address = symbolTable.get(variableName);

        if (!heapTable.containsKey(address))
            throw new NotAllocatedException("Memory not allocated in segment address " + address);

        return heapTable.get(address);
    }

    public String toString() {
        return "Read Heap ( " + variableName + " )";
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadHeapExpression that = (ReadHeapExpression) o;

        return variableName != null ? variableName.equals(that.variableName) : that.variableName == null;

    }

    @Override
    public int hashCode() {
        return variableName != null ? variableName.hashCode() : 0;
    }
}
