package interpretor.exceptions.repository;

import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import interpretor.exceptions.controller.ControllerException;
import interpretor.exceptions.model.ModelException;

/**
 * Created by nicu on 11/20/2016.
 */
public class RepositoryException extends Exception{
    public RepositoryException() {
        super();
    }

    public RepositoryException(String s) {
        super(s);
    }
}
