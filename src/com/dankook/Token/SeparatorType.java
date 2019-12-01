package com.dankook.Token;

public enum SeparatorType {
    
    SEMICOLON(12, ";"),
    LPARENTHESES(13, "("),
    RPARENTHESES(14, ")"),
    LCOMMENT(15, "/*"),
    RCOMMENT(16, "*/"),
    COMMENT(17, "//");
    
    public static final int LPARENTHESES_TOKEN_NUMBER = 13;

    private final int tokenNumber;
    private final String tokenValue;

    SeparatorType(int tokenNumber, String tokenValue) {
        this.tokenNumber = tokenNumber;
        this.tokenValue = tokenValue;
    }

    public static String isSeparatorInString(String target) {
        switch(target.charAt(0)) {
            case ';':
                return SEMICOLON.tokenValue;
            case '(':
                return LPARENTHESES.tokenValue;
            case ')':
                return RPARENTHESES.tokenValue;
            case '/':
                if(isCharEqual(target.charAt(1), '*')) {
                    return LCOMMENT.tokenValue;
                }
                else if(isCharEqual(target.charAt(1), '/')) {
                    return COMMENT.tokenValue;
                }
                break;
            case '*':
                if(isCharEqual(target.charAt(1), '/')) {
                    return RCOMMENT.tokenValue;
                }
                break;
        }

        return "";
    }

    public static boolean isSeparatorType(String target) {
        switch(target.charAt(0)) {
            case ';':
            case '(':
            case ')':
                return true;
            case '/':
                return isCharEqual(target.charAt(1), '*');
            case '*':
                return isCharEqual(target.charAt(1), '/');
        }
        return false;
    }

    private static boolean isCharEqual(char character, char comparedCharacter) {
        return character == comparedCharacter;
    }

    public static SeparatorType getSeparatorType(String target) {
        for(SeparatorType type : SeparatorType.values()) {
            if(type.tokenValue.equals(target)) {
                return type;
            }
        }
        return null;
    }

    public int getTokenNumber() {
        return tokenNumber;
    }

    public String getTokenValue() {
        return tokenValue;
    }
}
