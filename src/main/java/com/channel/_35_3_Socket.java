package com.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class _35_3_Socket {

	public static void main(String[] args) throws Exception {

		String host = "localhost";
		int port = 1234;
		if (args.length == 2) {
			host = args[0];
			port = Integer.parseInt(args[1]);
		}

		ByteBuffer buffer = ByteBuffer
				.wrap("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".getBytes());
		// 调用finishConnect( )方法来完成连接过程，该方法任何时候都可以安全地进行调用。假如在一个非阻塞模式的SocketChannel对象上调用finishConnect( )方法，将可能出现下列情形之一

		InetSocketAddress addr = new InetSocketAddress(host, port);
		SocketChannel sc = SocketChannel.open();
		sc.configureBlocking(false);
		System.out.println("Initating connection");
		sc.connect(addr);

		while (!sc.finishConnect()) {
			System.out.println("do something useless");
		}

		System.out.println("connection established");
		sc.write(buffer);
		// Do something with the connected socket
		// The SocketChannel is still nonblocking
		sc.close();

	}
}
