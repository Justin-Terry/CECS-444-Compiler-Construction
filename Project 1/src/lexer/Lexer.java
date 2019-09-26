/*
    Author: Justin Terry
    Email: justin.terry@student.csulb.edu
    Description: This class consists of the functions necessary to generate tokens from an input string.
 */

package lexer;

import input_output.FileInput;
import input_output.LexerInput;

import java.util.ArrayList;
import java.util.Map;

public class Lexer {

    private static Lexer SINGLETON_LEXER;
    private static LexerInput lexerInput;
    private static Map<String, Integer> tokenToIdMap;
    private static Map<Integer, String> idToStringMap;
    private static ArrayList<LexerToken> tokenList;

    private Lexer() {
        lexerInput = LexerInput.getInstance();
        tokenList = new ArrayList<>();
        FileInput fi = new FileInput();
        tokenToIdMap = fi.readFileToMap("lexermapping.txt");
        idToStringMap = fi.getIdToStringMap();
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

    public void parseProgramWithMap() {
        while(lexerInput.hasNext()) {
            StringBuilder sb = new StringBuilder();
            while(true) {
                // Gets full known tokens
                if(lexerInput.peek() != ' ' && !tokenToIdMap.containsKey(Character.toString(lexerInput.peek())) && !tokenToIdMap.containsKey(sb.toString())){
                    if(Character.isDigit(lexerInput.peek()) && sb.length() == 0){
                        handleDigit();
                    } else if(lexerInput.peek() == '\n'){
                        lexerInput.advance();
                    }
                    else {
                        sb.append(lexerInput.advance());
                    }
                } else {
                    if (lexerInput.peek() == ' ') {
                        if(!tokenToIdMap.containsKey(sb.toString()) && sb.length() > 0){
                            showToken(tokenToIdMap.get("id"), sb.toString(), lexerInput.getParsingLine(), lexerInput.getLineIndex()-sb.toString().length());
                        }
                        lexerInput.advance();
                    } else if (tokenToIdMap.containsKey(Character.toString(lexerInput.peek()))) {
                        if(lexerInput.peek() == '"') {
                            if (sb.length() > 0) {
                                showToken(tokenToIdMap.get(sb.toString()), sb.toString(), lexerInput.getParsingLine(), lexerInput.getLineIndex()-sb.toString().length());
                                sb = new StringBuilder();
                            }
                            handleString();
                        }
                        if(lexerInput.peek() == '-'){
                            if(tokenToIdMap.containsKey(sb.toString())) {
                                showToken(tokenToIdMap.get(sb.toString()), sb.toString(), lexerInput.getParsingLine(), lexerInput.getLineIndex() - sb.toString().length());
                                handleMinus(sb);
                            } else {
                                handleMinus(sb);
                            }
                            sb = new StringBuilder();
                        }
                        if(lexerInput.peek() == '/') {
                            if (sb.length() > 0) {
                                showToken(tokenToIdMap.get(sb.toString()), sb.toString(), lexerInput.getParsingLine(), lexerInput.getLineIndex()-sb.toString().length());
                                sb = new StringBuilder();
                            }
                            handleSlash();
                        }
                        else {
                            if (sb.length() > 0) {
                                if(tokenToIdMap.containsKey(sb.toString())) {
                                    showToken(tokenToIdMap.get(sb.toString()), sb.toString(), lexerInput.getParsingLine(), lexerInput.getLineIndex()-sb.toString().length());
                                }else {
                                    if (sb.length() > 0) {
                                        showToken(tokenToIdMap.get("id"), sb.toString(), lexerInput.getParsingLine(), lexerInput.getLineIndex() - sb.toString().length());
                                    }
                                }
                                sb = new StringBuilder();
                            }
                            char symbol = lexerInput.peek();
                            if (symbol != ' ') {
                                if(Character.isDigit(symbol)) {
                                    handleDigit();
                                }else{
                                    symbol = lexerInput.advance();
                                    showToken(tokenToIdMap.get(Character.toString(symbol)), Character.toString(symbol), lexerInput.getParsingLine(), lexerInput.getLineIndex() - sb.toString().length());
                                }
                            }
                        }
                        if(lexerInput.peek() == ' ') {
                            lexerInput.advance();
                        }
                    } else if (tokenToIdMap.containsKey(sb.toString())) {
                        // Handle else
                        lexerInput.advance();
                    }
                    break;
                }
            }
            if(sb.length() > 0) {
                if(tokenToIdMap.containsKey(sb.toString())) {
                    showToken(tokenToIdMap.get(sb.toString()), sb.toString(), lexerInput.getParsingLine(), lexerInput.getLineIndex()-sb.toString().length());
                }
            }
        }
        showToken(tokenToIdMap.get("$"),"",lexerInput.getParsingLine(), lexerInput.getLineIndex());
    }

    public void handleString() {
        lexerInput.advance();
        StringBuilder sb = new StringBuilder();
        while(lexerInput.peek() != '"') {
            sb.append(lexerInput.advance());
        }
        showToken(tokenToIdMap.get("\""), sb.toString(), lexerInput.getParsingLine(), lexerInput.getLineIndex() - sb.length());
        lexerInput.advance();
    }

    public void handleSlash() {
        lexerInput.advance();
        int currentLine = lexerInput.getParsingLine();
        if(lexerInput.peek() == '/') {
            // Comment
            while(lexerInput.hasNext() && lexerInput.getParsingLine() == currentLine){
                lexerInput.advance();
            }
        } else {
            showToken(tokenToIdMap.get("/"), "/", lexerInput.getParsingLine(), lexerInput.getLineIndex());
        }
    }

    public void handleDigit() {
        StringBuilder sb = new StringBuilder();
        while(Character.isDigit(lexerInput.peek()) || lexerInput.peek() == '.') {
            sb.append(lexerInput.advance());
        }
        if(Float.parseFloat(sb.toString()) % 1 != 0) {
            // Number is a float
            showToken(tokenToIdMap.get("myfloat"), sb.toString(), lexerInput.getParsingLine(), lexerInput.getLineIndex() - sb.length(), Float.parseFloat(sb.toString()));
        } else {
            // Number is an int
            showToken(tokenToIdMap.get("myint"), sb.toString(), lexerInput.getParsingLine(), lexerInput.getLineIndex() - sb.length(), Integer.parseInt(sb.toString()));
        }
        if(lexerInput.peek() != ' ' && !tokenToIdMap.containsKey(Character.toString(lexerInput.peek()))){
            showToken(tokenToIdMap.get("myerror"), "error", lexerInput.getParsingLine(), lexerInput.getLineIndex() - sb.length());
            System.out.println("ERROR! Compiler terminating");
            System.exit(0);
        }
    }

    public void handleMinus(StringBuilder sb) {
        String beforeMinus = "";
        int minusIndex = 0;
        if(sb.length() > 0 && Character.isDigit(sb.toString().charAt(0))) {
            // Preceding characters are a number
            int index = 0;
            while(Character.isDigit(sb.toString().charAt(index))){
                beforeMinus += sb.toString().charAt(index);
                index++;
            }
            minusIndex = index;
            if(Float.parseFloat(beforeMinus.toString()) % 1 != 0) {
                // Number is a float
                showToken(tokenToIdMap.get("myfloat"), beforeMinus, lexerInput.getParsingLine(), lexerInput.getLineIndex() - sb.length(), Float.parseFloat(beforeMinus));
            } else {
                // Number is an int
                showToken(tokenToIdMap.get("myint"), beforeMinus, lexerInput.getParsingLine(), lexerInput.getLineIndex() - sb.length(), Integer.parseInt(beforeMinus));
            }
        } else if (sb.length() > 0){
            // Preceding characters are an id
            int index = 0;
            while(index < sb.length() && sb.toString().charAt(index) != ' '){
                beforeMinus += sb.toString().charAt(index);
                index++;
            }
            minusIndex = index;
            showToken(tokenToIdMap.get("id"), beforeMinus, lexerInput.getParsingLine(), lexerInput.getLineIndex() - sb.length());
        }
        showToken(tokenToIdMap.get("-"), "minus", lexerInput.getParsingLine(), lexerInput.getLineIndex() - sb.length());
        lexerInput.advance();
    }

    public void handleComment() {

    }

    public void showToken(int id, String str, int line, int index){
        LexerToken lex = new LexerToken(id, str, line, index);
        System.out.println(lex.toString());
    }

    public void showToken(int id, String str, int line, int index, float f){
        LexerToken lex = new LexerToken(id, str, line, index, f);
        System.out.println(lex.toString());
    }

    public void showToken(int id, String str, int line, int index, int f){
        LexerToken lex = new LexerToken(id, str, line, index, f);
        System.out.println(lex.toString());
    }
}
