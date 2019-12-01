package com.dankook.Token;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

//토큰 숫자, 값을 가지고 있는 클래스
public class Token {
    
    //token 값이 없는 경우 -1
    public static final int NONE = -1;

    private int tokenNumber;
    private int tokenValue;

    //keyword, operator, separator, 변수, 상수 확인 후 token을 생성한다.
    public Token(String target) {
        if(KeywordType.isKeywordType(target)) {
            KeywordType type = KeywordType.getKeywordType(target);
            if(Objects.nonNull(type)) {
                tokenNumber = type.getTokenNumber();
                tokenValue = NONE;
            }
            return;
        }
        else if(OperatorType.isOperatorType(target)) {
            OperatorType type = OperatorType.getOperatorType(target);
            if(Objects.nonNull(type)) {
                tokenNumber = type.getTokenNumber();
                tokenValue = NONE;
            }
            return;
        }
        else if(SeparatorType.isSeparatorType(target)) {
            SeparatorType type = SeparatorType.getSeparatorType(target);
            if(Objects.nonNull(type)) {
                tokenNumber = type.getTokenNumber();
                tokenValue = NONE;
            }
            return;
        }
        
        if(StringUtils.isNumeric(target)) {
            tokenNumber = UserDefinedType.CONSTANT;
            tokenValue = UserDefinedType.putIdentifier(target);
            UserDefinedType.setIdentifierValue(target, Integer.parseInt(target));
        }
        else if(StringUtils.isAlphanumeric(target)) {
            tokenNumber = UserDefinedType.IDENTIFIER;
            tokenValue = UserDefinedType.putIdentifier(target);
        }
    }

    public int getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(int tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public int getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(int tokenValue) {
        this.tokenValue = tokenValue;
    }
}
