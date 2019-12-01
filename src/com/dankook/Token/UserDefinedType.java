package com.dankook.Token;

import java.util.HashMap;

// 변수 및 상수의 정의 타입을 다루는 클래스
public class UserDefinedType {
    
    //사용자 변수 혹은 상수의 tokenNumber 값
    public static final int IDENTIFIER = 2;
    public static final int CONSTANT = 4;
    
    //변수가 추가 될 시 변수의 주소가 증가
    private static int AUTO_INCREASE_ADDR = 1;

    //String : 변수의 이름, Integer : 변수가 들어가 있는 addr
    private static final HashMap<String, Integer> identifierAddress = new HashMap<>();

    //Integer : 변수가 들어가있는 addr, Integer : 변수의 value
    private static final HashMap<Integer, Integer> identifier = new HashMap<>();

    //변수를 Hash table에 저장
    public static int putIdentifier(String id) {

        if(identifierAddress.containsKey(id)) {
            return identifierAddress.get(id);
        }
        int tokenValue = AUTO_INCREASE_ADDR;

        identifierAddress.put(id, AUTO_INCREASE_ADDR);
        identifier.put(identifierAddress.get(id), null);
        AUTO_INCREASE_ADDR++;

        return tokenValue;
    }

    public static int getIdentifierValue(String id) {
        return identifier.get(identifierAddress.get(id));
    }
    
    public static int getIdentifierValue(int address) {
        return identifier.get(address);
    }

    public static void setIdentifierValue(String id, int value) {
        identifier.put(identifierAddress.get(id), value);
    }
    
    public static void setIdentifierValue(int address, int tokenValue) {
        identifier.put(address, tokenValue);
    }
}