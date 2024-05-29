package com.zgcns.lms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zgcns.lms.model.Employee;
import com.zgcns.lms.model.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployee(Employee employee);

    List<LeaveRequest> findAllByEmployee_EmpId(Long empId);
    
    
//	List<LeaveRequest> findAllLeaveRequestByUserId(Long userId);
}