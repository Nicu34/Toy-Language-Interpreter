package interpretor.utils;

import interpretor.collection.stack.IStack;
import interpretor.collection.stack.Stack;

/**
 * Created by nicu on 1/26/2017.
 */
public class FreeLocationLatchBuilder {
    private static Integer freeLocationKey = 0;

    private static IStack<Integer> freeAddresses = new Stack<>();

    public static Integer getFreeAddress() {
        return freeAddresses.isEmpty() ? freeLocationKey++ : freeAddresses.pop();
    }

    public static void freeAddress(Integer unusedAddress) {
        if (unusedAddress < FreeLocationLatchBuilder.freeLocationKey) {
            freeAddresses.push(unusedAddress);
        }
    }
}
