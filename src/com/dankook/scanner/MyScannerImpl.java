package com.dankook.scanner;

import com.dankook.Token.KeywordType;
import com.dankook.Token.OperatorType;
import com.dankook.Token.SeparatorType;
import com.dankook.Token.Token;

import java.util.ArrayList;
import java.util.List;

public class MyScannerImpl implements MyScanner {
    
    private List<Token> tokenList = new ArrayList<>();

    @Override
    public MyScanner tokenize(String input) {

        String token = "";
        int length = input.length();
        int i = 0;

        while(i < length) {

            token = "";
            length = input.length();
            i = 0;

            if(Character.isSpaceChar(input.charAt(0))) {
                i++;
                input = input.substring(i);
                continue;
            }

            token = KeywordType.isKeywordInString(input);
            if(addToken(token)) {
                i += token.length();
                input = input.substring(i);
                continue;
            }

            token = OperatorType.isOperatorInString(input);
            if(addToken(token)) {
                i += token.length();
                input = input.substring(i);
                continue;
            }

            token = SeparatorType.isSeparatorInString(input);
            if(addToken(token)) {
                i += token.length();

                input = input.substring(i);
                continue;
            }

            token = tokenizeUserDefinedVariableOrConstant(input);
            if(addToken(token)) {
                i += token.length();
                input = input.substring(i);
            }
        }

        return this;
    }

    public String tokenizeUserDefinedVariableOrConstant(String input) {

        StringBuilder token = new StringBuilder("");
        int length = input.length();

        if(Character.isAlphabetic(input.charAt(0)) || input.charAt(0) == '_') {
            token.append(input.charAt(0));
            
            for(int i = 1; i < length; i++) {
                if(!Character.isLetterOrDigit(input.charAt(i))) {
                    break;
                }
                token.append(input.charAt(i));
            }
        }

        else if(Character.isDigit(input.charAt(0))) {
            token.append(input.charAt(0));

            for(int i = 1; i < length; i++) {
                if(!Character.isDigit(input.charAt(i))) {
                    break;
                }
                token.append(input.charAt(i));
            }
        }

        return token.toString();
    }

    @Override
    public Token nextToken() {
        if(tokenList.isEmpty()) {
            System.out.println("========token is Empty!!!========\n");
            return null;
        }
        Token token = tokenList.get(0);
        tokenList.remove(0);

        return token;
    }

    public boolean addToken(String token) {
        if(token.isEmpty()) {
            return false;
        }
        tokenList.add(new Token(token));
        return true;
    }

    @Override
    public List<Token> getTokenList() {
        return tokenList;
    }

    @Override
    public String getValue(String value) {
        return null;
    }
}