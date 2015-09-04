package com.buffer;

import java.nio.CharBuffer;

public class _217_Compact {

	public static void main(String[] args) {
		
		String string = "abcdefg";
		CharBuffer buffer = CharBuffer.allocate(50);
		for(int i = 0; i < string.length(); i++){
			buffer.put(string.charAt(i));
		}
		
		buffer.position(3);
		buffer.compact();
		buffer.flip();
		
		while(buffer.hasRemaining()){
			System.out.print(buffer.get());
		}
		
	}
}
