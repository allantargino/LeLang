import java.util.*;
import java.io.*;
import lelang.models.*;
import lelang.util.*;

public class LeCompiler {
    private static File inputFile, outputFile, errorFile;
    private static String errors, code, beautyCode;
    private static LeLexer lexer;
    private static LeParser parser;
    private static ErrorHandler errorHandler;
    private static Beautifier beautifier;

    public static void main(String args[]) throws Exception {
        //Files to Le Compiler
        inputFile = new File(args[0]); //Input file in .le
        outputFile = new File(args[1]); //Output file in .c
        errorFile = new File(outputFile.getParent() + "/errors.csv"); //Error file in .csv

        //Lexic, Syntactic and Semantic Analysis
        lexer = new LeLexer(new FileInputStream(inputFile));
        parser = new LeParser(lexer);

        try {
            parser.Init(); //Constructor
            errorHandler = parser.GetErrorHandler(); //Get error handler logger
            parser.program(); //Starts compilation
        } catch (Exception e) {
            errorHandler.AddError(ErrorCategory.Critical, 0, lexer.GetLineNumber(), e.getMessage()); //Adds an error from parser
        }

        //Check for errors
        if (errorHandler.HasError()) {
            //Error handling
            errors = errorHandler.toString();
            FileHandler.WriteFileContent(errors, errorFile);
            FileHandler.DeleteFile(outputFile);
            System.out.println("\t--> COMPILATION DID NOT SUCCEDED. ERROR FILE: " + errorFile.getCanonicalPath()); //Message output
        } else {
            //Code Generation
            code = parser.GetCode();
            //Code indentation format
            beautifier = new Beautifier(code);
            beautyCode = beautifier.BeautifyCCode();
            //Code output
            FileHandler.WriteFileContent(beautyCode, outputFile);
            FileHandler.DeleteFile(errorFile);
            System.out.println("\t--> COMPILATION SUCCEDED. PROGRAM FILE: " + outputFile.getCanonicalPath()); //Message output
        }
    }
}