
	package com.zgcns.lms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zgcns.lms.model.Employee;
import com.zgcns.lms.model.LeaveRequest;
import com.zgcns.lms.repositories.EmployeeRepository;
import com.zgcns.lms.repositories.LeaveRequestRepository;

import jakarta.transaction.Transactional;

@Service
public class LeaveRequestService {
	@Autowired
    private final LeaveRequestRepository leaveRequestRepository;
	@Autowired
	private final EmployeeRepository employeeRepository;

    
    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository ,EmployeeRepository employeeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    public Optional<LeaveRequest> getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id);
    }

//    @Transactional
//    public LeaveRequest saveLeaveRequest(LeaveRequest leaveRequest) {
//        Long empId = leaveRequest.getEmpId();
//        if (empId != null) {
//            Optional<Employee> employeeOpt = employeeRepository.findById(empId);
//            if (employeeOpt.isPresent()) {
//                leaveRequest.setEmployee(employeeOpt.get());
//            } else {
//                throw new IllegalArgumentException("Employee with id " + empId + " does not exist");
//            }
//        } else {
//            throw new IllegalArgumentException("Employee id cannot be null");
//        }
//        return leaveRequestRepository.save(leaveRequest);
//    }

    @Transactional
    public LeaveRequest saveLeaveRequest(LeaveRequest leaveRequest) {
        // Fetch the existing employee from the database using empId
        Long empId = leaveRequest.getEmployee().getEmpId();
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + empId));

        // Set the existing employee to the leave request
        leaveRequest.setEmployee(employee);

        return leaveRequestRepository.save(leaveRequest);
    }
    public LeaveRequest updateLeaveRequest(Long id, LeaveRequest leaveRequest) {
        // Save the updated leave request
        return leaveRequestRepository.save(leaveRequest);
    }
    
    public void deleteLeaveRequestById(Long id) {
        leaveRequestRepository.deleteById(id);
    }

    public List<LeaveRequest> getLeaveRequestsByEmployee(Employee employee) {
        return leaveRequestRepository.findByEmployee(employee);
    }
    
    public List<LeaveRequest>getAllLeaveRequestByEmployeeId(Long empId){
    	return leaveRequestRepository.findAllByEmployee_EmpId(empId);
    }

//	public List<LeaveRequest> getAllLeaveRequestByUserId(Long userId) {
//		return leaveRequestRepository.findAllLeaveRequestByUserId(userId);
//		
//	}
//    
}
