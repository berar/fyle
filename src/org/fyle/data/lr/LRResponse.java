package org.fyle.data.lr;

import java.lang.reflect.GenericSignatureFormatError;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

@XStreamAlias("lrresponse")
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"type"})
public class LRResponse {
	
	private static final XStream xstream = new XStream();
	//can be either login or register
	//should be enum but there's no good converter for xstream
	private final String type;
	@XStreamAlias("message")
	private final String message;
	@XStreamAlias("component")
	private final String component;
	@XStreamAlias("status")
	private final String status;
	
	static{
		xstream.processAnnotations(LRResponse.class);
	}
	
	//error constructor
	public LRResponse(String type, String message, String component){
		if((!"login".equals(type))&&(!"register".equals(type)))
			throw new GenericSignatureFormatError();
		this.status = "error";
		this.type = type;
		this.message = message;
		this.component = component;
	}
	//ok constructor
	public LRResponse(String type, String message){
		if((!"login".equals(type))&&(!"register".equals(type)))
			throw new GenericSignatureFormatError();
		this.status = "ok";
		this.type = type;
		this.message = message;
		this.component = "button";
	}
	
	public String generateResponse(){
		return xstream.toXML(this);
	}
	
	public static LRResponse createLRResponse(String xml) {
		return (LRResponse) xstream.fromXML(xml);
	}
	
	//getters
	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public String getComponent() {
		return component;
	}

	public String getStatus() {
		return status;
	}
}

