package com.zgcns.lms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zgcns.lms.model.LeaveData;
import com.zgcns.lms.repositories.LeaveDataRepository;

@Service
public class LeaveDataService {

    @Autowired
    private LeaveDataRepository leaveDataRepository;

    public List<LeaveData> getAllLeaveData() {
        return leaveDataRepository.findAll();
    }

    public Optional<LeaveData> getLeaveDataById(int id) {
        return leaveDataRepository.findById(id);
    }

    public LeaveData saveLeaveData(LeaveData leaveData) {
        return leaveDataRepository.save(leaveData);
    }

    public LeaveData updateLeaveData(int id, LeaveData leaveData) {
        Optional<LeaveData> existingLeaveData = leaveDataRepository.findById(id);
        if (existingLeaveData.isPresent()) {
            LeaveData updatedLeaveData = existingLeaveData.get();
            updatedLeaveData.setCasualLeaves(leaveData.getCasualLeaves());
            updatedLeaveData.setMedicalLeaves(leaveData.getMedicalLeaves());
            updatedLeaveData.setPrivilegedLeaves(leaveData.getPrivilegedLeaves());
            updatedLeaveData.setUnpaidLeaves(leaveData.getUnpaidLeaves());
            return leaveDataRepository.save(updatedLeaveData);
        } else {
            throw new RuntimeException("LeaveData with id " + id + " not found");
        }
    }

    public void deleteLeaveData(int id) {
        leaveDataRepository.deleteById(id);
    }
}
