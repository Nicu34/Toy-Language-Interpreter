package interpretor.collection.stack;

import interpretor.exceptions.runtime.EmptyStackException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class StackTest {

    @Test
    public void test() {
        IStack<String>  stack = new Stack<>(1);

        assertEquals(stack.isEmpty(), true);

        stack.push("one");
        stack.push("two");
        stack.push("three");

        assertEquals(stack.pop(), "three");
        assertEquals(stack.peek(), "two");
        assertEquals(stack.search(10), -1);
        assertEquals(stack.search("one"), 2);
        assertEquals(stack.search("two"), 1);
    }

    @Test(expected = EmptyStackException.class)
    public void testEmpty() {
        new Stack<String>().pop();
        new Stack<String>().peek();
    }
}