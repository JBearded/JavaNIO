package com.buffer;

import java.nio.ByteBuffer;

public class Unsigned {

	public static short getUnsignedByte(ByteBuffer buffer){
		//默认情况下，0xff为整型数，为4字节数
		//例如：字节类型和整型类型做与运算时，字节类型将拓展成整型（有符号数值进行符号拓展）
		//最后，再转换为短整型
		return ((short)(buffer.get() & 0xff));
	}
	
	public static void putUnsignedByte(ByteBuffer buffer, int value){
		buffer.put((byte)(value & 0xff));
	}
	
	//----------------------------------------------------------------------------------
	
	public static int getUnsignedShort(ByteBuffer buffer){
		return (buffer.getShort() & 0xffff);
	}
	
	public static void putUnsignedShort(ByteBuffer buffer, int value){
		buffer.putShort((short)(value & 0xffff));
	}
	
	//----------------------------------------------------------------------------------
	
	public static long getUnsignedInt(ByteBuffer buffer){
		return buffer.getInt() & 0xffffffffL;
	}
	
	public static void putUnsignedInt(ByteBuffer buffer, long value){
		buffer.putInt((int)(value & 0xffffffffL));
	}
}
