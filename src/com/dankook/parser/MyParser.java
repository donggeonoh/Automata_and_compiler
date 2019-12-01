package com.dankook.parser;

public interface MyParser {
    void start(String input);
    boolean statement();
    void expression();
    void term();
    void factor();
    void operate(int tokenNumber);
    void push(int value);
    int pop();
    void checkToken(int tokenNumber);
    boolean isStatusSuccess();
}