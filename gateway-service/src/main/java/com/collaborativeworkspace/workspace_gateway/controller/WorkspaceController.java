package com.collaborativeworkspace.workspace_gateway.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collaborativeworkspace.workspace_gateway.dto.DocumentEditEventDto;
import com.collaborativeworkspace.workspace_gateway.dto.ShareRequestDto;
import com.collaborativeworkspace.workspace_gateway.security.JwtValidator;
import com.collaborativeworkspace.workspace_gateway.service.DocumentAccessService;
import com.collaborativeworkspace.workspace_gateway.service.DocumentEventPublisherService;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {
	private final DocumentEventPublisherService documentEventPublisherService;
	private final DocumentAccessService documentAccessService;
	private final JwtValidator jwtValidator;
	
	@Autowired
	public WorkspaceController(DocumentEventPublisherService documentEventPublisherService, DocumentAccessService documentAccessService, JwtValidator jwtValidator) {
		this.documentEventPublisherService = documentEventPublisherService;
		this.documentAccessService = documentAccessService;
		this.jwtValidator = jwtValidator;
	}
	
	@MessageMapping("/document/edit/{documentId}")
	@SendTo("/topic/document/{documentId}")
	public DocumentEditEventDto GetDocumentEditEvent(@DestinationVariable("documentId") String documentId, @Payload DocumentEditEventDto documentEditEventDto) {
		documentEditEventDto.setDocumentId(documentId);
		documentEventPublisherService.publishEditEvent(documentEditEventDto);
		return documentEditEventDto;
	}
	
	@PostMapping("/{documentId}/share")
    public ResponseEntity<String> shareDocument(@PathVariable String documentId, @RequestBody ShareRequestDto request, @RequestHeader("Authorization") String authHeader) {
		System.out.println(">>> HIT THE CONTROLLER! Sharing document: " + documentId + " with user: " + request.getTargetUserId());
		try {
        	if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Missing token.");
            }
            
            String token = authHeader.substring(7);
            
            if (!jwtValidator.validateToken(token)) {
                return ResponseEntity.status(401).body("Invalid token.");
            }
            
            String requesterId = jwtValidator.extractUserId(token);
            
            documentAccessService.shareDocument(documentId, requesterId, request.getTargetUserId(), request.getRole());
            
            return ResponseEntity.ok("Document shared successfully!");
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
}
