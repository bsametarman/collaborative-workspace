package com.collaborativeworkspace.snapshot_worker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.collaborativeworkspace.snapshot_worker.dto.DocumentEditEventDto;
import com.collaborativeworkspace.snapshot_worker.entity.DocumentEdit;
import com.collaborativeworkspace.snapshot_worker.repository.DocumentEditRepository;

@Service
public class DocumentEditConsumer {
	private DocumentEditRepository documentEditRepository;
	
	@Autowired
	public DocumentEditConsumer(DocumentEditRepository documentEditRepository) {
		this.documentEditRepository = documentEditRepository;
	}
	
	@KafkaListener(topics = "document-edits")
	public void consume(DocumentEditEventDto documentEditEventDto) {
		var document = new DocumentEdit();
		
		document.setUserId(documentEditEventDto.getUserId());
		document.setDocumentId(documentEditEventDto.getDocumentId());
		document.setActionType(documentEditEventDto.getActionType());
		document.setContent(documentEditEventDto.getContent());
		
		documentEditRepository.save(document);
	}
}
