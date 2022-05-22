package com.gd.security;


public enum ApplicationUserPermission {
	USER_SEND("user:send"),
	USER_RECEIVE("user:receive");
	
	private final String permission;
	
	private ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
	

}
