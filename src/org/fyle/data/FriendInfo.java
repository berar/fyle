package org.fyle.data;

import javax.swing.ImageIcon;

public class FriendInfo {
	
	private String username;
	private boolean online;
	private ImageIcon img;
	
	public FriendInfo(String username, boolean online){
		this.username = username;
		this.online = online;
		this.img = null;
	}

	public FriendInfo(String username, boolean online, ImageIcon img){
		this.username = username;
		this.online = online;
		this.img = img;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
}
