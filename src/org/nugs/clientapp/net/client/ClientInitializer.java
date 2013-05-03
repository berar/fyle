/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nugs.clientapp.net.client;

import io.netty.buffer.BufType;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.nugs.clientapp.presenter.ClientPresenter;

/**
 * Creates a newly configured {@link ChannelPipeline} for a new channel.
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    private ClientPresenter cp;
    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder(BufType.BYTE);
    private final ClientHandler CLIENTHANDLER = new ClientHandler(this);
    
    public ClientInitializer(ClientPresenter cp){
        this.cp=cp;
    }
    
    public ClientPresenter getClientPresenter(){
        return this.cp;
    }
    
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // Add the text line codec combination first,
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(
                8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", DECODER);
        pipeline.addLast("encoder", ENCODER);

        // and then business logic.
        pipeline.addLast("handler", CLIENTHANDLER);
    }
}