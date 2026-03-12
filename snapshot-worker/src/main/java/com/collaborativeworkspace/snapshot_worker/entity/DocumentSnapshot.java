package com.collaborativeworkspace.snapshot_worker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DocumentSnapshot {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentId;
    @Column(columnDefinition = "TEXT")
    private String content;
    private Long lastProcessedEditId;
    
    public DocumentSnapshot() {
    }

    public DocumentSnapshot(String documentId, String content, Long lastProcessedEditId) {
        this.documentId = documentId;
        this.content = content;
        this.lastProcessedEditId = lastProcessedEditId;
    }
    
    public Long getId() {
    	return id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public String getDocumentId() {
    	return documentId;
    }
    
    public void setDocumentId(String documentId) {
    	this.documentId = documentId;
    }
    
    public String getContent() {
    	return content;
    }
    
    public void setContent(String content) {
    	this.content = content;
    }
    
    public Long getLastProcessedEditId() {
    	return lastProcessedEditId;
    }
    
    public void setLastProcessedEditId(Long lastProcessedEditId) {
    	this.lastProcessedEditId = lastProcessedEditId;
    }
}