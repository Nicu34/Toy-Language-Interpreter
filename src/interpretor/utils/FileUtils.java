package interpretor.utils;

/**
 * Created by nicu on 12/8/2016.
 */
public class FileUtils {
    private static String DOT = ".";

    public static String replaceFileExtension(String fileName, String extension) {
        if (fileName.contains(".")) {
            return fileName.replaceFirst("\\..*$", DOT + extension);
        }
        else
            return fileName + "." + extension;
    }
}
