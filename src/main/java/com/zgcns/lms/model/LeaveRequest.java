package com.zgcns.lms.model;



import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
// @AllArgsConstructor
// @NoArgsConstructor
@Entity
@Table(name = "leaveRequest")
public class LeaveRequest {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String leaveType;
    private String reason;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;


    @ManyToOne(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;


//	@ManyToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "user_id")
//	private User user;
	public Long getLeaveId() {
		return id;
	}

	public void setLeaveId(Long leaveId) {
		this.id = leaveId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LeaveRequest(Long id, LocalDate startDate, LocalDate endDate, String leaveType, String reason,
			RequestStatus status, Employee employee) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
		this.reason = reason;
		this.status = status;
		this.employee = employee;
	}

	public LeaveRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LeaveRequest [leaveId=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", leaveType="
				+ leaveType + ", reason=" + reason + ", status=" + status + ", employee=" + employee + "]";
	}

	 public Long getEmployeeId() {
	        if (employee != null) {
	            return employee.getEmpId();
	        } else {
	            return null;
	        }
	    }

	  public Long getEmpId() {
	        return employee != null ? employee.getEmpId() : null;
	    }

	    public String getFirstName() {
	        return employee != null ? employee.getFirstName() : null;
	    }

	    public String getLastName() {
	        return employee != null ? employee.getLastName() : null;
	    }

	    public String getEmail() {
	        return employee != null ? employee.getEmail() : null;
	    }





}
