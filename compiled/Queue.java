import java.util.*;
import java.io.*;
public class Queue {
    /**
     * Queue class is our main driver
     */

    public static void main(String[] args) {

        BufferedReader  reader;      // reader for our source code
        StringBuilder   tempSource;  // mutable version of source string
        String          line;        // holds current line while source is read
        Parser          parser;      // The Parser
        String[]        tokenArray;  // array of tokens returned by Parser.java
        LexicalAnalyzer le;          // The Analyzer
        String          source = ""; // The source itself

        if (args.length != 1) {
            // throw error if no source file specified
            System.out.println ("Usage:   Queue [SOURCE_FILE]\n" +
                                "example: Queue myfile.queue");
            System.exit(1);
        }

        try {
            reader     = new BufferedReader(new FileReader(args[0]));
            tempSource = new StringBuilder();
            line       = reader.readLine();

            while (line != null) { // read source file
                tempSource.append(line);
                tempSource.append(System.lineSeparator());
                line = reader.readLine();
            }
            reader.close(); // close source file
            source = tempSource.toString();
        }
        catch (FileNotFoundException e) {
            // cannot find source file specified
            System.out.println ("FileNotFoundException: cannot find file " + args[0]);
            System.exit(1);
        }
        catch (IOException e) {
            System.out.println ("IOException on file read");
            System.exit(1);
        }

        /*
          - Parser -
        */
        System.out.printf("############## Parsing.... #################\n");

        parser     = new Parser(source); // instantiate Parser with the source code
        tokenArray = parser.getTokens(); // store the tokens in tokenArray

        for (String token : tokenArray) { // print tokens found
            System.out.printf("Next token is %s\n", token);
        }

        /*
          - Lexical Analysis -
        */
        System.out.printf("\n\n############## Analyzing.... #################\n");

        le = new LexicalAnalyzer(tokenArray); // instantiate analyzer with token array
        le.analyzeTokens(); // analyzer tokens, writing the result into output.java

        /*
          - Compiler -
        */
        System.out.printf("\n\n############## Compiling.... #################\n");

        OurCompiler oc = new OurCompiler(); // instantiate compiler class
        oc.compileSource(); // compile down output.java to output.class

        System.out.printf("\n\n############## Done! #################\n");
        System.out.printf("Your file is compiled. You can run it by running: \n\n" +
                          "\tjava output\n\nHappy queueing!\n");


    }

}
