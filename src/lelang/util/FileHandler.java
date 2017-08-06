package lelang.util;

import java.io.*;

public class FileHandler {
    public static void WriteFileContent(String text, File output) throws Exception {
        FileOutputStream stream = new FileOutputStream(output);

        if (!output.exists())
            output.createNewFile();

        byte[] bytes = text.getBytes();

        stream.write(bytes);
        stream.flush();
        stream.close();
    }

    public static void DeleteFile(File file) {
        if (file.exists())
            file.delete();
    }
}