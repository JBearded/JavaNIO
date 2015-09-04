package com.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class Put {

	public static void main(String[] args) {
		
		String string = "abcdefg";
		char[] chars = string.toCharArray();
		
		CharBuffer buffer = CharBuffer.allocate(20);
		buffer.put(chars, 3, 3);	
		buffer.flip();
		while(buffer.hasRemaining()){
			System.out.print(buffer.get());
		}

	}
}
