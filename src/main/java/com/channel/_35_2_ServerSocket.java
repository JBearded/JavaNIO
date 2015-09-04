package com.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Test nonblocking accept() using ServerSocketChannel.
 * Start this program, then "telnet localhost 1234" to 
 * connect to it
 * @author BeardedJ
 *
 */
public class _35_2_ServerSocket {

	public static final String GREETING = "Hello I must be going.\r\n";
	
	public static void main(String[] args) throws Exception {
		
		int port = 1234;
		if(args.length > 0){
			port = Integer.parseInt(args[0]);
		}
		
		ByteBuffer buffer = ByteBuffer.allocate(10000);
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(port));
		ssc.configureBlocking(false);
		while(true){
			SocketChannel sc = ssc.accept();
			if(sc == null){
				// no connections, snooze a while
				Thread.sleep(2000);
			}else{
				System.out.println("Incoming connection from: "+sc.socket().getRemoteSocketAddress());
				buffer.rewind();
				sc.read(buffer);
				buffer.flip();
				while(buffer.hasRemaining()){
					System.out.print((char)buffer.get());
				}
				sc.close();
			}
			
		}
		
	}
}
