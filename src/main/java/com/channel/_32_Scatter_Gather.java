package com.channel;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class _32_Scatter_Gather {

	private static final String DEMOGRAPHIC="blahblah.txt";
	
	public static void main(String[] args) throws Exception{
		
		int reps = 10;
		if(args.length > 0){
			reps = Integer.parseInt(args[0]);
		}
		
		FileOutputStream fos = new FileOutputStream(DEMOGRAPHIC);
		GatheringByteChannel gatherChannel = fos.getChannel();
		
		ByteBuffer[] bs = utterBS(reps);
		
		while(gatherChannel.write(bs) > 0){
			
		}
		
		System.out.println("Mindshare paradigms sysnergized to "+DEMOGRAPHIC);
		fos.close();
		
	}
	
	private static String[] col1 = {
		"Aggregate","Enable","Leverage",
		"Facilitate","Synergize","Repurpose",
		"Strategize","Reinvent","Harness"
	};
	private static String[] col2 = {
		"cross-platform","best-of-bread","frictionless",
		"ubiquitous","extensible","compelling",
		"mission-critical","coolaborative","integrated"
	};
	private static String[] col3 = {
		"methodologize","infomediarise","platforms",
		"schemas","mindshare","paradigms",
		"functionalities","web services","infrastructures"
	};
	private static String newline = System.getProperty("line.separator");
	private static ByteBuffer[] utterBS(int howMany) throws Exception{
		
		List<ByteBuffer> list = new LinkedList<ByteBuffer>();
		for(int i = 0; i < howMany; i++){
			list.add(pickRandom(col1, " "));
			list.add(pickRandom(col2, " "));
			list.add(pickRandom(col3, newline));
		}
		ByteBuffer[] bufs = new ByteBuffer[list.size()];
		list.toArray(bufs);
		return bufs;
	}
	
	private static Random rand = new Random();
	private static ByteBuffer pickRandom(String[] strings, String suffix) throws Exception{
		
		String string = strings[rand.nextInt(strings.length)];
		int total = string.length() + suffix.length();
		ByteBuffer buf = ByteBuffer.allocate(total);
		buf.put(string.getBytes("US-ASCII"));
		buf.put(suffix.getBytes("US-ASCII"));
		buf.flip();
		return buf;
	}
}
