package org.fyle.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * Simplistic telnet client.
 */
public class Client {

	private final String host;
	private final int port;
	private ChannelFuture lastWriteFuture = null;
	private Channel channel;
	private ClientBootstrap bootstrap;
	private static ClientPipelineFactory pipeline;
	private boolean connected;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
		this.connected = false;
		pipeline = new ClientPipelineFactory();
	}

	public void run() throws IOException {
		// Configure the client.
		bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));

		// Configure the pipeline factory.
		bootstrap.setPipelineFactory(pipeline);
		bootstrap.setOption("connectTimeoutMillis", 5000);

		// Start the connection attempt.
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(host,
				port));

		// Wait until the connection attempt succeeds or fails.
		channel = future.awaitUninterruptibly().getChannel();
		if (!future.isSuccess()) {
			// future.getCause().printStackTrace();
			bootstrap.releaseExternalResources();
			return;
		}
		this.setConnected(true);

	}

	public void close() {
		// Wait untill server closes the connection
		channel.getCloseFuture().awaitUninterruptibly();
		// Wait until all messages are flushed before closing the channel.
		if (lastWriteFuture != null) {
			lastWriteFuture.awaitUninterruptibly();
		}
		// Close the connection. Make sure the close operation ends because
		// all I/O operations are asynchronous in Netty.
		channel.close().awaitUninterruptibly();
		// Shut down all thread pools to exit.
		bootstrap.releaseExternalResources();
		this.setConnected(false);
	}

	public void write(String message) {
		lastWriteFuture = channel.write(message);
	}

	public boolean isConnected() {
		return this.connected;
	}

	private void setConnected(boolean bool) {
		this.connected = bool;
	}
	
	public ClientPipelineFactory getPipelineFactory(){
		return pipeline;
	}
	/*
	public static void main(String[] args) {

		// Parse options.
		String host = "localhost";
		int port = 6789;

		try {
			new Client(host, port).run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}