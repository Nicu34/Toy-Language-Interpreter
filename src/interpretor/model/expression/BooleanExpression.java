package interpretor.model.expression;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.model.expression.ComparisonException;
import interpretor.exceptions.model.expression.ExpressionException;

/**
 * Created by MuresanN on 12/6/2016.
 */
public class BooleanExpression implements IExpression {
    private IExpression expression1;

    private IExpression expression2;

    private String comparisonOperator;

    public BooleanExpression(IExpression expression1, IExpression expression2, String comparisonOperator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.comparisonOperator = comparisonOperator;
    }

    public IExpression getExpression1() {
        return expression1;
    }

    public void setExpression1(IExpression expression1) {
        this.expression1 = expression1;
    }

    public IExpression getExpression2() {
        return expression2;
    }

    public void setExpression2(IExpression expression2) {
        this.expression2 = expression2;
    }

    public String getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(String comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public void notOperator() {
          switch (comparisonOperator) {
              case "<":
                  this.comparisonOperator = ">=";
                  break;
              case "<=":
                  this.comparisonOperator = ">";
                  break;
              case ">":
                  this.comparisonOperator = "<=";
                  break;
              case ">=":
                  this.comparisonOperator = "<";
                  break;
              case "==":
                  this.comparisonOperator = "!=";
                  break;
              case "!=":
                  this.comparisonOperator = "==";
                  break;
          }
    }

    public Integer evaluate(IDictionary<String, Integer> symbolTable, IDictionary<Integer, Integer> heapTable) throws ExpressionException {
        Integer result1 = expression1.evaluate(symbolTable, heapTable);
        Integer result2 = expression2.evaluate(symbolTable, heapTable);
        Boolean booleanEvalResult;

        switch (comparisonOperator) {
            case "<":
                booleanEvalResult = result1 < result2;
                break;
            case "<=":
                booleanEvalResult = result1 <= result2;
                break;
            case ">":
                booleanEvalResult = result1 > result2;
                break;
            case ">=":
                booleanEvalResult = result1 >= result2;
                break;
            case "==":
                booleanEvalResult = result1.equals(result2);
                break;
            case "!=":
                booleanEvalResult = !result1.equals(result2);
                break;
            default:
                throw new ComparisonException(comparisonOperator + " is not a valid comparison operator.");
        }

        return booleanEvalResult ? 1 : 0;
    }

    @Override
    public String toString() {
        return expression1 + " " + comparisonOperator + " " + expression2;
    }

}
