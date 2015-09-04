package com.buffer;

import java.nio.CharBuffer;

public class Get {

	public static void main(String[] args) {
		
		String string = "abcdefg";
		CharBuffer buffer = CharBuffer.allocate(20);
		
		for(int i = 0; i < string.length(); i++){
			buffer.put(string.charAt(i));
		}
		
		buffer.flip();
		char[] chars = new char[10];
		buffer.get(chars, 1, 5);
		for(int i = 0; i < chars.length; i++){
			System.out.println(i+":"+chars[i]);
		}
		
	}
}
