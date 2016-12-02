import java.util.*;
import java.io.*;
public class LexicalAnalyzer{
    static String[] tokenArray;
    static int i = 0;
    static String javaCode = "";

    public LexicalAnalyzer(String[] tokenArray) {
        this.tokenArray = tokenArray;
        initializeJavaCode();
        writeQueueClass();
    }

    public void analyzeTokens() {
        try {
            File file = new File("output.java");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());

            BufferedWriter writer = new BufferedWriter(fw);

            javaCode +=
                "static QueueImplementation<Integer> queue = new QueueImplementation<Integer>();\n" +
                "public static void main(String[] args) {\n" ;

            for (; i < tokenArray.length; i++) {
                switch (tokenArray[i]) {
                case "ADD":
                    if (Integer.parseInt(tokenArray[i+1]) >= 0  &&
                        Integer.parseInt(tokenArray[i+1]) < 11) {
                        javaCode += "queue.add("+Integer.parseInt(tokenArray[++i])+");\n";
                        System.out.println("Next line of execution: queue.add("+Integer.parseInt(tokenArray[i])+");\n");
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
                    i += 1;
                }
            } // end for
            javaCode += "}\n";
            javaCode += "}\n";
            writer.write(javaCode);
            writer.close();
        }
        catch (Exception e)
            {
                e.printStackTrace();
            }
    }
    public static void handleIf() {
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
        System.out.println("Syntax error on token: " + tokenArray[i]);
        System.exit(1);
    }
    public static void initializeJavaCode() {
        javaCode += "import java.util.*;\n" +
            "import java.io.*;\n" +
            "public class output {\n" ;
    }

    public static void writeQueueClass() {
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
