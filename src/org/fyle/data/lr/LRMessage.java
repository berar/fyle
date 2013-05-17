package org.fyle.data.lr;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.*;
import com.thoughtworks.xstream.converters.extended.*;

@XStreamAlias("lrmessage")
@XStreamConverter(value = ToAttributedValueConverter.class, strings = { "type" })
public class LRMessage {

	@XStreamAlias("username")
	private final String username;
	@XStreamAlias("email")
	private final String email;
	@XStreamAlias("password")
	private final String password;
	@XStreamAlias("reppassword")
	private final String reppassword;
	// see annotation above this class -> strings={"type"}
	private final String type;

	private static final XStream xstream = new XStream();

	static {
		xstream.processAnnotations(LRMessage.class);
	}

	public LRMessage(String username, String email, String password,
			String reppassword) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.reppassword = reppassword;
		this.type = "register";
	}

	public LRMessage(String username, String password) {
		this.username = username;
		this.password = password;
		this.email = null;
		this.reppassword = null;
		this.type = "login";

	}

	public String generateMessage() {
		return xstream.toXML(this);
	}
	
	public static LRMessage createLRMessage(String xml) {
		return (LRMessage) xstream.fromXML(xml);
	}

	// getters
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getReppassword() {
		return reppassword;
	}

	public String getType() {
		return type;
	}
}
