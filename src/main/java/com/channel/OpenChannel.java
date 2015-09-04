package com.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class OpenChannel {

	public static void main(String[] args) throws IOException {
		
		String host = "";
		int port = 0;
		
		SocketChannel sc = SocketChannel.open();
		sc.connect(new InetSocketAddress(host, port));
		
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(port));
		
		DatagramChannel dc = DatagramChannel.open();
		
		String file = "";
		String mode = "r";
		RandomAccessFile raf = new RandomAccessFile(file, mode);
		FileChannel fc = raf.getChannel();
	} 
}
