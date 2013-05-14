package org.fyle.data;

public class LRMessage {
	
	private String username;
	private String email;
	private String password;
	private String reppassword;
	
	public LRMessage(String username, String email, String password, String reppassword){
		this.username = username;
		this.email = email;
		this.password = password;
		this.reppassword = reppassword;
	}
	
	public LRMessage(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public String generateRegisterMessage(){
		return "REGISTER " + "'u=" + this.username + "' " + 
	    "'e=" + this.email + "' " + "'p=" + this.password + "' "
	    + "'rp=" + this.reppassword + "'";
	}
}
