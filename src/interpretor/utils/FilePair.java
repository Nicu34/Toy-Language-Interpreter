package interpretor.utils;

import java.io.BufferedReader;
import java.io.File;

/**
 * Created by nicu on 11/20/2016.
 */
public class FilePair {
    private File file;

    private BufferedReader bufferedReader;

    public FilePair(String file) {
        this.file = new File(file);
        this.bufferedReader = null;
    }

    public FilePair(String file, BufferedReader bufferedReader) {
        this.file = new File(file);
        this.bufferedReader = bufferedReader;
    }

    public String toString() {
        return file.getName();
    }

    public File getFile() {
        return file;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilePair filePair = (FilePair) o;

        return file != null ? file.equals(filePair.file) : filePair.file == null;

    }

    @Override
    public int hashCode() {
        return file != null ? file.hashCode() : 0;
    }
}
