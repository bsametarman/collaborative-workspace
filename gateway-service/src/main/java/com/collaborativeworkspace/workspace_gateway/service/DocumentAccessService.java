package com.collaborativeworkspace.workspace_gateway.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.collaborativeworkspace.workspace_gateway.entity.AccessRole;
import com.collaborativeworkspace.workspace_gateway.entity.DocumentAccess;
import com.collaborativeworkspace.workspace_gateway.repository.DocumentAccessRepository;

@Service
public class DocumentAccessService {
	private final DocumentAccessRepository documentAccessRepository;

    public DocumentAccessService(DocumentAccessRepository documentAccessRepository) {
        this.documentAccessRepository = documentAccessRepository;
    }

    public void shareDocument(String documentId, String requesterId, String targetUserId, AccessRole role) {
        Optional<DocumentAccess> requesterAccess = documentAccessRepository.findByDocumentIdAndUserId(documentId, requesterId);
        
        if (requesterAccess.isEmpty() || requesterAccess.get().getRole() != AccessRole.OWNER) {
            throw new RuntimeException("Only the OWNER can share this document.");
        }

        if (documentAccessRepository.existsByDocumentIdAndUserId(documentId, targetUserId)) {
            throw new RuntimeException("User already has access to this document.");
        }

        DocumentAccess newAccess = new DocumentAccess(documentId, targetUserId, role);
        documentAccessRepository.save(newAccess);
    }
}
