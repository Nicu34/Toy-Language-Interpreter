package interpretor.collection.list;

import interpretor.exceptions.runtime.IndexOutOfBoundsException;
import interpretor.exceptions.runtime.NullPointerException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 10.11.2016.
 */
public class ListTest {

    @Test
    public void test() {
        IList<String> list = new List<>(1);

        assertEquals(list.size(), 0);
        assertEquals(list.contains("object"), false);
        list.add("one");
        list.add("two");
        list.add("three");
        assertEquals(list.size(), 3);
        assertEquals(list.contains("three"), true);
        assertEquals(list.get(0), "one");
        list.add(0, "zero");
        assertEquals(list.size(), 4);
        assertEquals(list.get(0), "zero");
        String one = list.remove(1);
        assertEquals(list.contains("one"), false);
        assertEquals(one, "one");
        assertEquals(list.get(1), "two");
        list.remove("zero");
        assertEquals(list.size(), 2);
        assertEquals(list.get(0), "two");
        list.clear();
        assertEquals(list.size(), 0);
    }

    @Test(expected = NullPointerException.class)
    public void    testNullPointerEx() {
        IList<String>   list = new List<>();

        list.add(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void    indexOutOfBound() {
        IList<String>   list = new List<>();

        list.remove(10);
        list.get(10);
    }
}