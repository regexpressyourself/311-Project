import java.util.*;

public class Interpreter {
    public static List<String> tokenArray;
    public static String       nextToken;

    public String       sourceCode;
    public char[]       sourceArray;
    public char         currentChar;
    public String       finalTokenArray[];

    static QueueImplementation<Integer> queue = new QueueImplementation<Integer>();
    static int ifArgCount = 0;

    public Interpreter() {
        this.sourceCode  = "";
        this.sourceArray = sourceCode.toCharArray();
    }
    public Interpreter(String sourceCode) {
        this.sourceCode  = sourceCode;
        this.sourceArray = sourceCode.toCharArray();
    }

    public String[] getTokens() {

        boolean executeIf       = false; // evaluate expression following if statement
        boolean inIf            = false; // in an if statement
        boolean addNextToken    = false; // triggered when the next token is an element to be added
        boolean ignoreNextToken = false; // flag to ignore semi colons
        nextToken               = "";
        tokenArray              = new ArrayList<String>();

        for (int i = 0; i < sourceCode.length(); i++){
            currentChar = sourceCode.charAt(i);
            if (!(Character.isSpaceChar(currentChar) || currentChar == '\n')) {

                if (Character.isDigit(currentChar)) {
                    nextToken = getNextNumber(sourceArray, i);
                    i        += nextToken.length() - 1;
                } //end isDigit

                else if (Character.isLetter(currentChar)) {
                    nextToken = getNextWord(sourceArray, i);
                    i        += nextToken.length() - 1;
                } //end isLetter

                else if (isSymbol(sourceArray, i)) {
                    nextToken = getNextSymbol(sourceArray, i);
                    i        += nextToken.length() - 1;
                }
                else {
                    // if it's not a symbol, word, or number, throw a syntax error
                    nextToken = "" + currentChar;
                    syntaxError();
                }

                // Once the token is found, analyze it

                System.out.println("Got token " + nextToken);

                if (ignoreNextToken) {
                    // ignoreNextToken is triggered to ignore a semi colon

                    if (nextToken != ";"){
                        syntaxError();
                    }

                    ignoreNextToken = false;
                    if (inIf) {
                        // reset if related flags
                        ifArgCount = 0;
                        inIf       = false;
                        executeIf  = false;
                        System.out.println("-OUT IF-");
                    }
                }
                else if (inIf) {
                    // catch when we are inside an if statement
                    ifArgCount += 1;

                    if (ifArgCount == 2) { // the statement in the parentheses
                        executeIf = evaluateIf(nextToken);
                    }

                    else if ((ifArgCount == 5) ) {
                        // the statement that follows the if statement
                        if (executeIf) {
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
                            case "CLEAR":
                                queue.clear();
                                ignoreNextToken = true;
                                break;
                            default:
                                syntaxError();
                                break;
                            } // end switch
                        } // end if
                    } // end else if
                    else if (addNextToken ) {
                        addNextToken    = false;
                        ignoreNextToken = true;
                        queue.add(nextToken);
                    }
                } // end else if (inIf)
                else if (addNextToken) {
                    addNextToken    = false;
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
                        System.out.println("-IN IF-");
                        ifArgCount = 0;
                        break;
                    case "CLEAR":
                        queue.clear();
                        ignoreNextToken = true;
                        break;
                    default:
                        syntaxError();
                        break;
                    } // end switch
                } // end else
                tokenArray.add(nextToken);
            }// end if(!is space)
        } //end for loop

        finalTokenArray = new String[tokenArray.size()];
        finalTokenArray = tokenArray.toArray(finalTokenArray);
        return finalTokenArray;

    }// end getTokens

    public static boolean evaluateIf(String nextToken){
        // evaluate the parameter of if statement and decide if it should execute or not
        boolean executeIf = false;
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
        default:
            syntaxError();
            break;
        }
        return executeIf;
    }

    public static void syntaxError() {
        // throw a syntax error on the next token
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
