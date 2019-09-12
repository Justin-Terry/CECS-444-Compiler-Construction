package lexer;

import input_output.FileInput;

import java.util.ArrayList;

public class Lexer {

    private static Lexer SINGLETON_LEXER;
    private static String programString;
    private static ArrayList<LexerToken> tokenList;
    private static int parsingIndex;
    private static int parsingLine = 1;

    private Lexer() {
        tokenList = new ArrayList<>();
    }

    public static Lexer getInstance() {
        if (SINGLETON_LEXER == null) {
            SINGLETON_LEXER = new Lexer();
        }
        return SINGLETON_LEXER;
    }

    public ArrayList<LexerToken> getLexerResults() {
        return tokenList;
    }

    private boolean peek() {
        if (this.programString.length() > this.parsingIndex + 1) {
            return true;
        }
        return false;
    }

    private char advance() {
        if (this.programString.length() > this.parsingIndex + 1) {
            this.parsingIndex++;
            return this.programString.charAt(this.parsingIndex);
        }
        return ' ';
    }

    public void parseFile(String filepath) {
        FileInput mFI = new FileInput();
        this.programString = mFI.getFileContents();
        parseProgram();
    }

    private void parseProgram() {
        parsingIndex = 0;
        while (this.peek()) {
            if (this.programString.charAt(parsingIndex) != ' ') {
                char currentChar = this.programString.charAt(parsingIndex);
                switch (currentChar) {
                    case 'a':
                        break;
                    case 'b':
                        break;
                    case 'c':
                        break;
                    case 'd':
                        break;
                    case 'e':
                        break;
                    case 'f':
                        break;
                    case 'i':
                        break;
                    case 'm':
                        break;
                    case 'n':
                        break;
                    case 'p':
                        tokenList.add(this.fP());
                        break;
                    case 'r':
                        break;
                    case 's':
                        break;
                    case 'v':
                        break;
                    case 'w':
                        break;
                    default:
                        tokenList.add(new LexerToken(99, "error", this.parsingLine, this.parsingIndex - 3));
                        break;
                }
            } else {
                this.advance();
            }
        }
    }

    private LexerToken fP() {
        char currentChar;
        if (this.peek()) {
            currentChar = this.advance();
            switch (currentChar) {
                case 'r':
                    //pr
                    if (this.peek()) {
                        currentChar = this.advance();
                        switch (currentChar) {
                            case 'i':
                                //pri
                                break;
                            case 'o':
                                //pro
                                if (this.peek()) {
                                    currentChar = this.advance();
                                    switch (currentChar) {
                                        case 'g':
                                            currentChar = this.advance();
                                            switch (currentChar) {
                                                case ' ':
                                                    return new LexerToken(10, "prog", this.parsingLine, this.parsingIndex - 3);
                                                default:
                                                    return new LexerToken(99, "error", this.parsingLine, this.parsingIndex - 3);
                                            }
                                        default:
                                            return new LexerToken(99, "error", this.parsingLine, this.parsingIndex - 3);

                                    }
                                }
                            default:
                                return new LexerToken(10, "prog", this.parsingLine, this.parsingIndex - 2);
                        }
                    }
                case 'a':
                    //pa
                    break;
                default:
                    return new LexerToken(99, "error", this.parsingLine, this.parsingIndex);
            }
        }
        return new LexerToken();
    }
}
