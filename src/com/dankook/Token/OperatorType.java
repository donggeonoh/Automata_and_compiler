package com.dankook.Token;

public enum OperatorType {
	
	PLUS(6, "+"),
    MINUS(7, "-"),
    MULTIPLY(8, "*"),
    DIVIDE(9, "/"),
    ASSIGN(10, "="),
    PRINT(11, "?"),
	EXP(5, "^");
	
	public static final int PLUS_TOKEN_NUMBER = 6;
	public static final int MINUS_TOKEN_NUMBER = 7;
	public static final int MULTIPLY_TOKEN_NUMBER = 8;
	public static final int DIVIDE_TOKEN_NUMBER = 9;
	public static final int EXP_TOKEN_NUMBER = 12;
	
	public static final int PRINT_TOKEN_NUMBER = 11;
	
	private final int tokenNumber;
	private final String tokenValue;
	
	OperatorType(final int tokenNumber, final String tokenValue) {
		this.tokenNumber = tokenNumber;
		this.tokenValue = tokenValue;
	}
	
	public static String isOperatorInString(String target) {
		switch(target.charAt(0)) {
			case '+':
				return PLUS.tokenValue;
			case '-':
				return MINUS.tokenValue;
			case '*':
				return MULTIPLY.tokenValue;
			case '/':
				return DIVIDE.tokenValue;
			case '=':
				return ASSIGN.tokenValue;
			case '?':
				return PRINT.tokenValue;
			case '^':
				return EXP.tokenValue;
		}
		return "";
	}
	
	public static boolean isOperatorType(String target) {
		for(OperatorType type : OperatorType.values()) {
			if(type.tokenValue.equals(target)) {
				return true;
			}
		}
		return false;
	}
	
	public static OperatorType getOperatorType(String target) {
		for(OperatorType type : OperatorType.values()) {
			if(type.tokenValue.equals(target)) {
				return type;
			}
		}
		return null;
	}
	
	public boolean equal(Token token) {
		return token.getTokenNumber() == this.getTokenNumber();
	}
	
	public int getTokenNumber() {
		return tokenNumber;
	}
	
	public String getTokenValue() {
		return tokenValue;
	}
	
}
