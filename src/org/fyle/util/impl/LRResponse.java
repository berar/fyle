package org.fyle.util.impl;

public class LRResponse {
	
	private final String key;
	private final String component;
	private final String message;

	public LRResponse(String key, String message, String component){
		this.key = key;
		this.component = component;
		this.message = message;
	}
	
	public String getProperty(){
		return this.key;
	}
	
	public String getComponent(){
		return this.component;
	}
	
	public String getMessage(){
		return this.message;
	}
}
