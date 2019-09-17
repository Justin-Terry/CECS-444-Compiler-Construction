package lexer;

import input_output.FileInput;

import java.util.ArrayList;
import java.util.Map;

public class Lexer {

    private static Lexer SINGLETON_LEXER;
    private static String programString;
    private static Map<String, Integer> tokenMap;
    private static ArrayList<LexerToken> tokenList;
    private static int parsingIndex;
    private static int parsingLine = 1;

    private Lexer() {
        tokenList = new ArrayList<>();
        FileInput fi = new FileInput();
        tokenMap = fi.readFileToMap("lexermapping.txt");
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

    private char peek() {
        if(this.hasNext() && this.programString.charAt(this.parsingIndex + 1) != ' '){
            return this.programString.charAt(parsingIndex + 1);
        } else {
            return ' ';
        }
    }

    private boolean hasNext() {
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

    public void parseProgramWithMap() {
        FileInput mFI = new FileInput();
        this.programString = mFI.getFileContents();
        parsingIndex = -1;

        while(this.hasNext()) {
            StringBuilder sb = new StringBuilder();
            while(this.hasNext()) {
                if(this.peek() != ' '){
                    sb.append(this.advance());
                } else{
                    this.advance();
                    break;
                }
            }
            if(sb.length() > 0) {
                if(tokenMap.containsKey(sb.toString())){
                    LexerToken token = new LexerToken(tokenMap.get(sb.toString()), sb.toString(), parsingLine, parsingIndex - sb.toString().length());
                    System.out.println(token.toString());
                }
            }
        }

    }

    private void parseProgram() {
        parsingIndex = 0;
        while (true) {
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

//    private LexerToken fP() {
//        char currentChar;
//        if (this.peek()) {
//            currentChar = this.advance();
//            switch (currentChar) {
//                case 'r':
//                    //pr
//                    if (this.peek()) {
//                        currentChar = this.advance();
//                        switch (currentChar) {
//                            case 'i':
//                                //pri
//                                if (this.peek()) {
//                                    currentChar = this.advance();
//                                    switch (currentChar) {
//                                        case 'n':
//                                            //prin
//                                            if (this.peek()) {
//                                                currentChar = this.advance();
//                                                switch (currentChar) {
//                                                    //print
//                                                    case 't':
//                                                        currentChar = this.advance();
//                                                        switch (currentChar) {
//                                                            case ' ':
//                                                                return new LexerToken(26, "print", this.parsingLine, this.parsingIndex - 4);
//                                                            default:
//                                                                currentChar = this.advance();
//                                                                return new LexerToken(99, "error", this.parsingLine, this.parsingIndex - 4);
//                                                        }
//                                                    default:
//                                                        return new LexerToken(99, "error", this.parsingLine, this.parsingIndex - 3);
//                                                }
//                                            }
//                                        default:
//                                            return new LexerToken(99, "error", this.parsingLine, this.parsingIndex - 3);
//                                    }
//                                }
//                            case 'o':
//                                //pro
//                                if (this.peek()) {
//                                    currentChar = this.advance();
//                                    switch (currentChar) {
//                                        case 'g':
//                                            currentChar = this.advance();
//                                            switch (currentChar) {
//                                                case ' ':
//                                                    return new LexerToken(10, "prog", this.parsingLine, this.parsingIndex - 3);
//                                                default:
//                                                    currentChar = this.advance();
//                                                    return new LexerToken(99, "error", this.parsingLine, this.parsingIndex - 3);
//                                            }
//                                        default:
//                                            return new LexerToken(99, "error", this.parsingLine, this.parsingIndex - 3);
//                                    }
//                                }
//                            default:
//                                return new LexerToken(99, "error", this.parsingLine, this.parsingIndex - 3);
//                        }
//                    }
//                case 'a':
//                    //pa
//                    break;
//                default:
//                    return new LexerToken(99, "error", this.parsingLine, this.parsingIndex);
//            }
//        }
//        return new LexerToken();
//    }
}
