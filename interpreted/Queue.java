import java.util.*;
import java.io.*;
public class Queue {
    public static void main(String[] args) {

        BufferedReader reader;
        StringBuilder  tempSource ;
        String         line;
        String         source;
        String         sourceFile;
        String[]       tokenArray;
        Interpreter    interpreter;
        boolean        verbose;

        source     = "";
        sourceFile = "";
        verbose    = true;

        if (args.length != 1 && args.length != 2) {
            System.out.println ("Usage:   Queue [OPTION] SOURCE_FILE\n"    +
                                "example: Queue -v myfile.queue");
            System.exit(1);
        }
        if (args.length == 2) {
            if (args[0].equals("-v")){
                verbose    = true;
                sourceFile = args[1];
            }
            else {
                System.out.println ("Usage:   Queue [OPTION] SOURCE_FILE\n"    +
                                    "example: Queue -v myfile.queue");
                System.exit(1);
            }
        }
        else {
            verbose    = false;
            sourceFile = args[0];
        }

        try {
            reader     = new BufferedReader(new FileReader(sourceFile)); 
            tempSource = new StringBuilder();
            line       = reader.readLine();

            while (line != null) {
                tempSource.append(line);
                tempSource.append(System.lineSeparator());
                line = reader.readLine();
            }
            reader.close();
            source = tempSource.toString();
        }
        catch (FileNotFoundException e) {
            System.err.println ("FileNotFoundException: cannot find file " + sourceFile);
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println ("IOException on file read");
            System.exit(1);
        }

        /*
          - Parser/Analyzer/Interpreter -
        */
        interpreter = new Interpreter(source, verbose);
        tokenArray = interpreter.getTokens();





    }

}
