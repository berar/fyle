package org.fyle.data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

@XStreamAlias("discmessage")
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"info"})
public class DiscMessage {
	private static final XStream xstream = new XStream();
	private final String info;
	
	static{
		xstream.processAnnotations(DiscMessage.class);
	}
	
	public DiscMessage(){
		this.info = "Not used for now.";
	}
	
	public String generateMessage(){
		return xstream.toXML(this);
	}
	
	public static DiscMessage createDiscMessage(String xml) {
		return (DiscMessage) xstream.fromXML(xml);
	}
	//getters
	public String getInfo(){
		return this.info;
	}
}
