/*
    Author: Justin Terry
    Email: justin.terry@student.csulb.edu
    Description: This class just gets and instance of the lexer and starts the parsing function.
 */

package application;

import input_output.FileInput;
import lexer.Lexer;
import lexer.LexerToken;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]) {
        Lexer lex = Lexer.getInstance();
        lex.parseProgramWithMap();


    }
}
