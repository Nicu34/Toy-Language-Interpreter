package interpretor.utils;

import interpretor.collection.stack.IStack;
import interpretor.collection.stack.Stack;

/**
 * Created by nicu on 12/3/2016.
 */
public abstract class HeapAddressBuilder {
    private static Integer address = 1;

    private static IStack<Integer> freeAddresses = new Stack<>();

    public static Integer getFreeAddress() {
        return freeAddresses.isEmpty() ? address++ : freeAddresses.pop();
    }

    public static void freeAddress(Integer unusedAddress) {
        if (unusedAddress < HeapAddressBuilder.address) {
            freeAddresses.push(unusedAddress);
        }
    }

}
