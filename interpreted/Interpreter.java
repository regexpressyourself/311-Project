import java.util.*;

public class Interpreter {
    public static QueueImplementation<Integer> queue = new QueueImplementation<Integer>();
    // the QueueImplementation which will run our commands

    public static String nextToken;   // the next token
    public static int    ifArgCount;  // placeholder for parsing an if statement

    public boolean       verbose;     // print tokens as they arrive
    public String        sourceCode;  // the original Q Language source code
    public char[]        sourceArray; // the original source in a char array
    public char          currentChar; // the current character being parsed


    public Interpreter() {
        // constructor with nothing
        this.sourceCode  = "";
        this.sourceArray = sourceCode.toCharArray();
        this.verbose     = true;
    }
    public Interpreter(String sourceCode) {
        // constructor with source code
        this.sourceCode  = sourceCode;
        this.sourceArray = sourceCode.toCharArray();
        this.verbose     = true;
    }
    public Interpreter(String sourceCode, boolean verbose) {
        // constructor with source code and verbose option set
        this.sourceCode  = sourceCode;
        this.sourceCode  = sourceCode;
        this.sourceArray = sourceCode.toCharArray();
        this.verbose     = verbose;
    }

    public void getTokens() {
        /**
         * getTokens is the meat and potatoes of the interpreted version
         *
         * getTokens will run through every character in the source file, parsing, analyzing,
         * and executing as it goes. It gets a little messy, but it works.
         *
         * The boolean flags help keep track of things like if statements,
         * ignoring semi colons, adding the next token, etc.
         *
         * @param  none, but uses global variables in the class
         * @return none, but runs the appropriate java code
         */

        boolean executeIf       = false; // evaluate expression following if statement
        boolean inIf            = false; // in an if statement
        boolean addNextToken    = false; // triggered when the next token is an element to be added
        boolean ignoreNextToken = false; // flag to ignore semi colons
        ifArgCount              = 0;     // set ifArgCount to 0
        nextToken               = "";

        for (int i = 0; i < sourceCode.length(); i++){
            // for every token, analyze it and add the correct QueueImplementation method
            currentChar = sourceCode.charAt(i);

            if (!(Character.isSpaceChar(currentChar) || currentChar == '\n')) {

                if (Character.isDigit(currentChar)) { // get number
                    nextToken = getNextNumber(sourceArray, i);
                    i        += nextToken.length() - 1;
                } //end isDigit

                else if (Character.isLetter(currentChar)) { // get word
                    nextToken = getNextWord(sourceArray, i);
                    i        += nextToken.length() - 1;
                } //end isLetter

                else if (isSymbol(sourceArray, i)) { // get symbol
                    nextToken = getNextSymbol(sourceArray, i);
                    i        += nextToken.length() - 1;
                }
                else {
                    // if it's not a symbol, word, or number, throw a syntax error
                    nextToken = "" + currentChar;
                    syntaxError();
                }

                // Once the token is found, analyze it

                if (verbose) { // print the token if verbose option is set to true
                    System.out.println("Got token " + nextToken);
                }

                if (ignoreNextToken) {
                    // ignoreNextToken is triggered to ignore a semi colon

                    if (!nextToken.equals(";")){ // check that semi colon is ignored
                        syntaxError();
                    }

                    ignoreNextToken = false;
                    if (inIf) {
                        // reset if related flags if the semi colon follows an if statement
                        ifArgCount = 0;
                        inIf       = false;
                        executeIf  = false;
                        if (verbose) {System.out.println("-OUT IF-");}
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
                        // catch when previous token was "ADD"
                        addNextToken    = false;
                        ignoreNextToken = true;
                        queue.add(nextToken);
                    }
                } // end else if (inIf)
                else if (addNextToken) {
                    // catch when previous token was "ADD"
                    if (Integer.parseInt(nextToken) >= 0 && Integer.parseInt(nextToken) < 11) {
                        addNextToken    = false;
                        ignoreNextToken = true;
                        queue.add(nextToken);
                    }
                    else {
                        System.err.println("Queue elements must be integers from 0 - 10");
                        syntaxError();
                    }
                }
                else {
                    // if no flags are set, analysis starts here
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
                        if (verbose) {System.out.println("-IN IF-");}
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
            }// end if(!is space)
        } //end for loop


    }// end getTokens

    public static boolean evaluateIf(String nextToken){
        /**
         * evaluate the parameter of if statement and decide if it should execute or not
         *
         * When an if statement is reached, evaluateIf() will be called to
         * evaluate the statement in the parenthesis.
         *
         * @param  none, but it uses static variables nextToken, queue, and executeIf.
         * @return whether or not the if statement evaluates to true
         */
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
        /**
         * syntaxError throws an error and exits, displaying the last token analyzed
         *
         * @param  none, but it uses static variables i and tokenArray.
         * @return none
         */

        System.out.println("Syntax error on token: " + nextToken);
        System.exit(1);
    }

    public static boolean isSymbol(char[] charArray, int currentIndex){
        /**
         * isSymbol determines if the current character is a valid Q Language symbol
         *
         * Valid symbols in the Q Programming language are parentheses and semi-colons
         *
         * @param  charArray    - the array of characters in the source code
         * @param  currentIndex - the placeholder for where the parser is in the array
         * @return true if the character is a valid symbol, false otherwise
         */

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
        /**
         * getNextSymbol parses the code to find the next symbol and returns it.
         *
         * Valid symbols in the Q Programming language are parentheses and semi-colons
         *
         * @param  charArray    - the array of characters in the source code
         * @param  currentIndex - the placeholder for where the parser is in the array
         * @return the symbol as a string
         */

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
        /**
         * getNextWord parses the code to find the next word and returns it.
         *
         * @param  charArray    - the array of characters in the source code
         * @param  currentIndex - the placeholder for where the parser is in the array
         * @return the word as a string
         */

        String nextWord = "";
        while (Character.isLetter(charArray[currentIndex]) ||
               Character.isDigit (charArray[currentIndex])  ||
               charArray[currentIndex] == '_'){
            nextWord += charArray[currentIndex++];
        }
        return nextWord;

    }

    public static String getNextNumber(char[] charArray, int currentIndex){
        /**
         * getNextNumber parses the code to find the next number and returns it.
         *
         * @param  charArray    - the array of characters in the source code
         * @param  currentIndex - the placeholder for where the parser is in the array
         * @return the number as a string
         */

        String nextNum = "";
        while (Character.isDigit(charArray[currentIndex])){
            nextNum += charArray[currentIndex++];
        }
        return nextNum;

    } //end getNexNumber

}
