import java.util.*;
import java.io.*;
public class Queue {
    public static void main(String[] args) {

        BufferedReader reader;
        StringBuilder  tempSource ;
        String         line;
        String         source;
        String[]       tokenArray;
        Interpretter   interpretter;

        source = "";

        /* Uncomment to allow user-input source file
        if (args.length != 1) {
            System.out.println ("Usage:   Queue [SOURCE_FILE]\n"    +
                                "example: Queue myfile.queue");
            System.exit(1);
        }
        */

        try {
            reader = new BufferedReader(new FileReader("source.queue"));
            // reader = new BufferedReader(new FileReader(args[0])); Uncomment to allow user-input source file
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
            System.out.println ("FileNotFoundException: cannot find file" + args[1]);
            System.exit(1);
        }
        catch (IOException e) {
            System.out.println ("IOException on file read");
            System.exit(1);
        }

        /*
          - Parser/Analyzer/Interpreter -
        */
        interpreter = new Interpreter(source);
        tokenArray = interpreter.getTokens();





    }

}
