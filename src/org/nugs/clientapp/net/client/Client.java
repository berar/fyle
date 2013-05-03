
package org.nugs.clientapp.net.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.nugs.clientapp.presenter.ClientPresenter;

/**
 * Simplistic telnet client.
 */
public class Client {

    private final String host;
    private final int port;
    private Channel ch;
    private ChannelFuture lastWriteFuture = null;
    private EventLoopGroup group;
    private ClientPresenter cp;
    private ClientInitializer ci;

    public Client(String host, int port, ClientPresenter cp) {
        this.host = host;
        this.port = port;
        this.cp = cp;
        this.ci = new ClientInitializer(this.cp);
    }

    public void run() throws Exception {
        group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(ci);

            // Start the connection attempt.
            ch = b.connect(host, port).sync().channel();

        } catch (Exception io){
        
        }
        
    }
    
    public Channel getChannel(){
        return this.ch;
    }
    
    public EventLoopGroup getEventLoopGroup(){
        return this.group;
    }
    
    public ChannelFuture getChannelFuture(){
        return this.lastWriteFuture;
    }
    
    public void setChannelFuture(ChannelFuture cf){
        this.lastWriteFuture = cf;
    }

}

