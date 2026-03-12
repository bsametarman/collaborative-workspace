package com.collaborativeworkspace.snapshot_worker.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collaborativeworkspace.snapshot_worker.entity.DocumentSnapshot;
import com.collaborativeworkspace.snapshot_worker.repository.DocumentSnapshotRepository;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*")
public class SnapshotController {
	private final DocumentSnapshotRepository snapshotRepository;

    public SnapshotController(DocumentSnapshotRepository snapshotRepository) {
        this.snapshotRepository = snapshotRepository;
    }

    @GetMapping("/{documentId}/snapshot")
    public String getDocumentSnapshot(@PathVariable String documentId) {
        DocumentSnapshot snapshot = snapshotRepository.findByDocumentId(documentId);
        
        if (snapshot == null) {
            return "";
        }
        
        return snapshot.getContent();
    }
}
