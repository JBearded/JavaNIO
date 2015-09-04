package com.buffer;

import java.nio.CharBuffer;

//position <= index < limit
public class Equals {

	public static void main(String[] args) {
		
		String string1 = "abcdefg";
		String string2 = "hzyabcdefgaop";
		CharBuffer buffer1 = CharBuffer.allocate(50);
		CharBuffer buffer2 = CharBuffer.allocate(20);
		
		for(int i = 0; i < string1.length(); i++){
			buffer1.put(string1.charAt(i));
		}
		
		for(int i = 0; i < string2.length(); i++){
			buffer2.put(string2.charAt(i));
		}
		
		buffer1.flip().mark();
		buffer2.position(3).mark(); //3为a
		buffer2.limit(10);	//9为w
		
		while(buffer1.hasRemaining()){
			System.out.print(buffer1.get());
		}
		System.out.println("");
		while(buffer2.hasRemaining()){
			System.out.print(buffer2.get());
		}
		System.out.println("");
		
		buffer1.reset();
		buffer2.reset();
		
		System.out.println(buffer1.equals(buffer2));
		
		System.out.println(buffer1.compareTo(buffer2));
		
		buffer1.position(4); //4为e
		buffer2.position(10); //10为a
		buffer2.limit(12);
		System.out.println(buffer1.compareTo(buffer2));
	}
}
