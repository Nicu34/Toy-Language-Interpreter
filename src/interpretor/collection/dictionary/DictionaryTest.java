package interpretor.collection.dictionary;

import interpretor.exceptions.runtime.NullPointerException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class DictionaryTest{

    @Test
    public void test() {
        IDictionary<String, String> map = new Dictionary<>(1);

        assertEquals(map.size(), 0);
        assertEquals(map.containsKey("null"), false);
        assertNotNull(map);
        assertEquals(map.keySet().size(), 0);

        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");

        assertEquals(map.size(), 3);
        assertEquals(map.containsKey("three"), true);
        assertEquals(map.containsValue("1"), true);

        map.remove("one");

        assertEquals(map.size(), 2);
        assertEquals(map.containsKey("one"), false);
        assertEquals(map.containsValue("1"), false);
        assertEquals(map.get("two"), "2");
        assertEquals(map.get("four"), null);

        IDictionary<String, String> otherMap = new Dictionary<>();

        otherMap.setContent(map.entrySet());
        map.put("other", "value");

        assertNotEquals(otherMap, map);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerEx() {
        IDictionary<String, String> map = new Dictionary<>(1);

        map.containsKey(null);
        map.containsValue(null);
    }
}