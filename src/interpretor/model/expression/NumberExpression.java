package interpretor.model.expression;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.runtime.ClassCastException;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class NumberExpression implements IExpression {
    private Integer     number;

    public NumberExpression() {
    }

    public NumberExpression(Object number) throws ClassCastException{
        if (! (number instanceof Integer ) )
            throw new ClassCastException("Invalid number expression. " + number + " is not a integer.");
        this.number = (Integer)number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer  evaluate(IDictionary<String, Integer> table, IDictionary<Integer, Integer> heapTable) {
        return number;
    }

    @Override
    public String toString() {
        return number.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberExpression that = (NumberExpression) o;

        return number != null ? number.equals(that.number) : that.number == null;

    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
