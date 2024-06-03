package com.zgcns.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgcns.lms.model.Employee;
import com.zgcns.lms.model.LeaveRequest;
import com.zgcns.lms.services.EmployeeService;
import com.zgcns.lms.services.LeaveRequestService;
import com.zgcns.lms.services.UserService;

//@CrossOrigin("*")
@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {
	@Autowired
    private final LeaveRequestService leaveRequestService;
	@Autowired
    private final EmployeeService employeeService;
	
	@Autowired
	private final UserService userService;
    
    public LeaveRequestController(LeaveRequestService leaveRequestService, EmployeeService employeeService, UserService userService) {
        this.leaveRequestService = leaveRequestService;
		this.employeeService = employeeService;
		this.userService = userService;
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestService.getAllLeaveRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable Long id) {
        Optional<LeaveRequest> leaveRequest = leaveRequestService.getLeaveRequestById(id);
        return leaveRequest.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
  

//    @PostMapping
//    public ResponseEntity<LeaveRequest> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
//        LeaveRequest savedLeaveRequest = leaveRequestService.saveLeaveRequest(leaveRequest);
//        return new ResponseEntity<>(savedLeaveRequest, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<LeaveRequest> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        LeaveRequest savedLeaveRequest = leaveRequestService.saveLeaveRequest(leaveRequest);
        return new ResponseEntity<>(savedLeaveRequest, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequest> updateLeaveRequest(@PathVariable Long id, @RequestBody LeaveRequest leaveRequest) {
        Optional<LeaveRequest> existingLeaveRequest = leaveRequestService.getLeaveRequestById(id);
        if (existingLeaveRequest.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update the fields that are provided in the request body
        LeaveRequest existingRequest = existingLeaveRequest.get();
        if (leaveRequest.getStatus() != null) {
            existingRequest.setStatus(leaveRequest.getStatus());
        }



        LeaveRequest updatedLeaveRequest = leaveRequestService.updateLeaveRequest(id, existingRequest);
        return new ResponseEntity<>(updatedLeaveRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(@PathVariable Long id) {
        if (!leaveRequestService.getLeaveRequestById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        leaveRequestService.deleteLeaveRequestById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequest>> getLeaveRequestsByEmployee(@PathVariable Long employeeId) {
        Optional<Employee> employee = employeeService.getEmployeeById(employeeId);
        if (!employee.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<LeaveRequest> leaveRequests = leaveRequestService.getLeaveRequestsByEmployee(employee.get());
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }
    
    @GetMapping("/user/{empId}")
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequestByEmployeeId(@PathVariable Long empId) {
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequestByEmployeeId(empId);
        if (leaveRequests != null && !leaveRequests.isEmpty()) {
            return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
//    @GetMapping("user/{userId}")
//    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequestByUserId(@PathVariable Long userId) {
//       List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequestByUserId(userId);
//       if(leaveRequests != null  && !leaveRequests.isEmpty()) {
//    	   return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
//    	   
//       }else {
//    	   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//       }
//    }
    
    
    

}
