import java.io.*;
public class MainClass{
    public static void main(String args[]){
        try{
            LeLexer lexer   = new LeLexer(new FileInputStream(new File("program.le")));
            LeParser parser = new LeParser(lexer);
            parser.init();
            parser.program();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}