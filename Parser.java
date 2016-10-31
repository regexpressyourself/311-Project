import java.util.*;

public class Parser {
    public String sourceCode= "";
    public char[] sourceArray;

    public Parser(String sourceCode) {
        this.sourceCode = sourceCode;
        this.sourceArray = sourceCode.toCharArray();
    }

    public String[] getTokens() {


        char currentChar;
        List<String> tokenArray = new ArrayList<String>();
        String nextToken = "";


        for (int i = 0; i < sourceCode.length(); i++){
            currentChar = sourceCode.charAt(i);
            if (!(Character.isSpaceChar(currentChar))) {

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
                tokenArray.add(nextToken);
            }// end if(!is space)
        } //end for loop

        String finalTokenArray[] = new String[tokenArray.size()];
        finalTokenArray = tokenArray.toArray(finalTokenArray);
        return finalTokenArray;

    }// end getTokens

    public static boolean isSymbol(char[] charArray, int currentIndex){
        switch (charArray[currentIndex]) {
            case '(':
            case ')':
            case ';':
            case '{':
            case '}':
            case '%':
            case '+':
            case '-':
            case '/':
            case '=':
            case '<':
            case '>':
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
            case '{':
            case '}':
                returnString =  String.valueOf(charArray[currentIndex]);
                break;
            case '%':
            case '/':
            case '+':
            case '-':
            case '=':
            case '<':
            case '>':
                returnString =  String.valueOf(charArray[currentIndex]);

                if (isSymbol(charArray, currentIndex+1)                   &&(
                        String.valueOf(charArray[currentIndex+1]).equals("=") ||
                                String.valueOf(charArray[currentIndex+1]).equals("+") ||
                                String.valueOf(charArray[currentIndex+1]).equals("-") ))
                {

                    returnString +=  String.valueOf(charArray[currentIndex+1]);
                }

                break;
            default:
                break;
        }
        return returnString;
    }
    public static String getNextWord(char[] charArray, int currentIndex){
        String nextWord = "";
        while (Character.isLetter(charArray[currentIndex]) ||
                Character.isDigit(charArray[currentIndex])){
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
