package Ejercicio4;

import java.io.File;

public class Generador {
	public static void main(String[] args) {
		String ruta = "C://Users//Usuario//eclipse-workspace//AnalizadorLexico//src//Ejercicio4//";
		String archivo = ruta + "Lexer.flex";
		
		File file = new File(archivo);
		jflex.Main.generate(file);
		
	}
}
