package com.example.plates;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RegisterVehicle {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
     Long id;

    String username;
    String natId;
    String vehicleType;
    String numPlate;
    Double amtToCharge;
    Double totAmt;

    public RegisterVehicle() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getTotAmt() {
        return totAmt;
    }

    public void setTotAmt(Double totAmt) {
        this.totAmt = totAmt;
    }
}
