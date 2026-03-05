package com.collaborativeworkspace.snapshot_worker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DocumentEdit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Long documentId;
	private ActionType actionType;
	private char content;
	
	public DocumentEdit() {}

	public DocumentEdit(Long userId, Long documentId, ActionType actionType, char content) {
		this.userId = userId;
		this.documentId = documentId;
		this.actionType = actionType;
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public char getContent() {
		return content;
	}
	
	public void setContent(char content) {
		this.content = content;
	}
}
