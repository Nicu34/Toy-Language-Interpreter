package interpretor.collection.stack;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MuresanN on 11/20/2016.
 */
public interface IStack<E> extends Serializable{
    boolean isEmpty();

    E peek();

    E pop();

    E push(E element);

    int search(Object o);

    int size();

    List<E> toJavaUtilList();
}
