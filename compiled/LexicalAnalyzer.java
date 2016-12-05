import java.util.*;
import java.io.*;
public class LexicalAnalyzer{

    /**
     * Lexical Analyzer
     * This class takes in an array of tokens and analyzes them.
     */

    static String[] tokenArray;    // The array of tokens taken from Parser.java
    static int      i        = 0;  // A token counter
    static String   javaCode = ""; // Java implementation of the original Q Language source code

    public LexicalAnalyzer(String[] tokenArray) {
        this.tokenArray = tokenArray;
        initializeJavaCode(); // Write in import statements and start of output class
        writeQueueClass();    // Write in the QueueImplmentation class to use
    }

    public void analyzeTokens() {
        try {
            File file = new File("output.java");
            // Java file to be compiled down into executable

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter     fw     = new FileWriter(file.getAbsoluteFile());
            BufferedWriter writer = new BufferedWriter(fw);

            javaCode += "static QueueImplementation<Integer> queue = new QueueImplementation<Integer>();\n" +
                "public static void main(String[] args) {\n" ;

            for (; i < tokenArray.length; i++) {
                // for every token, analyze it and add the correct QueueImplementation method
                switch (tokenArray[i]) {
                case "ADD":
                    if (Integer.parseInt(tokenArray[i+1]) >= 0  &&
                        Integer.parseInt(tokenArray[i+1]) < 11) {
                        javaCode += "queue.add("+ Integer.parseInt(tokenArray[++i]) +");\n";
                        System.out.println("Next line of execution: queue.add("+ Integer.parseInt(tokenArray[i]) +");\n");
                    }
                    else {
                        System.err.println("Queue elements must be integers from 0 - 10");
                        syntaxError();
                    }
                    break;
                case "REMOVE":
                    javaCode += "queue.remove();\n";
                    System.out.println("Next line of execution: queue.remove();\n");
                    break;
                case "PEEK":
                    javaCode += "queue.showFirst((Integer) queue.peek());\n";
                    System.out.println("Next line of execution: queue.showFirst((Integer) queue.peek());\n");
                    break;
                case "LENGTH":
                    javaCode += "queue.getLength(queue.size());\n";
                    System.out.println("Next line of execution: queue.getLength(queue.size());\n");
                    break;
                case "EMPTY":
                    javaCode += "queue.isempty();\n";
                    System.out.println("Next line of execution: queue.isempty();\n");
                    break;
                case "NOT_EMPTY":
                    javaCode += "queue.notEmpty();\n";
                    System.out.println("Next line of execution: queue.notEmpty();\n");
                    break;
                case "VIEW":
                    javaCode += "queue.view();\n";
                    System.out.println("Next line of execution: queue.view();\n");
                    break;
                case "IF":
                    handleIf();
                    break;
                case "CLEAR":
                    javaCode += "queue.clear();\n";
                    System.out.println("Next line of execution: queue.clear();\n");
                    break;
                default:
                    syntaxError();
                    break;
                }
                if (tokenArray[i+1].equals(";")) {
                    // skip semi colons
                    i += 1;
                }
            } // end for

            javaCode += "}\n"; // close out the Main Driver
            javaCode += "}\n"; // close out the class

            writer.write(javaCode); // write the string to output.java
            writer.close();         // close output.java
        }
        catch (Exception e) { // catch any exceptions in file I/O
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void handleIf() {
        /**
         * handleIf evaluates what is inside an if statement
         *
         * When an if statement is reached, handleIf() will be called to
         * evaluate the statement in the parenthesis.
         *
         * @param  none, but it uses static variables javaCode, i and tokenArray.
         * @return none, but i will be incremented accordingly, and javaCode will be edited
         */

        while (!tokenArray[i].equals("(")){ i++; }
        i++;

        if (tokenArray[i].equals("EMPTY") && tokenArray[i+2].equals(";")){
            javaCode += "if(queue.isEmpty()) ";
            i++;
        }
        else if (tokenArray[i].equals("NOT_EMPTY") && tokenArray[i+2].equals(";")){
            javaCode += "if(queue.notEmpty()) ";
            i++;
        }
        else {
            syntaxError();
        }
    }

    public static void syntaxError() {
        /**
         * syntaxError throws an error and exits, displaying the last token analyzed
         *
         * @param  none, but it uses static variables i and tokenArray.
         * @return none
         */
        System.out.println("Syntax error on token: " + tokenArray[i]);
        System.exit(1);
    }

    public static void initializeJavaCode() {
        /**
         * initializeJavaCode writes import statments into the javaCode string
         *
         * @param  none
         * @return none, but it changes the static variable javaCode
         */

        javaCode += "import java.util.*;\n" +
            "import java.io.*;\n" +
            "public class output {\n" ;
    }

    public static void writeQueueClass() {
        /**
         * writeQueueClass writes the QueueImplementation class into our javaCode string
         *
         * The QueueImplementation class extends ArrayDeque, Java's own Queue class.
         * It modifies some methods and adds others to comply with how the Q Programming language
         * is written. You can see the QueueImplementation class in its own file in the interpreted
         * version of the Q Programming Language, under QueueImplmentation.java.
         *
         * @param  none, but it uses the static variable javaCode
         * @return none, but it changes the static variable javaCode
         */
        javaCode +=
            "public static class QueueImplementation<E> extends ArrayDeque {\n" +
            "public void view() {\n" +
            "String    prettyView = \"\";\n" +
            "Object[] arrayQueue = this.toArray();\n" +
            "for (int i = 0; i < arrayQueue.length; i++) {\n" +
            "prettyView += arrayQueue[i] + \" <- \";\n" +
            "}\n" +
            "System.out.println(prettyView);\n" +
            "}" +
            "public void showFirst(Integer firstElement) {\n" +
            "System.out.println(\"The first element is: \" + firstElement);\n" +
            "}" +
            "public void getLength(int length) {\n" +
            "System.out.println(\"The length is: \" + length);\n" +
            "}\n" +
            "public boolean isEmpty() {\n" +
            "return (this.size() == 0);\n" +
            "}\n" +
            "public boolean notEmpty() {\n" +
            "return (this.size() > 0);\n" +
            "}\n" +
            "}\n";
    }
}
