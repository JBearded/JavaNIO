package com.buffer;

import java.nio.CharBuffer;

public class Mark {

	public static void main(String[] args){
		
		String string = "abcdefg";
		CharBuffer buffer = CharBuffer.allocate(50);
		
		for(int i = 0; i < string.length(); i++){
			buffer.put(string.charAt(i));
		}
		
		if(!buffer.hasRemaining())
			System.out.println("已经到界限");
		else
			System.out.println("界限初始化为最大容量");
		
		buffer.position(2).mark();
		
		while(buffer.hasRemaining()){
			System.out.print(buffer.get());
		}
		
		buffer.reset();
		System.out.printf("\n 复位 %d", buffer.position());
	}
}
