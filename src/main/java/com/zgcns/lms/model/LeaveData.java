package com.zgcns.lms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LeaveData {
		
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
        int  id;
        int casualLeaves;
        int  medicalLeaves;
        int privilegedLeaves;
        int unpaidLeaves;

    public int getId() {
        return id;
    }

    public int getCasualLeaves() {
        return casualLeaves;
    }

    public int getMedicalLeaves() {
        return medicalLeaves;
    }

    public int getPrivilegedLeaves() {
        return privilegedLeaves;
    }

    public int getUnpaidLeaves() {
        return unpaidLeaves;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCasualLeaves(int casualLeaves) {
        this.casualLeaves = casualLeaves;
    }

    public void setMedicalLeaves(int medicalLeaves) {
        this.medicalLeaves = medicalLeaves;
    }

    public void setPrivilegedLeaves(int privilegedLeaves) {
        this.privilegedLeaves = privilegedLeaves;
    }

    public void setUnpaidLeaves(int unpaidLeaves) {
        this.unpaidLeaves = unpaidLeaves;
    }

    public LeaveData(int id, int casualLeaves, int medicalLeaves, int privilegedLeaves, int unpaidLeaves) {
        this.id = id;
        this.casualLeaves = casualLeaves;
        this.medicalLeaves = medicalLeaves;
        this.privilegedLeaves = privilegedLeaves;
        this.unpaidLeaves = unpaidLeaves;
    }

    public LeaveData(){

    }

    @Override
    public String toString() {
        return "LeaveData{" +
                "id=" + id +
                ", casualLeaves=" + casualLeaves +
                ", medicalLeaves=" + medicalLeaves +
                ", privilegedLeaves=" + privilegedLeaves +
                ", unpaidLeaves=" + unpaidLeaves +
                '}';
    }
}
