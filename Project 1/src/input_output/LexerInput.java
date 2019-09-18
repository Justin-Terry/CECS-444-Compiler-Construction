package input_output;

public class LexerInput {
    private static LexerInput lexerInput;
    private String programString;
    private static int parsingIndex;
    private static int parsingLine;


    private LexerInput() {
        FileInput mFI = new FileInput();
        this.programString = mFI.readFileToString();
        System.out.println("LEXERINPUT: " + programString);
        this.parsingIndex = -1;
        this.parsingLine = 0;
    }

    public static LexerInput getInstance() {
        if(lexerInput == null) {
            lexerInput = new LexerInput();
        }
        return lexerInput;
    }

    public char peek() {
        if(this.hasNext() && this.programString.charAt(this.parsingIndex + 1) != ' ' && this.programString.charAt(this.parsingIndex + 1) != '\n'){
            return this.programString.charAt(parsingIndex + 1);
        } else {
            return ' ';
        }
    }

    public boolean hasNext() {
        if (this.programString.length() > this.parsingIndex + 1) {
            return true;
        }
        return false;
    }

    public char advance() {
        if (this.programString.length() > this.parsingIndex + 1) {
            this.parsingIndex++;
            if(this.programString.charAt(this.parsingIndex) == '\n'){
                parsingLine++;
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
