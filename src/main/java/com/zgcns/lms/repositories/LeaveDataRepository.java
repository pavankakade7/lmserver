package com.zgcns.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zgcns.lms.model.LeaveData;

@Repository
public interface LeaveDataRepository extends JpaRepository<LeaveData, Integer> {
}