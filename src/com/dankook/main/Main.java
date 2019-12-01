package com.dankook.main;

import com.dankook.parser.MyParser;
import com.dankook.parser.MyParserImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		MyParser parser = new MyParserImpl();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			String input = scanner.nextLine();
			if(StringUtils.isBlank(input)) {
				break;
			}
			parser.start(input);
		}
		
	}
}
