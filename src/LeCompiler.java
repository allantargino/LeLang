import java.io.*;
import lelang.util.*;

public class LeCompiler {
    public static void main(String args[]) {
        try {
            LeLexer lexer = new LeLexer(new FileInputStream(new File("program.le")));
            LeParser parser = new LeParser(lexer);
            parser.Init();
            parser.program();
            parser.ErrorHandling();
            String code = parser.GetCode();

            Beautifier beautifier = new Beautifier(code);
            String beautyCode = beautifier.BeautifyCCode();

            WriteFileCode(beautyCode);
        } catch (IOException e) {
			e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
		}
    }

    private static void WriteFileCode(String text) throws Exception {
        File file = new File("program.c");
        FileOutputStream stream = new FileOutputStream(file);

        if (!file.exists())
            file.createNewFile();

        byte[] bytes = text.getBytes();

        stream.write(bytes);
        stream.flush();
        stream.close();
    }
}