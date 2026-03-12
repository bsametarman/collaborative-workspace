package com.collaborativeworkspace.snapshot_worker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.collaborativeworkspace.snapshot_worker.entity.DocumentEdit;

@Repository
public interface DocumentEditRepository extends JpaRepository<DocumentEdit, Long>{
	List<DocumentEdit> findByDocumentIdAndIdGreaterThanOrderByIdAsc(String documentId, Long lastId);
	@Query("SELECT DISTINCT d.documentId FROM DocumentEdit d")
	List<String> findDistinctDocumentIds();
}
