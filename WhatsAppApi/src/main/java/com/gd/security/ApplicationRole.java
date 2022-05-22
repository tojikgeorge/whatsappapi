package com.gd.security;

import java.util.Set;

import com.google.common.collect.Sets;

public enum ApplicationRole {
	SEND(Sets.newHashSet(ApplicationUserPermission.USER_SEND)),
	RECEIVE(Sets.newHashSet(ApplicationUserPermission.USER_RECEIVE));
	
	private final Set<ApplicationUserPermission> permissions;
	
	private ApplicationRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}

}
