package org.fyle.util.mrhandler.impl;

import java.util.List;

import org.fyle.util.impl.LRResponse;
import org.fyle.util.mrhandler.MessageReceivedHandler;

public class RegistrationHandler implements MessageReceivedHandler {
	//REGISTER ERROR MESSAGE COMPONENT
	private List<String> messages;
	
	public RegistrationHandler(List<String> messageParts) {
		this.messages = messageParts;
		messageParts.remove(0);
	}

	@Override
	public LRResponse createLRResponse() {
		if("ERROR".equals(messages.get(0))) return createError();
		if("OK".equals(messages.get(0))) return createOk();
		return null;
	}
	
	private LRResponse createError(){
		StringBuilder errorMessage = new StringBuilder();
		for(String message : messages){
			if("ERROR".equals(message) || messages.get(messages.size()-1).equals(message)){
				continue;
			}
			errorMessage.append(message).append(" ");
		}
		return new LRResponse("REGISTER", errorMessage.toString(), messages.get(messages.size()-1));
	}
	
	private LRResponse createOk(){
		System.out.println("LRResponse OK; Not Supported yet.");
		return null;
	}
}
