package com.collaborativeworkspace.snapshot_worker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collaborativeworkspace.snapshot_worker.entity.DocumentEdit;

@Repository
public interface DocumentEditRepository extends JpaRepository<DocumentEdit, Long>{
	List<DocumentEdit> findByDocumentIdAndIdGreaterThanOrderByIdAsc(Long documentId, Long lastId);
}
