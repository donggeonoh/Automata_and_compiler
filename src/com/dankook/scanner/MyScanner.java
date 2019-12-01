package com.dankook.scanner;

import com.dankook.Token.Token;

import java.util.List;

public interface MyScanner {

    //입력받은 문자열을 tokenize 한다.
    MyScanner tokenize(String target);

    //사용자 정의어(id) 혹은 상수 값을 tokenize한다
    String tokenizeUserDefinedVariableOrConstant(String input);

    //다음 token 값을 불러온다. (값이 없을 시 console에 empty인 것을 알린다.)
    Token nextToken();

    boolean addToken(String token);
    List<Token> getTokenList();
    String getValue(String value);
}
