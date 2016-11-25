import java.util.*;
import java.io.*;
public class LexicalAnalyzer{
    static String[] tokenArray;
    static QueueImplementation<Integer> queue = new QueueImplementation<Integer>();
    static int i = 0;

    public LexicalAnalyzer(String[] tokenArray) {
        this.tokenArray = tokenArray;
    }

    public void analyzeTokens() {
        for (; i < tokenArray.length; i++) {
            switch (tokenArray[i]) {
            case "ADD":
                if (Integer.parseInt(tokenArray[i+1]) >= 0  &&
                    Integer.parseInt(tokenArray[i+1]) <= 11) {
                    queue.add(Integer.parseInt(tokenArray[++i]));
                }
                break;
            case "REMOVE":
                queue.remove();
                break;
            case "PEEK":
                queue.showFirst((Integer) queue.peek());
                break;
            case "LENGTH":
                queue.getLength(queue.size());
                break;
            case "EMPTY":
                queue.isEmpty();
                break;
            case "NOT_EMPTY":
                queue.notEmpty();
                break;
            case "VIEW":
                queue.view();
                break;
            case "IF":
                handleIf();
                break;
            case "CLEAR":
                queue.clear();
                break;
            default:
                break;

            }
            if (tokenArray[i+1].equals(";")) {
                i += 1;
            }
        } // end for
    }
    public static void handleIf() {
        while (!tokenArray[i].equals("(")){ i++; }
        i++;

        if (tokenArray[i].equals("EMPTY") && tokenArray[i+2].equals(";")){
            if(queue.isEmpty()) {
                i += 1;
            }
            else {
                i += 3;
            }
        }
        else if (tokenArray[i].equals("NOT_EMPTY") && tokenArray[i+2].equals(";")){
            if(queue.notEmpty()) {
                i += 1;
            }
            else {
                i += 3;
            }
        }
        else {
            syntaxError();
        }
    }
    public static void syntaxError() {
    }
}
