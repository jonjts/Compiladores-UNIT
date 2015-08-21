package br.com.jonjts;

import java.io.FileReader;
import java.io.Reader;

import br.com.jonjts.entity.Token;
import br.com.jonjts.lexico.Scanner;

public class Main {

	public static void main(String[] args) {
		try
		{
		Reader input = new FileReader("C:\\Users\\Jonas\\Desktop\\ler.txt");
		Scanner scanner = new Scanner(input);
		Token token;
		do
		{
		token = scanner.yylex();
		System.out.println(token);
		}
		while (token.type != token.type.EOF);
		}
		catch (Exception e)
		{
		e.printStackTrace();
		}

	}

}
