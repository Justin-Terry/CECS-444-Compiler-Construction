package application;

import input_output.FileInput;
import lexer.Lexer;
import lexer.LexerToken;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]) {
        Lexer lex = Lexer.getInstance();
        lex.parseFile(" ");
        System.out.println(lex.getLexerResults().toString());


    }
}
