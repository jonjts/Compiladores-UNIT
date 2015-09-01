package br.com.jonjts.entity;

import java.awt.HeadlessException;
import java.util.Formatter;
import java.util.Stack;

public class Token {

	public enum T {
		NADA { public String toString(){return "";}},
		COMENTARIO {public String toString(){return "COMENTARIO";}},
		BEGIN {public String toString(){return "BEGIN";}},
		END {public String toString(){return "END";}},
		DOIS_PONTOS {public String toString(){return "DOIS PONTOS";}},
		VEZES {public String toString(){return "VEZES";}},
		DIVIDIR {public String toString(){return "DIVIDIR";}},
		MENOS {public String toString(){return "MENOS";}},
		MAIS {public String toString(){return "MAIS";}},
		ATRIBUICAO {public String toString() {return "ATRINUIÇÃO";}},
		MAIOR_QUE {public String toString(){return "MAIOR_QUE";}},
		MENOR_QUE {public String toString(){return "MENOR_QUE";}},
		IGUALDADE {public String toString(){return "IGUALDAE";}},
		VIRGULA {public String toString(){return "VIRGULA";}},
		FECHA_PARENTESE {public String toString(){return "FECHA_PARENTESE";}},
		ABRE_PARENTESE {public String toString(){return "ABRE_PARENTESE";}},
		READ { public String toString(){return "READ";}},
		WRITE{public String toString(){return "WRITE";}},
		IF {public String toString() {return " IF ";}},
		ELSE {public String toString() {return "ELSE";}},
		WHILE {public String toString() {return "WHILE";}},
		BOOL {public String toString() {return "BOOL";}},
		VOID {public String toString() {return "VOID";}},
		AND {public String toString() {return "AND";}},
		OR {public String toString() {return "OR";}},
		ELIF {public String toString() {return "ELIF";}},
		NOT {public String toString() {return "NOT";}},
		PASS {public String toString() {return "PASS";}},
		TRUE {public String toString() {return "TRUE";}},
		FALSE {public String toString() {return "FALSE";}},
		ID {public String toString() {return " ID";}},
		INT {public String toString() {return " INT ";}},
		INTEGER {public String toString() {return " INTEGER ";}},
		FLOAT {public String toString() {return "FLOAT";}},
		STR {public String toString() {return "STR";}},
		EOF {public String toString() {return "EOF";}}
	}

	public T type;
	public Object val;
	public int line;
	public int col;

	public Token(T type, int line, int col) {
		this.type = type;
		this.line = line;
		this.col = col;
	}

	public Token(T type, Object val, int line, int col) {
		this.type = type;
		this.val = val;
		this.line = line;
		this.col = col;
	}

	public String toString() {
		if(line == -1 || col == -1){
			return "";
		}
		Formatter out = new Formatter();
		out.format(" [%3d,%3d] %s", line, col, type);
		if (val != null)
			out.format(" [%s] ", val);
		return out.toString();
	}

}
