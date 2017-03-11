package interpretor.model.statement;

import interpretor.exceptions.model.ModelException;
import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.StatementException;
import interpretor.exceptions.model.statement.StatementExecutionException;
import interpretor.model.ProgramState;

import java.io.Serializable;

/**
 * Created by MuresanN on 11/20/2016.
 */
public interface IStatement extends Serializable {
    ProgramState    execute(ProgramState state) throws StatementException;
}
