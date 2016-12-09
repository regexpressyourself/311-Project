import java.util.*;

public class Parser {
    /**
     * Parser takes in a string of Q Language code and returns an array of tokens.
     */

    public String       sourceCode;        // the original string of Q code
    public char[]       sourceArray;       // the original code stored as a char array
    public char         currentChar;       // the current character being parsed
    public List<String> tokenArray;        // working, generic, mutable copy of the token array
    public String       nextToken;         // the next token to be added to the array
    public String       finalTokenArray[]; // the returned token array

    public Parser() {
        // no code constructor
        this.sourceCode = "";
        this.sourceArray = sourceCode.toCharArray();
    }
    public Parser(String sourceCode) {
        // constructor with code
        this.sourceCode = sourceCode;
        this.sourceArray = sourceCode.toCharArray();
    }

    public String[] getTokens() {
        /**
         * getTokens is the meat and potatoes of Parser
         *
         * getTokens uses the other methods to extract each token and put 
         * it in the token array.
         *
         * @param  none, but it uses the global Parser variables
         * @return the token array
         */

        tokenArray = new ArrayList<String>(); // dynamic array
        nextToken  = "";                      // String for our next token

        for (int i = 0; i < sourceCode.length(); i++){
            currentChar = sourceCode.charAt(i);
            // go through each character of the source code and find the tokens

            if (!(Character.isSpaceChar(currentChar) || currentChar == '\n')) { // check for space or newline

                if (Character.isDigit(currentChar)) { // get numbers
                    nextToken = getNextNumber(sourceArray, i);
                    i += nextToken.length() - 1;
                } //end isDigit

                else if (Character.isLetter(currentChar)) { // get words
                    nextToken = getNextWord(sourceArray, i);
                    i += nextToken.length() - 1;
                } //end isLetter

                else if (isSymbol(sourceArray, i)) { // get symbols
                    nextToken = getNextSymbol(sourceArray, i);
                    i += nextToken.length() - 1;
                }
                tokenArray.add(nextToken); // add the token
            }// end if(!is space)
        } //end for loop

        finalTokenArray = new String[tokenArray.size()]; // String[] version of the List tokenArray
        finalTokenArray = tokenArray.toArray(finalTokenArray);
        return finalTokenArray;

    }// end getTokens

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
