Project 1: Lexer
CECS 444 Sec 01 5191 Compiler Construction

Team JKT
Members: Justin Terry

The objective of this assignment is to write a lexer for the A5 language lexicon: its tokens (i.e., legal
“words”). The lexer transforms an A5 high-level program sequence of characters into a list of tokens for that
program (in a special format). For convenience, this lexer will take input from standard-input (stdin) and send
output to standard-output (stdout). Coding is in Java.

Contents:

	src
	├── application
	│ 	└── Main.java
	├── input_output
	│ 	├── FileInput.java
	│ 	└── LexerInput.java
	├── lexer
	│	├── Lexer.java
	│ 	└── LexerToken.java
	│ 
	lexermapping.txt
	
External Requirements: 

	None

Setup: 

	Ensure the lexermapping.txt is in the same directory as the src folder.

Sample Invocation:

	>> prog main { // Find the circumference of a circle.
	>> var pi = 007;
	>> print( "Input radius> " );
	>> var rx = input ( float );
	>> var circum = 2 * pi * rx;
	>> print( "Circumf= ", circum );
	>> }
	>> 

Sample Results:

	(Tok: 10 lin= 1 str= "prog")
	(Tok: 11 lin= 1 str= "main")
	(Tok: 33 lin= 1 str= "{")
	(Tok: 26 lin= 2 str= "var")
	(Tok: 2 lin= 2 str= "pi")
	(Tok: 45 lin= 2 str= "=")
	(Tok: 3 lin= 2 str= "007" int= 7)
	(Tok: 7 lin= 2 str= ";")
	(Tok: 23 lin= 3 str= "print")
	(Tok: 37 lin= 3 str= "(")
	(Tok: 5 lin= 3 str= "Input radius> ")
	(Tok: 38 lin= 3 str= ")")
	(Tok: 7 lin= 3 str= ";")
	(Tok: 26 lin= 4 str= "var")
	(Tok: 2 lin= 4 str= "rx")
	(Tok: 45 lin= 4 str= "=")
	(Tok: 22 lin= 4 str= "input")
	(Tok: 37 lin= 4 str= "(")
	(Tok: 15 lin= 4 str= "float")
	(Tok: 38 lin= 4 str= ")")
	(Tok: 7 lin= 4 str= ";")
	(Tok: 26 lin= 5 str= "var")
	(Tok: 2 lin= 5 str= "circum")
	(Tok: 45 lin= 5 str= "=")
	(Tok: 3 lin= 5 str= "2" int= 2)
	(Tok: 41 lin= 5 str= "*")
	(Tok: 2 lin= 5 str= "pi")
	(Tok: 41 lin= 5 str= "*")
	(Tok: 2 lin= 5 str= "rx")
	(Tok: 7 lin= 5 str= ";")
	(Tok: 23 lin= 6 str= "print")
	(Tok: 37 lin= 6 str= "(")
	(Tok: 5 lin= 6 str= "Circumf= ")
	(Tok: 6 lin= 6 str= ",")
	(Tok: 2 lin= 6 str= "circum")
	(Tok: 38 lin= 6 str= ")")
	(Tok: 7 lin= 6 str= ";")
	(Tok: 34 lin= 7 str= "}")
	(Tok: 0 lin= 8 str= "")

	Process finished with exit code 0

Features:
	
	This program will provide tokens when given a sentence of legal words in the A5 language.
	
Bugs:
	
	At this time negative integers are split into two tokens, a minus token and an integer token. 
	In later projects rules will be written that create a negative integer value from these two tokens.
	

	