package com.various.sockets.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * origin: https://github.com/teocci/NioSocketCodeSample/blob/master/src/com/github/teocci/nio/socket/nio/NonBlockingClient.java
 * @author diego
 *
 */
public class NonBlockingClient {
	private final static String HOSTNAME = "localhost";
	private final static int PORT = 9093;

	public static void main(String[] args) throws IOException, InterruptedException {
		SocketChannel channel = SocketChannel.open();

		// we open this channel in non blocking mode
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress(HOSTNAME, PORT));

		while (!channel.finishConnect()) {
			// System.out.println("still connecting");
		}
		while (true) {
			// See if any message has been received
			ByteBuffer bufferA = ByteBuffer.allocate(20);
			String message = "";
			while (channel.read(bufferA) > 0) {
				// Flip the buffer to start reading
				bufferA.flip();
				message += Charset.defaultCharset().decode(bufferA);
			}

			if (message.length() > 0) {
				System.out.println(message);
				// Write some data into the channel
				CharBuffer buffer = CharBuffer.wrap("Hello Server");
				while (buffer.hasRemaining()) {
					channel.write(Charset.defaultCharset().encode(buffer));
				}
				message = "";
			}
		}
	}
}