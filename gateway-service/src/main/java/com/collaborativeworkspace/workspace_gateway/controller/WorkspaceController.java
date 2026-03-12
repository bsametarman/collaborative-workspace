package com.collaborativeworkspace.workspace_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.collaborativeworkspace.workspace_gateway.dto.DocumentEditEventDto;
import com.collaborativeworkspace.workspace_gateway.service.DocumentEventPublisherService;

@Controller
public class WorkspaceController {
	private final DocumentEventPublisherService documentEventPublisherService;
	
	@Autowired
	public WorkspaceController(DocumentEventPublisherService documentEventPublisherService) {
		this.documentEventPublisherService = documentEventPublisherService;
	}
	
	@MessageMapping("/document/edit/{documentId}")
	@SendTo("/topic/document/{documentId}")
	public DocumentEditEventDto GetDocumentEditEvent(@DestinationVariable("documentId") String documentId, @Payload DocumentEditEventDto documentEditEventDto) {
		documentEditEventDto.setDocumentId(documentId);
		documentEventPublisherService.publishEditEvent(documentEditEventDto);
		return documentEditEventDto;
	}
	
}
