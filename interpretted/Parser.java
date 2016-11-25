import java.util.*;

public class Parser {
    public String       sourceCode;
    public char[]       sourceArray;
    public char         currentChar;
    public static List<String> tokenArray;
    public static String       nextToken;
    public String       finalTokenArray[];
    static QueueImplementation<Integer> queue = new QueueImplementation<Integer>();
    static int ifArgCount = 0;

    public Parser() {
        this.sourceCode = "";
        this.sourceArray = sourceCode.toCharArray();
    }
    public Parser(String sourceCode) {
        this.sourceCode = sourceCode;
        this.sourceArray = sourceCode.toCharArray();
    }

    public String[] getTokens() {

        boolean executeIf = false;
        boolean inIf = false;
        boolean addNextToken = false;
        boolean ignoreNextToken = false;
        tokenArray = new ArrayList<String>();
        nextToken  = "";

        for (int i = 0; i < sourceCode.length(); i++){
            currentChar = sourceCode.charAt(i);
            if (!(Character.isSpaceChar(currentChar) || currentChar == '\n')) {

                if (Character.isDigit(currentChar)) {
                    nextToken = getNextNumber(sourceArray, i);
                    i += nextToken.length() - 1;
                } //end isDigit

                else if (Character.isLetter(currentChar)) {
                    nextToken = getNextWord(sourceArray, i);
                    i += nextToken.length() - 1;
                } //end isLetter

                else if (isSymbol(sourceArray, i)) {

                    nextToken = getNextSymbol(sourceArray, i);
                    i += nextToken.length() - 1;
                }



                System.out.println("Got token " + nextToken);

                if (ignoreNextToken) {
                    ignoreNextToken = false;
                }
                else if (inIf) {
                    ifArgCount += 1;
                    if (ifArgCount == 2) {
                        switch (nextToken) {
                        case "EMPTY":
                            if (queue.isEmpty()) {
                                executeIf = true;
                            }
                            else {
                                executeIf = false;
                            }
                            break;
                        case "NOT_EMPTY":
                            if (queue.notEmpty()) {
                                executeIf = true;
                            }
                            else {
                                executeIf = false;
                            }
                            break;
                        }
                    }
                    else if (ifArgCount == 5 || addNextToken) {
                        if (executeIf) {
                            if (addNextToken) {
                                addNextToken = false;
                                ignoreNextToken = true;
                                executeIf = false;
                                inIf = false;
                                queue.add(nextToken);
                            }
                            else {
                                switch (nextToken) {
                                case "ADD":
                                    addNextToken = true;
                                    break;
                                case "REMOVE":
                                    queue.remove();
                                    inIf = false;
                                    ignoreNextToken = true;
                                    break;
                                case "PEEK":
                                    queue.showFirst((String) queue.peek());
                                    ignoreNextToken = true;
                                    inIf = false;
                                    break;
                                case "LENGTH":
                                    queue.getLength(queue.size());
                                    ignoreNextToken = true;
                                    inIf = false;
                                    break;
                                case "VIEW":
                                    queue.view();
                                    ignoreNextToken = true;
                                    inIf = false;
                                    break;
                                case "CLEAR":
                                    queue.clear();
                                    inIf = false;
                                    ignoreNextToken = true;
                                    break;
                                default:
                                    syntaxError();
                                    break;

                                }// end switch
                                executeIf = false;
                            } // end else
                        }
                    }
                    else if (ifArgCount == 5) {
                        ifArgCount = 0;
                        inIf = false;
                        System.out.println("OUTIFF");
                    }
                    else if (ifArgCount == 6) {
                        ifArgCount = 0;
                        inIf = false;
                        System.out.println("OUTIFF");
                    }
                }
                else {

                    if (addNextToken) {
                        addNextToken = false;
                        ignoreNextToken = true;
                        queue.add(nextToken);
                    }
                    else {
                        switch (nextToken) {
                        case "ADD":
                            addNextToken = true;
                            break;
                        case "REMOVE":
                            queue.remove();
                            ignoreNextToken = true;
                            break;
                        case "PEEK":
                            queue.showFirst((String) queue.peek());
                            ignoreNextToken = true;
                            break;
                        case "LENGTH":
                            queue.getLength(queue.size());
                            ignoreNextToken = true;
                            break;
                        case "VIEW":
                            queue.view();
                            ignoreNextToken = true;
                            break;
                        case "IF":
                            inIf = true;
                            System.out.println("INIFF");
                            ifArgCount = 0;
                            break;
                        case "CLEAR":
                            queue.clear();
                            ignoreNextToken = true;
                            break;
                        default:
                            syntaxError();
                            break;

                        }
                    }
                }
                tokenArray.add(nextToken);
            }// end if(!is space)
        } //end for loop

        finalTokenArray = new String[tokenArray.size()];
        finalTokenArray = tokenArray.toArray(finalTokenArray);
        return finalTokenArray;

    }// end getTokens

    public static void syntaxError() {
        System.out.println("Syntax error on token: " + nextToken);
        System.exit(1);
    }

    public static boolean isSymbol(char[] charArray, int currentIndex){
        switch (charArray[currentIndex]) {
        case '(':
        case ')':
        case ';':
            return true;
        default:
            return false;
        }
    } //end isSymbol

    public static String getNextSymbol(char[] charArray, int currentIndex) {
        String returnString = "";
        switch (charArray[currentIndex]) {
        case '(':
        case ')':
        case ';':
            returnString =  String.valueOf(charArray[currentIndex]);
            break;
        default:
            break;
        }
        return returnString;
    }
    public static String getNextWord(char[] charArray, int currentIndex){
        String nextWord = "";
        while (Character.isLetter(charArray[currentIndex]) ||
               Character.isDigit (charArray[currentIndex])  ||
               charArray[currentIndex] == '_'){
            nextWord += charArray[currentIndex++];
        }
        return nextWord;

    }
    public static String getNextNumber(char[] charArray, int currentIndex){
        String nextNum = "";
        while (Character.isDigit(charArray[currentIndex])){
            nextNum += charArray[currentIndex++];
        }
        return nextNum;

    } //end getNexNumber

}
