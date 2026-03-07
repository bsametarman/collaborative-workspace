package com.collaborativeworkspace.workspace_gateway.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.collaborativeworkspace.workspace_gateway.dto.DocumentEditEventDto;

@Service
public class DocumentEventPublisherService {
	private final KafkaTemplate<String, DocumentEditEventDto> kafkaTemplate;
	
	public DocumentEventPublisherService(KafkaTemplate<String, DocumentEditEventDto> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void publishEditEvent(DocumentEditEventDto event) {
		kafkaTemplate.send("document-edits", event.getDocumentId().toString(), event);
	}
}
