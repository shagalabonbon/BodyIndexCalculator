package com.example.calculate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.calculate.model.entity.BodyIndexRecord;

@Repository
public interface BodyIndexRecordRepository extends JpaRepository<BodyIndexRecord,Long> {
	
	
}
