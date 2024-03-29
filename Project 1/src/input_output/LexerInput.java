/*
    Author: Justin Terry
    Email: justin.terry@student.csulb.edu
    Description: This class provides the lexer with an input and functions like peek and advance.
 */

package input_output;

public class LexerInput {
    private static LexerInput lexerInput;
    private String programString;
    private static int parsingIndex;
    private static int parsingLine;
    private static int lineIndex;


    private LexerInput() {
        FileInput mFI = new FileInput();
        //this.programString = mFI.readFileUsingScanner();
        this.programString = mFI.getScannerInput();
        this.parsingIndex = -1;
        this.lineIndex = 0;
        this.parsingLine = 0;
    }

    public static LexerInput getInstance() {
        if(lexerInput == null) {
            lexerInput = new LexerInput();
        }
        return lexerInput;
    }

    public int getLineIndex(){
        return this.lineIndex;
    }

    // Returns next character
    public char peek() {
        if(this.hasNext() && this.programString.charAt(this.parsingIndex + 1) != ' ' && this.programString.charAt(this.parsingIndex + 1) != '\n'){
            return this.programString.charAt(parsingIndex + 1);
        } else {
            return ' ';
        }
    }

    // Returns true if there is a next character
    public boolean hasNext() {
        if (this.programString.length() > this.parsingIndex + 1) {
            return true;
        }
        return false;
    }

    // Returns the next character and advances the pointer to the next character
    public char advance() {
        if (this.programString.length() > this.parsingIndex + 1) {
            this.parsingIndex++;
            this.lineIndex++;
            if(this.programString.charAt(this.parsingIndex) == '\n'){
                parsingLine++;
                lineIndex = 0;
            }
            return this.programString.charAt(this.parsingIndex);
        }
        return ' ';
    }

    public int getParsingIndex() {
        return this.parsingIndex;
    }

    public int getParsingLine() {
        return this.parsingLine;
    }



}
