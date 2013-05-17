package org.fyle.data.lr;

import org.fyle.data.LRInterface;

public class LRClientSideResponse implements LRInterface {
	//type -> login or register
	private final String type;
	private final String component;
	private final String message;
	
	public LRClientSideResponse(String type, String message, String component){
		this.type = type;
		this.component = component;
		this.message = message;
	}
	@Override
	public String getType(){
		return this.type;
	}
	@Override
	public String getComponent(){
		return this.component;
	}
	@Override
	public String getMessage(){
		return this.message;
	}
}
