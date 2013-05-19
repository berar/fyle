package org.fyle.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fyle.model.ClientHandlerEvent;
import org.fyle.model.ClientHandlerSubscriber;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * Handles a client-side channel.
 */
public class ClientHandler extends SimpleChannelUpstreamHandler {

    private static final Logger logger = Logger.getLogger(
            ClientHandler.class.getName());
    private static List<ClientHandlerSubscriber> subs = new ArrayList<>();

    @Override
    public void handleUpstream(
            ChannelHandlerContext ctx, ChannelEvent e) throws Exception  {
        if (e instanceof ChannelStateEvent) {
            logger.info(e.toString());
        }
		super.handleUpstream(ctx, e);
    }

    @Override
    public void messageReceived(
            ChannelHandlerContext ctx, MessageEvent e) {
    	String message = (String) e.getMessage();
    	notifySubscribers("MESSAGE_RECEIVED", ctx, message);
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) {
    	Throwable error = e.getCause();
    	notifySubscribers("EXCEPTION_CAUGHT", ctx, error);
    	logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.");
    	/*
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.",
                e.getCause());*/
        e.getChannel().close();
    }
    
    private void notifySubscribers(String key, ChannelHandlerContext ctx, Object obj) {
        for (ClientHandlerSubscriber sub : subs) {
            sub.publish(new ClientHandlerEvent(key, ctx, obj));
        }
    }
    
    public void addSubscriber(ClientHandlerSubscriber sub) {
        subs.add(sub);
    }
}
