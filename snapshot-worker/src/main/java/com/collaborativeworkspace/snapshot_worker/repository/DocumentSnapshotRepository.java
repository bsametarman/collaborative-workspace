package com.collaborativeworkspace.snapshot_worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collaborativeworkspace.snapshot_worker.entity.DocumentSnapshot;

@Repository
public interface DocumentSnapshotRepository extends JpaRepository<DocumentSnapshot, Long>{
	DocumentSnapshot findByDocumentId(Long documentId);
}
