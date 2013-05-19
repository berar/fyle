package org.fyle.model;

import org.jboss.netty.channel.ChannelHandlerContext;

public class ClientHandlerEvent {
	
	private final String key;
	private final ChannelHandlerContext ctx;
	private final Object obj;
	
	public ClientHandlerEvent(String key, ChannelHandlerContext ctx, Object obj){
		this.key = key;
		this.ctx = ctx;
		this.obj = obj;
	}
	
	public String getProperty(){
		return this.key;
	}
	
	public ChannelHandlerContext getChannelHandlerContext(){
		return this.ctx;
	}
	
	public Object getObject(){
		return this.obj;
	}
}
