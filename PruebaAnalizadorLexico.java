package Ejercicio4;

import java.io.IOException;
import java.io.StringReader;

public class PruebaAnalizadorLexico {

	public static void main(String[] args) throws java.io.IOException {
		// TODO Auto-generated method stub
		String expresion = "2+3";
		
		Lexer lexico = new Lexer(new StringReader(expresion));
		lexico.yylex();
	}

}
