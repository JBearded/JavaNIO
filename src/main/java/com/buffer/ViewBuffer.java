package com.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

public class ViewBuffer {

	public static void main(String[] args) {
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(7).order(ByteOrder.BIG_ENDIAN);
		CharBuffer charBuffer = byteBuffer.asCharBuffer();
		byteBuffer.put((byte)0);
		byteBuffer.put((byte)'H');
		byteBuffer.put((byte)0);
		byteBuffer.put((byte)'I');
		byteBuffer.put((byte)0);
		byteBuffer.put((byte)'!');
		
		
		byteBuffer.rewind();
		while(byteBuffer.asCharBuffer().hasRemaining()){
			System.out.println(byteBuffer.getChar());
		}

		println(byteBuffer);
		println(charBuffer);
		
		System.out.println(Integer.toBinaryString(255));
		System.out.println((byte)(255 & 0xff));

		System.out.println((byte)255);
	}
	
	private static void println(Buffer buffer){
		System.out.println("pos="+buffer.position()
				+", limit="+buffer.limit()
				+", capacity="+buffer.capacity()
				+": '"+buffer.toString()+"'");
	}
}
