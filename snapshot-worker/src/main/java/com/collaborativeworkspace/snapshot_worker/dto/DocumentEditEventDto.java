package com.collaborativeworkspace.snapshot_worker.dto;

import com.collaborativeworkspace.snapshot_worker.entity.ActionType;

public class DocumentEditEventDto {
	private Long userId;
	private Long documentId;
	private ActionType actionType;
	private String content;
	private int position;
	
	public DocumentEditEventDto() {}

	public DocumentEditEventDto(Long userId, Long documentId, ActionType actionType, String content, int position) {
		this.userId = userId;
		this.documentId = documentId;
		this.actionType = actionType;
		this.content = content;
		this.position = position;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(Long documentId) {
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

