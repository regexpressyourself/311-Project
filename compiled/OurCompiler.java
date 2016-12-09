import java.io.*;
import java.util.*;
import java.lang.Runtime;

public class OurCompiler {
    /**
     * OurCompiler simply compiles down output.java (the file written by LexicalAnalyzer)
     *
     * Using java.lang.Runtime, the class runs the java compiler on output.java
     *
     */

    public void compileSource() {

        /**
         * compileSource compiles down output.java
         *
         * param  none, but assumes output.java exists
         * return none, but writes output.class to the disk
         */

        String sourceFile = "output.java";
        String sourceName = "output";
        try {
            Process p = null;
            p = Runtime.getRuntime().exec("javac " + sourceFile);
            p.waitFor();
        }
        catch (Exception e) {
            System.err.println("Error: Cannot compile output.java\n");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
