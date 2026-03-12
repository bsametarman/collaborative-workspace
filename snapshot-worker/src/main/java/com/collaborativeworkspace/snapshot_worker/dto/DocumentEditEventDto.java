package com.collaborativeworkspace.snapshot_worker.dto;

import com.collaborativeworkspace.snapshot_worker.entity.ActionType;

public class DocumentEditEventDto {
	private String userId;
	private String documentId;
	private ActionType actionType;
	private String content;
	private int position;
	
	public DocumentEditEventDto() {}

	public DocumentEditEventDto(String userId, String documentId, ActionType actionType, String content, int position) {
		this.userId = userId;
		this.documentId = documentId;
		this.actionType = actionType;
		this.content = content;
		this.position = position;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	public ActionType getActionType() {
		return actionType;
	}
	
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
}

