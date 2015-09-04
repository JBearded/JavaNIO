package com.channel;

import java.io.FileInputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class _34_1_ChannelTransfer {

	public static void main(String[] args) throws Exception{
		
		if(args.length == 0){
			System.out.println("Usage: filename ...");
			return;
		}
		catFiles(Channels.newChannel(System.out), args);
	}
	
	private static void catFiles(WritableByteChannel target, String[] files) throws Exception{
	
		for(int i = 0; i < files.length; i++){
			FileInputStream fis = new FileInputStream(files[i]);
			FileChannel channel = fis.getChannel();
			channel.transferTo(0, channel.size(), target);
			channel.close();
			fis.close();
		}
	}
}
