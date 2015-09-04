package com.channel;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class _34_MappedByteBuffer_2 {

	public static void main(String[] args) throws Exception {

		// Create a temp file and get a channel connected to it
		File tempFile = File.createTempFile("mmaptest", null);
		RandomAccessFile file = new RandomAccessFile(tempFile, "rw");
		FileChannel channel = file.getChannel();
		ByteBuffer temp = ByteBuffer.allocate(100);

		// Put something in the file, starting at location 0
		temp.put("This is the file connect".getBytes());
		temp.flip();
		channel.write(temp, 0);

		// Put something else in the file, starting at location 8192
		// 8192 is 8KB, almost certainly a different memory/FS page
		// This may cause a file hole, depending on the filesytem page size
		temp.clear();
		temp.put("This is more file content".getBytes());
		temp.flip();
		channel.write(temp, 8192);

		// Create three types of mappings to the same file
		MappedByteBuffer ro = channel.map(MapMode.READ_ONLY, 0, channel.size());
		MappedByteBuffer rw = channel
				.map(MapMode.READ_WRITE, 0, channel.size());
		MappedByteBuffer cow = channel.map(MapMode.PRIVATE, 0, channel.size());

		// the buffer states before any modifications
		System.out.println("Begin");
		showBuffers(ro, rw, cow);
		
		//Modify the copy-on-write buffer
		cow.position(8);
		cow.put("COW".getBytes());
		System.out.println("Change to COW buffer");
		showBuffers(ro, rw, cow);
		
		//Modify the read/write buffer
		rw.position(9);
		rw.put("R/W".getBytes());
		rw.position(8194);
		rw.put("R/W".getBytes());
		rw.force();
		System.out.println("Change to R/W buffer");
		showBuffers(ro, rw, cow);
		
		//Write to the file through the channel; hit both pages
		temp.clear();
		temp.put("Channel write ".getBytes());
		temp.flip();
		channel.write(temp, 0);
		temp.rewind();
		channel.write(temp, 8202);
		System.out.println("Write on channel");
		showBuffers(ro, rw, cow);
		
		//Modify the copy-on-write buffer again
		cow.position(8207);
		cow.put("COW2".getBytes());
		System.out.println("Second change to COW buffer");
		showBuffers(ro, rw, cow);
		
		//Modify the read/write buffer
		rw.position(0);
		rw.put("R/W2".getBytes());
		rw.position(8210);
		rw.put("R/W2".getBytes());
		rw.force();
		System.out.println("Second change to R/W buffer");
		showBuffers(ro, rw, cow);
		
		//cleaup
		channel.close();
		file.close();
		tempFile.delete();
		
		
	}

	public static void showBuffers(ByteBuffer ro, ByteBuffer rw, ByteBuffer cow)
			throws Exception {

		dumpBuffer("R/O", ro);
		dumpBuffer("R/W", rw);
		dumpBuffer("COW", cow);
		System.out.println("");
	}

	// Dump buffer content, counting and skipping nulls
	public static void dumpBuffer(String prefix, ByteBuffer buffer)
			throws Exception {
		System.out.println(prefix + ": '");
		int nulls = 0;
		int limit = buffer.limit();
		for (int i = 0; i < limit; i++) {
			char c = (char) buffer.get(i);
			if (c == '\u0000') {
				nulls++;
				continue;
			}
			if(nulls != 0){
				System.out.println("|["+nulls+" nulls]|");
				nulls = 0;
			}
			System.out.println(c);
		}
		System.out.println("'");
	}
}
