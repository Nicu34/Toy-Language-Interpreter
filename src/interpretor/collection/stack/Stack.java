package interpretor.collection.stack;

import interpretor.exceptions.runtime.EmptyStackException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class Stack<E> implements IStack<E> {

    private int size;
    private final int CAPACITY;
    private E[] elements;

    public Stack() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        this.CAPACITY = capacity;
        elements = (E[]) new Object[capacity];
    }

    private void ensureCapacity() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    public E push(E e) {
        if (size == elements.length)
            ensureCapacity();
        elements[size++] = e;

        return e;
    }

    public int size() {
        return size;
    }

    @Override
    public List<E> toJavaUtilList() {
        return Arrays.stream(elements).filter(e -> e != null).collect(Collectors.toList());
    }

    public E pop() throws EmptyStackException{
        if (size == 0)
            throw new EmptyStackException("Stack is empty. Cannot pop.");
        E e = elements[--size];
        elements[size] = null;
        return e;
    }

    public E peek() throws EmptyStackException{
        if (size == 0)
            throw new EmptyStackException("Stack is empty. Cannot peek.");
        return elements[size - 1];
    }

    public int search(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(o))
               return size - i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stack<?> stack = (Stack<?>) o;

        if (size != stack.size) return false;
        if (CAPACITY != stack.CAPACITY) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(elements, stack.elements);

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
        String  result = "ExeStack:\n";

        for (int i = size - 1; i >= 0; i--) {
            result += elements[i] + "\n";
        }

        return result;
    }
}
