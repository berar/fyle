
package org.nugs.clientapp.net.server;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles a server-side channel.
 */
@Sharable
public class ServerHandler extends ChannelInboundMessageHandlerAdapter<String> {

    private static final Logger logger = Logger.getLogger(
            ServerHandler.class.getName());
    List<ChannelHandlerContext> channels = new ArrayList<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send greeting for a new connection.
        channels.add(ctx);
        ctx.write(
                "Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
        ctx.write("It is " + new Date() + " now.\r\n");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, String request) throws Exception {
        // Generate and write a response.
        String response;
        boolean close = false;
        //check if user want to quit or send to all connections what user wrote
        if ("/quit".equals(request.toLowerCase())) {
            response = "Have a good day!\r\n";
            close = true;
        } else {
            for (ChannelHandlerContext c : channels) {
                c.write(request + "\n");
            }
        }
        if (close) {
            ctx.close();
            channels.remove(ctx);
            //future.addListener(ChannelFutureListener.CLOSE);            
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.", cause);
        ctx.close();
    }
}
