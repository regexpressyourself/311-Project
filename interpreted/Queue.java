import java.util.*;
import java.io.*;
public class Queue {
    /**
     * Queue class is our main driver
     */

    public static void main(String[] args) {

        BufferedReader reader;      // reader for our source code
        StringBuilder  tempSource;  // mutable version of source string
        String         line;        // holds current line while source is read
        String[]       tokenArray;  // array of tokens returned by Parser.java
        String         source;      // the source itself
        String         sourceFile;  // the name of the .queue file
        Interpreter    interpreter; // the interpreter class
        boolean        verbose;     // verbose option 

        source     = "";
        sourceFile = "";
        verbose    = true; // runs verbose by default

        if (args.length != 1 && args.length != 2) {
            // check for correct arguments
            System.out.println ("Usage:   Queue [OPTION] SOURCE_FILE\n"    +
                                "example: Queue -v myfile.queue");
            System.exit(1);
        }
        if (args.length == 2) {
            // check if verbose is specified
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
            // no verbose
            verbose    = false;
            sourceFile = args[0];
        }

        try {
            // read in source code
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
            // cannot find source file specified
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

        // instantiate Interpreter with source String and verbose option
        interpreter = new Interpreter(source, verbose);





    }

}
