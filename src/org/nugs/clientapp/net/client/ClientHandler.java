
package org.nugs.clientapp.net.client;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles a client-side channel.
 */
@Sharable
public class ClientHandler extends ChannelInboundMessageHandlerAdapter<String> {

    private ClientInitializer ci;
    public ClientHandler(ClientInitializer ci){
        this.ci=ci;
    }
    private static final Logger logger = Logger.getLogger(
            ClientHandler.class.getName());
    //when server sends something append it to client's chatarea
    @Override
    public void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        ci.getClientPresenter().getView().setOutputText(msg, true);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.", cause);
        ctx.close();
    }
}
