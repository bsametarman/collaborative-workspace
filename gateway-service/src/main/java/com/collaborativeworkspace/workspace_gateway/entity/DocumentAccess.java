package com.collaborativeworkspace.workspace_gateway.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "document_access")
public class DocumentAccess {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String documentId;

    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccessRole role;

    public DocumentAccess() {}

    public DocumentAccess(String documentId, String userId, AccessRole role) {
        this.documentId = documentId;
        this.userId = userId;
        this.role = role;
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

    public String getUserId() {
    	return userId;
    }
    
    public void setUserId(String userId) {
    	this.userId = userId;
    }

    public AccessRole getRole() {
    	return role;
    }
    
    public void setRole(AccessRole role) {
    	this.role = role;
    }
}
