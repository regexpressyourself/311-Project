import java.io.*;
import java.util.*;
import java.lang.Runtime;

public class OurCompiler {

    public void compileSource() {
        String sourceFile = "output.java";
        String sourceName = "output";
        try {
            Process p = null;
            p = Runtime.getRuntime().exec("javac " + sourceFile);
            p.waitFor();
            p = Runtime.getRuntime().exec("rm " + sourceFile);
            p.waitFor();
        }
        catch (Exception e) {
        }
    }
}
