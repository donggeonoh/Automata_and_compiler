package com.dankook.Token;

public enum KeywordType {
    
    //자료형
    BOOLEAN(23, "boolean"),
    CHARACTER(24, "char"),
    INTEGER(26, "int"),
    FLOAT(27, "float"),
    DOUBLE(28, "double"),

    //문법
    FOR(29, "for"),
    IF(30, "if");

    private final int tokenNumber;
    private final String tokenValue;

    KeywordType(int tokenNumber, String tokenValue) {
        this.tokenNumber = tokenNumber;
        this.tokenValue = tokenValue;
    }

    public static String isKeywordInString(String target) {
        if(target.contains(BOOLEAN.tokenValue)) {
            return BOOLEAN.tokenValue;
        }
        else if(target.contains(CHARACTER.tokenValue)) {
            return CHARACTER.tokenValue;
        }
        else if(target.contains(INTEGER.tokenValue)) {
            return INTEGER.tokenValue;
        }
        else if(target.contains((FLOAT.tokenValue))) {
            return FLOAT.tokenValue;
        }
        else if(target.contains(DOUBLE.tokenValue)) {
            return DOUBLE.tokenValue;
        }
        else if(target.contains(FOR.tokenValue)) {
            return FOR.tokenValue;
        }
        else if(target.contains(IF.tokenValue)) {
            return IF.tokenValue;
        }
        return "";
    }

    public static boolean isKeywordType(String target) {
        for(KeywordType type : KeywordType.values()) {
            if(type.tokenValue.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public static KeywordType getKeywordType(String target) {
        for(KeywordType type : KeywordType.values()) {
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
