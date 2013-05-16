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
	@XStreamAlias("errormessage")
	private final String errorMessage;
	@XStreamAlias("errorcomponent")
	private final String errorComponent;
	@XStreamAlias("status")
	private final String status;
	
	static{
		xstream.processAnnotations(LRResponse.class);
	}
	
	//error constructor, TODO ok constructor
	public LRResponse(String type, String errorMessage, String errorComponent){
		if((!"login".equals(type))&&(!"register".equals(type)))
			throw new GenericSignatureFormatError();
		this.status = "error";
		this.type = type;
		this.errorMessage = errorMessage;
		this.errorComponent = errorComponent;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorComponent() {
		return errorComponent;
	}

	public String getStatus() {
		return status;
	}
}
