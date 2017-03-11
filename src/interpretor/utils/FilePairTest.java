package interpretor.utils;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

/**
 * Created by nicu on 12/3/2016.
 */
public class FilePairTest {
    @Test
    public void test() {
        FilePair filePair = new FilePair("file");

        assertEquals("file", filePair.getFile().getName());
        assertEquals(null, filePair.getBufferedReader());
    }
}