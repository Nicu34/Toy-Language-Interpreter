package interpretor.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 12/3/2016.
 */
public class FileDescriptorBuilderTest {

    @Test
    public void test() {
        FileDescriptorBuilder.freeFileDescriptor(2);
        FileDescriptorBuilder.freeFileDescriptor(1);
        assertEquals(((Integer) 1), FileDescriptorBuilder.getFileDescriptor());
        assertEquals(((Integer) 2), FileDescriptorBuilder.getFileDescriptor());
        FileDescriptorBuilder.freeFileDescriptor(1);
        assertEquals(((Integer) 1), FileDescriptorBuilder.getFileDescriptor());
    }

}