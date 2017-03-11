package interpretor.utils;

import java.io.*;

/**
 * Created by nicu on 12/8/2016.
 */
public class SerializationUtil {
    public static Object deserialize(String fileName) throws IOException,
            ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();

        return object;
    }

    public static void serialize(Object object, String fileName)
            throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ObjectOutputStream outputStream = new ObjectOutputStream(bufferedOutputStream);
        outputStream.writeObject(object);
        outputStream.close();
    }
}
