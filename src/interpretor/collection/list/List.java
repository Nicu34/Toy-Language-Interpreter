package interpretor.collection.list;

import interpretor.exceptions.runtime.ClassCastException;
import interpretor.exceptions.runtime.IndexOutOfBoundsException;
import interpretor.exceptions.runtime.NullPointerException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class List<E> implements IList<E> {
    private int size;
    private final int CAPACITY;
    private E[] elements;

    public List() {
        this(10);
    }

    public List(E[] elements) {
        this(elements.length);
        this.elements = elements;
    }

    @SuppressWarnings("unchecked")
    public List(int capacity) {
        this.CAPACITY = capacity;
        elements = (E[]) new Object[capacity];
    }

    private void ensureCapacity() {
        if (size + 1 == elements.length)
            elements = Arrays.copyOf(elements, elements.length * 2);
    }

    private void reinitializeArray(int position, boolean larger) {
        if (!larger)
            for (int i = position; i < size; i++)
                elements[i] = elements[i + 1];
        else
            for (int i = size; i >= position; i--)
                elements[i + 1] = elements[i];
    }

    public boolean add(E e) throws NullPointerException{
        if (e == null)
            throw new NullPointerException("Element " + e + " cannot be null.");
        if (size + 1 == elements.length)
            ensureCapacity();
        elements[size++] = e;

        return true;
    }

    public void add(int index, E element) {
        if (size + 1 == elements.length)
            ensureCapacity();
        reinitializeArray(index, true);
        elements[index] = element;
        size++;
    }

    public E get(int i) throws IndexOutOfBoundsException{
        if (i >= size) {
            throw new IndexOutOfBoundsException("Position " + i + " doesn't exist.");
        }
        return elements[i];
    }

    public int size() {
        return size;
    }

    public E remove (int index) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Position " + index + " doesn't exist.");
        }
        E   e = elements[index];

        elements[index] = null;
        size--;
        reinitializeArray(index, false);
        return e;
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++)
            if (elements[i].equals(o)) {
                elements[i] = null;
                size--;
                reinitializeArray(i, false);
                break;
            }
        return true;
    }

    public boolean contains(Object o) {
        for (int i = 0;i < size; i++)
            if (elements[i] == o)
                return true;
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++){
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        List<?> list = (List<?>) o;

        if (size != list.size) return false;
        if (CAPACITY != list.CAPACITY) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(elements, list.elements);

    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + CAPACITY;
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

    @Override
    public String toString() {
        String  result = "Out:\n";

        for (int i = 0; i < size; i++) {
            result += elements[i] + "\n";
        }

        return result;
    }

    @Override
    public java.util.List<E> toJavaUtilList() {
//        return Arrays.stream(elements).filter(e -> e != null).collect(Collectors.toList());
        LinkedList<E> list = new LinkedList<E>();

        for (int i = 0; i < size; i++) {
            list.add(elements[i]);
        }

        return list;
    }

    @Override
    public IList<E> setContent (Object[] elements) throws ClassCastException{
        this.clear();
        this.elements = ((E[]) elements);
        size = elements.length;
        return this;
    }
}
