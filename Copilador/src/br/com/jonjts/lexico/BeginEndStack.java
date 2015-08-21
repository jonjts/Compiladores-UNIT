package br.com.jonjts.lexico;

import java.util.Stack;

import javax.swing.text.html.HTMLDocument.Iterator;
import javax.tools.StandardLocation;

import br.com.jonjts.entity.Token;

public class BeginEndStack {
	
	private Stack<Integer> stack;
	
	public BeginEndStack() {
		stack = new Stack<>();
	}
	
	public Token checkSpace(String space, int line, int col){
		if(stack.isEmpty()){
			stack.add(space.length());
			return new Token(Token.T.BEGIN, line, col);
		}else{
			int length = space.length();
			int pop = stack.pop();
			stack.add(pop);
				if(length > pop){
					stack.add(length);
					return new Token(Token.T.BEGIN, line, col);
				}else if(length < pop){
					stack.pop();
					return new Token(Token.T.END, line, col);
				}
			
		}
		return null;
	}
	
	public String popAll(){
		java.util.Iterator<Integer> it = stack.iterator();
		String pop = "";
		while (it.hasNext()) {
			it.next();
			pop += "END\n";
		}
		stack.removeAllElements();
		return pop;
	}

}
