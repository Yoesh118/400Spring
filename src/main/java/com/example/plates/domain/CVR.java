package com.example.plates.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class CVR {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
     Long id;

    String username;
    String address;
    String natId;
    String vehicleType;
    String numPlate;
    String engineNo;
    String chassisNo;
    String exemptionStatus;
    Double amtToCharge;

    @Column(name = "created_at")
    @CreationTimestamp
     LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;


    public CVR() {
    }

    public CVR(String numPlate) {
    }


    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getNatId() {

        return natId;
    }

    public void setNatId(String natId) {

        this.natId = natId;
    }

    public String getVehicleType() {

        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {

        this.vehicleType = vehicleType;
    }

    public String getNumPlate() {

        return numPlate;
    }

    public void setNumPlate(String numPlate) {

        this.numPlate = numPlate;
    }

    public Double getAmtToCharge() {

        return amtToCharge;
    }

    public void setAmtToCharge(Double amtToCharge) {

        this.amtToCharge = amtToCharge;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {

        this.engineNo = engineNo;
    }

    public String getChassisNo()
    {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {

        this.chassisNo = chassisNo;
    }

    public String getExemptionStatus() {
        return exemptionStatus;
    }

    public void setExemptionStatus(String exemptionStatus) {

        this.exemptionStatus = exemptionStatus;
    }
}
