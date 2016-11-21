import java.util.*;
import java.io.*;
public class LexicalAnalyzer{
    String[] tokenArray;
    QueueImplementation queue = new QueueImplementation();
    static i = 0;

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
                queue.peek();
                break;
            case "LENGTH":
                System.out.println("Queue size: " + queue.size());
                break;
            case "EMPTY":
                break;
            case "NOT_EMPTY":
                break;
            case "VIEW":
                break;
            case "IF":
                handleIf();
                break;
            default:
                break;

            }
            removeSemiColon(i);
        }
    }

    public static void removeSemiColon() {
        if (tokenArray[i+1].equals(";")) {
            i += 1;
        }
    }
    public static void handleIf() {
    }

}
