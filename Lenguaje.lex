package Ejercicio4;
import java_cup.runtime.*;
%%
%{
/*-*
* funciones y variables
*/
private void imprimir(String descripcion, String lexema) {
System.out.println(lexema + " :: " + descripcion);
}
%}
/*-*
* Información sobre la clase generada
*/
%public
%class AnalizadorLexico
%type void
/*-*
* Ajustes regulares
*/
BLANCO = [\n| |\t]
ID = [_|a-z|A-Z][a-z|A-Z|0-9|_]*
ENTERO = 0|[1-9][0-9]*
PUNTO_FLOTANTE = [0-9][0-9]*"."[0-9]+
OPERADORES_MATEMATICOS = ("+" | "-" | "*" | "/")
%%
"if" { imprimir("Intrucción if", yytext()); }
"then" { imprimir("Intrucción then", yytext()); }
{BLANCO} { imprimir("Blanco", yytext()); }
{ID} { imprimir("Identificador", yytext()); }
{ENTERO} { imprimir("Numero entero", yytext()); }
{PUNTO_FLOTANTE} { imprimir("Punto flotante", yytext()); }
{OPERADORES_MATEMATICOS} { imprimir("Operadores matemáticos", yytext()); }
"==" { imprimir("Operador igualdade", yytext()); }
. { throw new RuntimeException("Caracter inválido \""+yytext() +
"\" en la línea "+yyline+", columna "+yycolumn); }
