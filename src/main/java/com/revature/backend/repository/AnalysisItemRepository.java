package com.revature.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.backend.model.AnalysisItem;

@Repository
public interface AnalysisItemRepository extends JpaRepository<AnalysisItem, Integer>{

}
