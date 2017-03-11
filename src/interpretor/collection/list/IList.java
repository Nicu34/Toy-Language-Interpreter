package interpretor.collection.list;

import java.io.Serializable;

/**
 * Created by MuresanN on 11/20/2016.
 */
public interface IList<E> extends Serializable{
    boolean add(E e);
    void add(int index, E element);
    int  size();
    E    remove(int index);
    boolean remove(Object o);
    E get(int i);
    boolean contains(Object o);
    void clear();
    java.util.List<E> toJavaUtilList();
    IList<E> setContent(Object[] elements);
}
