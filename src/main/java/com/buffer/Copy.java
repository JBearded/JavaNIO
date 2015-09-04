package com.buffer;

import java.nio.CharBuffer;

public class Copy {

	public static void main(String[] args) {
		
		//duplicate返回一个新缓冲区，共享数据元素，但各自有各自的位置，上界和标记属性
		//asReadOnlyBuffer和duplicate一样，只是只读
		String string = "abcdefg";
		CharBuffer buffer = CharBuffer.allocate(50);
		for(int i = 0; i < string.length(); i++){
			buffer.put(string.charAt(i));
		}
		CharBuffer newBuffer = buffer.duplicate();
		
		System.out.println(buffer.position()+";"+buffer.limit());
		System.out.println(newBuffer.position()+";"+newBuffer.limit());
		
		buffer.flip();
		
		System.out.println(buffer.position()+";"+buffer.limit());
		System.out.println(newBuffer.position()+";"+newBuffer.limit());
		
		buffer.put(0, 'w');
		System.out.println(newBuffer.get(0));
		System.out.println(buffer.get(0));
		
		
		buffer.position(0);
		while(buffer.hasRemaining()){
			System.out.print(buffer.get());
		}
		System.out.println("");
		
		//slice
		buffer.position(2).limit(4);
		CharBuffer sliceBuffer = buffer.slice();

		while(sliceBuffer.hasRemaining()){
			System.out.print(sliceBuffer.get());
		}
		System.out.println("");
		
		sliceBuffer.put(0, 'k');
		
		buffer.position(0).limit(buffer.capacity());
		while(buffer.hasRemaining()){
			System.out.print(buffer.get());
		}
	}
}
