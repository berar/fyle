package org.fyle.util.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fyle.util.MessageReceivedParser;
import org.fyle.util.mrhandler.MessageReceivedHandler;
import org.fyle.util.mrhandler.impl.RegistrationHandler;

public class MessageReceivedParserImpl implements MessageReceivedParser {
	
	private String message;
	private List<String> messageParts = new ArrayList<>();
	
	public MessageReceivedParserImpl(String message){
		this.message = message;
		init();
	}
	
	private void init(){
		message.trim();
        String[] tokens = message.split(" ");
        messageParts.addAll(Arrays.asList(tokens));
	}
	
	@Override
	public MessageReceivedHandler handle(){
//		if("AUTH".equals(messageParts.get(0))){
//			return new LoginHandler(messageParts);
//		}
		if("REGISTER".equals(messageParts.get(0))){
			return new RegistrationHandler(messageParts);
		}
		System.out.println("Error or not supported yet.");
		return null;
	}
}
