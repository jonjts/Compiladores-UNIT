
package br.com.jonjts.lexico;

import java_cup.runtime.*;
import java.io.IOException;
import br.com.jonjts.entity.*;

// import br.com.jonjts.lexico.ScannerSym;
// import static br.com.jonjts.lexico.ScannerSym.*;

%%

%class Scanner
%type Token

%unicode
%public
%line
%column 

%{

	private BeginEndStack bes = new BeginEndStack();
	private Token token(Token.T type){
		return new Token(type, yyline, yycolumn);
	}
	 
	private Token token(Token.T type, Object val){
		return new Token(type, val, yyline, yycolumn);
	}
%}

ESPACOS = " "+
NOT_ESPACO = !{ESPACOS}

ALPHA = [a-zA-Z] 
NOT_ALPHA = !{ALPHA}
DIG = [0-9]
ID = {ALPHA}({ALPHA}|{DIG}|_)*
INTEGER = {DIG}+
FLOAT = {DIG}+ "." {DIG}* | {DIG}* "." {DIG}+

ALPHA_NUMERIC = ({ALPHA} | {DIG} | {FLOAT}| " ")+
COMENTARIO = "{" {ALPHA_NUMERIC} "}"

%state CHECK_BEGIN_END

%%
<YYINITIAL>{ 

\n{ESPACOS}		{ return bes.checkSpace(yytext(),yyline, yycolumn);	}

\n			{ return bes.popAll(yyline, yycolumn); }

int 		{ return token(Token.T.INT, yytext());}

if			{ return token(Token.T.IF, yytext()); }

else		{ return token(Token.T.ELSE, yytext()); }

read		{ return token(Token.T.READ, yytext()); }

write		{ return token(Token.T.WRITE, yytext()); }

elif		{ return token(Token.T.ELIF, yytext()); }

while		{ return token(Token.T.WHILE, yytext()); }

bool		{ return token(Token.T.BOOL, yytext()); }

void		{ return token(Token.T.VOID, yytext()); }

and			{ return token(Token.T.AND, yytext()); }

or			{ return token(Token.T.OR, yytext()); }

not			{ return token(Token.T.NOT, yytext()); }

pass		{ return token(Token.T.PASS, yytext()); }

true		{ return token(Token.T.TRUE, yytext()); }

false		{ return token(Token.T.FALSE, yytext()); }

{ID}		{ return token(Token.T.ID, yytext()); }

{INTEGER}	{ return token(Token.T.INTEGER, yytext()); }

{FLOAT}		{ return token(Token.T.FLOAT, yytext()); }

{COMENTARIO} { return token(Token.T.COMENTARIO, yytext()); } 

"("			{ return token(Token.T.ABRE_PARENTESE, yytext()); }

")"			{ return token(Token.T.FECHA_PARENTESE, yytext()); }
 
:			{ return token(Token.T.DOIS_PONTOS, yytext()); }

"<"			{ return token(Token.T.MENOR_QUE, yytext()); }

">"			{ return token(Token.T.MAIOR_QUE, yytext()); }

:=			{ return token(Token.T.ATRIBUICAO, yytext()); }

"+"			{ return token(Token.T.MAIS, yytext()); }

"-"			{ return token(Token.T.MENOS, yytext()); }

"*"			{ return token(Token.T.VEZES, yytext()); }

"/"			{ return token(Token.T.DIVIDIR, yytext()); }

","			{ return token(Token.T.VIRGULA, yytext()); }

"="			{ return token(Token.T.IGUALDADE, yytext()); }
 
[ \t\r]+	{ /* do nothing */ }

<<EOF>>		{ return bes.popAll(yyline, yycolumn); }

.           { System.err.println("Illegal character: ' " + yytext() + " '"); }
}

