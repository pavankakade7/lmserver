package com.zgcns.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgcns.lms.model.LeaveData;
import com.zgcns.lms.services.LeaveDataService;

@RestController
@RequestMapping("/api/leavedata")
public class LeaveDataController {

    @Autowired
    private LeaveDataService leaveDataService;

    @GetMapping
    public List<LeaveData> getAllLeaveData() {
        return leaveDataService.getAllLeaveData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveData> getLeaveDataById(@PathVariable int id) {
        Optional<LeaveData> leaveData = leaveDataService.getLeaveDataById(id);
        return leaveData.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public LeaveData createLeaveData(@RequestBody LeaveData leaveData) {
        return leaveDataService.saveLeaveData(leaveData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveData> updateLeaveData(@PathVariable int id, @RequestBody LeaveData leaveData) {
        try {
            LeaveData updatedLeaveData = leaveDataService.updateLeaveData(id, leaveData);
            return ResponseEntity.ok(updatedLeaveData);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveData(@PathVariable int id) {
        leaveDataService.deleteLeaveData(id);
        return ResponseEntity.ok().build();
    }
}
