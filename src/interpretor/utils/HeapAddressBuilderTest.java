package interpretor.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 12/3/2016.
 */
public class HeapAddressBuilderTest {

    @Test
    public void test() {
        HeapAddressBuilder.freeAddress(2);
        HeapAddressBuilder.freeAddress(1);
        assertEquals(((Integer) 1), HeapAddressBuilder.getFreeAddress());
        assertEquals(((Integer) 2), HeapAddressBuilder.getFreeAddress());
        HeapAddressBuilder.freeAddress(1);
        assertEquals(((Integer) 1), HeapAddressBuilder.getFreeAddress());
    }
}