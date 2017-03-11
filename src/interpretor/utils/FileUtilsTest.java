package interpretor.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 12/8/2016.
 */
public class FileUtilsTest {

    @Test
    public void test_replaceFileExtension() {
        assertEquals("fileName.newExtension", FileUtils.replaceFileExtension("fileName.oldExtension", "newExtension"));
    }
}