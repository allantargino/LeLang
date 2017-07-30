import java.io.*;
public class MainClass{
    public static void main(String args[]){
        try{
            LeLexer lexer   = new LeLexer(new FileInputStream(new File("program.le")));
            LeParser parser = new LeParser(lexer);
            parser.Init();
            parser.program();
            parser.ErrorHandling();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}