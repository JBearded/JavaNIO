package com.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;
import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class _35_4_DatagramClient {

	private static final int DEFAULT_TIME_PORT = 37;
	private static final long DIFF_1900 = 2208988800L;
	protected int port = DEFAULT_TIME_PORT;
	protected List remoteHosts;
	protected DatagramChannel channel;

	public _35_4_DatagramClient(String[] args) throws Exception {

		if (args.length == 0) {
			throw new Exception("Usage: [-p port ] host ...");
		}
		parseArgs(args);
		this.channel = DatagramChannel.open();
	}

	protected void parseArgs(String[] args) {
		remoteHosts = new LinkedList<InetSocketAddress>();
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			// send client requests to the given port
			if (arg.equals("-p")) {
				i++;
				this.port = Integer.parseInt(args[i]);
				continue;
			}
			// create an address object for the hostname
			InetSocketAddress sa = new InetSocketAddress(arg, port);

			// validate that it has an address
			if (sa.getAddress() == null) {
				System.out.println("Cannot resolve address: " + arg);
				continue;
			}
			remoteHosts.add(sa);
		}

	}

	protected void sendRequests() throws Exception {

		ByteBuffer buffer = ByteBuffer.allocate(1);
		Iterator it = remoteHosts.iterator();
		while (it.hasNext()) {
			InetSocketAddress sa = (InetSocketAddress) it.next();
			System.out
					.println("Requesting time from " + sa.getHostName() + ":");
			// make it empty
			buffer.clear().flip();
			// fire and forget
			channel.send(buffer, sa);
		}

	}

	protected InetSocketAddress receivePacket(DatagramChannel channel,
			ByteBuffer buffer) throws Exception {

		buffer.clear();
		// Receive an unsigned 32-bit, big-endian value
		return ((InetSocketAddress) channel.receive(buffer));
	}

	// Receive any replies that arrive
	public void getReplies() throws Exception {

		// Allocate a buffer to hold a long value
		ByteBuffer longBuffer = ByteBuffer.allocate(8);

		// Assure big-endian (network) byte order
		longBuffer.order(ByteOrder.BIG_ENDIAN);

		// Zero the whole buffer to be sure
		longBuffer.putLong(0, 0);

		// Position to first byte of the low-order 32 bits
		longBuffer.position(4);

		// Slice the buffer; gives view of the low-order 32 bits
		ByteBuffer buffer = longBuffer.slice();
		int expect = remoteHosts.size();
		int replies = 0;
		System.out.println("");
		System.out.println("Waiting for replies...");
		while (true) {
			InetSocketAddress sa;
			sa = receivePacket(channel, buffer);
			buffer.flip();
			replies++;
			printTime(longBuffer.getLong(0), sa);
		}
	}

	protected void printTime(long remote1900, InetSocketAddress sa) {

		// local time as seconds since Jan 1,1970
		long local = System.currentTimeMillis() / 1000;
		// remote time as seconds since Jan 1, 1970
		long remote = remote1900 - DIFF_1900;

		Date remoteDate = new Date(remote * 1000);
		Date localDate = new Date(local * 1000);

		long skew = remote - local;
		System.out.println("Reply from " + sa.getHostName() + ":"
				+ sa.getPort());
		System.out.println(" there: " + remoteDate);
		System.out.println(" here: " + localDate);
		System.out.println(" skew: ");
		if (skew == 0) {
			System.out.println("none");
		} else if (skew > 0) {
			System.out.println(skew + " seconds ahead");
		} else {
			System.out.println((-skew) + " seconds behind");
		}
	}

	public static void main(String[] args) throws Exception {

		_35_4_DatagramClient client = new _35_4_DatagramClient(args);
		client.sendRequests();
		client.getReplies();
	}

}
