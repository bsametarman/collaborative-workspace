package com.collaborativeworkspace.workspace_gateway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collaborativeworkspace.workspace_gateway.entity.DocumentAccess;

import jakarta.transaction.Transactional;

@Repository
public interface DocumentAccessRepository extends JpaRepository<DocumentAccess, Long>{
	boolean existsByDocumentIdAndUserId(String documentId, String userId);
    Optional<DocumentAccess> findByDocumentIdAndUserId(String documentId, String userId);
    List<DocumentAccess> findByUserId(String userId);
    @Transactional
    void deleteByDocumentId(String documentId);
}
