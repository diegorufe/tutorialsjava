package com.various.sockets.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * origin
 * https://github.com/teocci/NioSocketCodeSample/blob/master/src/com/github/teocci/nio/socket/nio/NonBlockingEchoClient.java
 * 
 * @author diego
 *
 */
public class NonBlockingEchoClient {
	private final static String HOSTNAME = "localhost";
	private final static int PORT = 9093;

	public static void main(String[] args) throws Exception {
		Runnable client = () -> {
			try {
				new NonBlockingEchoClient().startClient();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		};
		new Thread(client, "client-A").start();
		new Thread(client, "client-B").start();
	}

	/**
	 * Start the client
	 *
	 * @throws IOException
	 */
	private void startClient() throws IOException, InterruptedException {
		InetSocketAddress hostAddress = new InetSocketAddress(HOSTNAME, PORT);
		SocketChannel client = SocketChannel.open(hostAddress);
		client.configureBlocking(false);

		String threadName = Thread.currentThread().getName();

		// Send messages to server
		String[] messages = new String[] { threadName + ": msg1", threadName + ": msg2", threadName + ": msg3" };

		System.out.println(threadName + " started");

		// For send messages
//		for (int i = 0; i < messages.length; i++) {
//			ByteBuffer buffer = ByteBuffer.allocate(74);
//			buffer.put(messages[i].getBytes());
//			buffer.flip();
//			client.write(buffer);
//			System.out.println(messages[i]);
//			buffer.clear();
//			Thread.sleep(5000);
//		}

		while (true) {
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int numRead = client.read(buffer);
			
			if(numRead > 0) {
				System.out.println("Num read: " + numRead);
				byte[] data = new byte[numRead];
				System.arraycopy(buffer.array(), 0, data, 0, numRead);
				System.out.println("Data "+new String(data));
			}

			//

//			byte[] data = new byte[numRead];
//			
		}
		// client.close();
	}
}