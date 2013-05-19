package org.fyle.util.impl;

import java.util.regex.Pattern;

import org.fyle.data.LRInterface;
import org.fyle.data.lr.LRClientSideResponse;
import org.fyle.data.lr.LRResponse;
import org.fyle.util.MessageReceivedParser;

public class MessageReceivedParserImpl implements MessageReceivedParser {
	private static final Pattern RGX_PATTERN_LRRESPONSE = Pattern.compile("^(?i)<lrresponse([^>]+)>(.+?)</lrresponse>");
	
	private String message;
	private String type;
	
	public MessageReceivedParserImpl(String message){
		this.message = message;
		init();
	}
	
	private void init(){
		if(RGX_PATTERN_LRRESPONSE.matcher(message).matches()){
			this.type = "LR";
			return;
		}
	}
	
	@Override
	public LRInterface handle(){
		if("LR".equals(this.type)){
			LRResponse lrr = LRResponse.createLRResponse(message);
			if ("register".equals(lrr.getType()) && "error".equals(lrr.getStatus())){
				return new LRClientSideResponse("register", lrr.getMessage(), lrr.getComponent());
			}
			if ("register".equals(lrr.getType()) && "ok".equals(lrr.getStatus())){
				return new LRClientSideResponse("register", lrr.getMessage(), "button");
			}
			if("login".equals(lrr.getType()) && "error".equals(lrr.getStatus())){
				return new LRClientSideResponse("login", lrr.getMessage(), lrr.getComponent());
			}
			if("login".equals(lrr.getType()) && ("ok".equals(lrr.getStatus()))){
				return new LRClientSideResponse("login", lrr.getMessage(), "button");
			}
		}
		System.out.println("MRH: Error or not supported yet.");
		return null;
	}
}
