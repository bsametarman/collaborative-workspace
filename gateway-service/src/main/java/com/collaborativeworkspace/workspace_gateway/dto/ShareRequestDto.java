package com.collaborativeworkspace.workspace_gateway.dto;

import com.collaborativeworkspace.workspace_gateway.entity.AccessRole;

public class ShareRequestDto {
	private String targetUserId;
    private AccessRole role;

    public ShareRequestDto() {}

    public String getTargetUserId() {
    	return targetUserId;
    }
    
    public void setTargetUserId(String targetUserId) {
    	this.targetUserId = targetUserId;
    }

    public AccessRole getRole() {
    	return role;
    }
    
    public void setRole(AccessRole role) {
    	this.role = role;
    }
}
