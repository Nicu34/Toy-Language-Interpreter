package interpretor.utils;

import interpretor.collection.stack.IStack;
import interpretor.collection.stack.Stack;

/**
 * Created by nicu on 11/20/2016.
 */
public abstract class FileDescriptorBuilder {
    private static Integer fd = 1;

    private static IStack<Integer> freeFileDescriptors = new Stack<>();

    public static Integer getFileDescriptor() {
        return freeFileDescriptors.isEmpty() ? fd++ : freeFileDescriptors.pop();
    }

    public static void freeFileDescriptor(Integer fd) {
        if (fd < FileDescriptorBuilder.fd) {
            freeFileDescriptors.push(fd);
        }
    }
}
