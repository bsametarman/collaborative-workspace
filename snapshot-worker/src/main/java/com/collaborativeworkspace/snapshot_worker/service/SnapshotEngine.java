package com.collaborativeworkspace.snapshot_worker.service;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.collaborativeworkspace.snapshot_worker.entity.ActionType;
import com.collaborativeworkspace.snapshot_worker.entity.DocumentEdit;
import com.collaborativeworkspace.snapshot_worker.entity.DocumentSnapshot;
import com.collaborativeworkspace.snapshot_worker.repository.DocumentEditRepository;
import com.collaborativeworkspace.snapshot_worker.repository.DocumentSnapshotRepository;

import java.util.List;

@Service
public class SnapshotEngine {

    private final DocumentEditRepository editRepository;
    private final DocumentSnapshotRepository snapshotRepository;

    public SnapshotEngine(DocumentEditRepository editRepository, DocumentSnapshotRepository snapshotRepository) {
        this.editRepository = editRepository;
        this.snapshotRepository = snapshotRepository;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void generateSnapshots() {
    	List<Long> distinctIds = editRepository.findDistinctDocumentIds();
    	
    	for(Long targetDocumentId : distinctIds) {
            DocumentSnapshot snapshot = snapshotRepository.findByDocumentId(targetDocumentId);
            if (snapshot == null) {
                snapshot = new DocumentSnapshot(targetDocumentId, "", 0L);
            }

            List<DocumentEdit> newEdits = editRepository.findByDocumentIdAndIdGreaterThanOrderByIdAsc(targetDocumentId, snapshot.getLastProcessedEditId());

            if (newEdits.isEmpty()) {
                System.out.println("No new edits to process for document with id: " + targetDocumentId);
                continue;
            }
            
            StringBuilder documentText = new StringBuilder(snapshot.getContent());
            Long highestEditId = snapshot.getLastProcessedEditId();

            for (DocumentEdit edit : newEdits) {
                int pos = Math.min(edit.getPosition(), documentText.length());
                pos = Math.max(pos, 0);

                if (edit.getActionType() == ActionType.INSERT) {
                    documentText.insert(pos, edit.getContent());
                } else if (edit.getActionType() == ActionType.DELETE) {
                    if (documentText.length() > 0 && pos < documentText.length()) {
                        documentText.deleteCharAt(pos);
                    }
                }
                highestEditId = edit.getId();
            }

            snapshot.setContent(documentText.toString());
            snapshot.setLastProcessedEditId(highestEditId);
            snapshotRepository.save(snapshot);
    	}
    }
}
