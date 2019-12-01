package com.dankook.parser;

import com.dankook.Token.OperatorType;
import com.dankook.Token.SeparatorType;
import com.dankook.Token.Token;
import com.dankook.Token.UserDefinedType;
import com.dankook.scanner.MyScannerImpl;

import java.util.Stack;

public class MyParserImpl implements MyParser {
	
	//상태 값 정의
	public static final int SUCCESS = 1;
	public static final int FAILURE = 0;
	
	private int status = 1;
	
	private MyScannerImpl myScanner = new MyScannerImpl();
	
	//연산 시 stack 사용
	private Stack<Integer> stack = new Stack<>();
	
	private Token token;
	
	public void start(String input) {
		myScanner = new MyScannerImpl();
		status = 1;
		token = null;
		
		myScanner.tokenize(input);
		token = myScanner.nextToken();
		statement();
	}
	
	@Override
	public boolean statement() {
		
		int id;
		
		switch(token.getTokenNumber()) {
			case UserDefinedType.IDENTIFIER:
				
				id = token.getTokenValue();
				token = myScanner.nextToken();
				
				checkToken(OperatorType.ASSIGN.getTokenNumber());
				
				if(!isStatusSuccess()) {
					break;
				}
				token = myScanner.nextToken();
				expression();
				UserDefinedType.setIdentifierValue(id, pop());
				break;
			
			case OperatorType.PRINT_TOKEN_NUMBER:
				
				token = myScanner.nextToken();
				expression();
				
				checkToken(SeparatorType.SEMICOLON.getTokenNumber());
				if(!isStatusSuccess()) {
					break;
				}
				System.out.println(" " + pop());
				return true;
			
			default:
				status = FAILURE;
		}
		
		checkToken(SeparatorType.SEMICOLON.getTokenNumber());
		
		return false;
	}
	
	@Override
	public void expression() {
		int tokenNumber;
		
		term();
		while(OperatorType.PLUS.equal(token) || OperatorType.MINUS.equal(token)) {
			tokenNumber = token.getTokenNumber();
			token = myScanner.nextToken();
			term();
			operate(tokenNumber);
		}
	}
	
	@Override
	public void term() {
		int tokenNumber;
		
		factor();
		while(OperatorType.MULTIPLY.equal(token) || OperatorType.DIVIDE.equal(token) || OperatorType.EXP.equal(token)) {
			tokenNumber = token.getTokenNumber();
			token = myScanner.nextToken();
			factor();
			operate(tokenNumber);
		}
	}
	
	@Override
	public void factor() {
		
		switch(token.getTokenNumber()) {
			case UserDefinedType.IDENTIFIER:
			case UserDefinedType.CONSTANT:
				push(UserDefinedType.getIdentifierValue(token.getTokenValue()));
				break;
			
			case SeparatorType.LPARENTHESES_TOKEN_NUMBER:
				token = myScanner.nextToken();
				expression();
				checkToken(SeparatorType.RPARENTHESES.getTokenNumber());
				break;
			
			default:
				status = FAILURE;
		}
		
		token = myScanner.nextToken();
	}
	
	@Override
	public void operate(int tokenNumber) {
		int d1 = pop(), d2 = pop();
		
		if(tokenNumber == OperatorType.DIVIDE.getTokenNumber() && d2 == 0) {
			System.out.println("division by 0");
			return;
		}
		
		switch(tokenNumber) {
			case OperatorType.PLUS_TOKEN_NUMBER:
				push(d1 + d2);
				break;
			case OperatorType.MINUS_TOKEN_NUMBER:
				push(d1 - d2);
				break;
			case OperatorType.MULTIPLY_TOKEN_NUMBER:
				push(d1 * d2);
				break;
			case OperatorType.DIVIDE_TOKEN_NUMBER:
				push(d1 / d2);
				break;
			case OperatorType.EXP_TOKEN_NUMBER:
				push((int) Math.pow(d1, d2));
				break;
		}
	}
	
	@Override
	public void push(int value) {
	    if(!isStatusSuccess()) {
	        return;
        }
		stack.push(value);
	}
	
	@Override
	public int pop() {
        if(!isStatusSuccess()) {
            System.out.println("1 반환");
            return 1;
        }
		if(stack.isEmpty()) {
			System.out.println("========stack is empty========");
			throw new IndexOutOfBoundsException();
		}
		return stack.pop();
	}
	
	@Override
	public void checkToken(int tokenNumber) {
		if(token.getTokenNumber() != tokenNumber) {
			status = FAILURE;
		}
	}
	
	@Override
	public boolean isStatusSuccess() {
		return status == SUCCESS;
	}
}
