package interpretor.model.expression;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.model.expression.VariableNotFoundException;
import interpretor.exceptions.runtime.ClassCastException;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class VariableExpression implements IExpression {

    private String  name;

    public VariableExpression() {
    }

    public VariableExpression(Object name) {
        if (! (name instanceof String ) )
            throw new ClassCastException("Invalid Variable Expression. " + name + " is not a String.");
        this.name = (String)name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer  evaluate(IDictionary<String, Integer> table, IDictionary<Integer, Integer> heapTable) throws VariableNotFoundException{
        if (!table.containsKey(name))
            throw new VariableNotFoundException("Variable " + name + " doesn't exist in table ");
        return table.get(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VariableExpression that = (VariableExpression) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
