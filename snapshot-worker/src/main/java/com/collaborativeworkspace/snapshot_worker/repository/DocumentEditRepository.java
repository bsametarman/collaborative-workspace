package com.collaborativeworkspace.snapshot_worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collaborativeworkspace.snapshot_worker.entity.DocumentEdit;

@Repository
public interface DocumentEditRepository extends JpaRepository<DocumentEdit, Long>{

}
