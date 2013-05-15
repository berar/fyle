package org.fyle.util.mrhandler.impl;

import org.fyle.data.lr.LRErrorResponse;
import org.fyle.data.lr.LRInterface;
import org.fyle.data.lr.LRResponse;
import org.fyle.util.mrhandler.MessageReceivedHandler;

public class RegistrationErrorHandler implements MessageReceivedHandler{
	
	private LRResponse lrr;
	
	public RegistrationErrorHandler(LRResponse lrr){
		this.lrr = lrr;
	}

	@Override
	public LRInterface createLRResponse() {
		return new LRErrorResponse("register", lrr.getErrorMessage(), lrr.getErrorComponent());
	}
}
