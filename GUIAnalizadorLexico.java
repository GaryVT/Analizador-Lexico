package Ejercicio4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class GUIAnalizadorLexico extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldEntrada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAnalizadorLexico frame = new GUIAnalizadorLexico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIAnalizadorLexico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtFieldEntrada = new JTextField();
		txtFieldEntrada.setBounds(51, 28, 374, 148);
		contentPane.add(txtFieldEntrada);
		txtFieldEntrada.setColumns(10);
		
		JButton btnAnalizar = new JButton("Analizar");
		btnAnalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAnalizar.setBounds(478, 39, 106, 47);
		contentPane.add(btnAnalizar);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(51, 187, 536, 237);
		contentPane.add(textArea);
		
		btnAnalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Primero vamos a convertir la entrada del JTextField a un documento de texto, posteriormente analizarlo con el lexer que creamos 
				File archivo = new File("archivo.txt");
				PrintWriter escribir;
				try {
					escribir = new PrintWriter(archivo);
					escribir.print(txtFieldEntrada.getText());
					escribir.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Empezaremos a utilizar el analizador lexico
				
				try {//No se sabe si el archivo esta o no en la computadora
					Reader lector = new BufferedReader(new FileReader("archivo.txt"));
					Lexer lexer = new Lexer(lector);
					String resultado ="";//Sirve como resultado de toda la cadena que vamos a analizar
					while(true) {
						Tokens tokens = lexer.yylex();
						if(tokens == null) {
							resultado += "FIN";
							textArea.setText(resultado);
							return;
						}
						switch(tokens) {
							case ERROR:
								resultado +="Simbolo no definido\n";
								break;
							case Identificador:
							case Numero:
							case Reservadas:
								resultado += lexer.lexeme + ": Es un " + tokens + ".\n";
								break;
							default:
								resultado += "Token: " + tokens + ".\n";
								break;
						}
						
						
						
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
		
		
		
		
	}
}
