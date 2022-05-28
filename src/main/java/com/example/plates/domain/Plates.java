package com.example.plates.domain;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class Plates {
    @ManyToOne
    @JoinColumn(name = "plateNo")
    public Payments payments;
    @Id
    @GeneratedValue
    Long id;

    @Lob
    byte[] content;

    @Lob
    byte[] content2;

    String numPlate;
    String newPlate;

    String passed;

    @Column(name = "created_at")
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "updatedAt")
    @CreationTimestamp
    LocalDateTime updatedAt;



    public Payments getPayments() {
        return payments;
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

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public byte[] getContent() {

        return content;
    }

    public String generateBase64(){
        return Base64.getEncoder().encodeToString(this.getContent());
    }

    public void setContent(byte[] content) {

        this.content = content;
    }

    public byte[] getContent2() {
        return content2;
    }

    public void setContent2(byte[] content2) {
        this.content2 = content2;
    }

    public String getNumPlate() {

        return numPlate;
    }

    public void setNumPlate(String numPlate) {

        this.numPlate = numPlate;
    }

    public String getPassed() {

        return passed;
    }

    public void setPassed(String passed) {

        this.passed = passed;
    }

    public String getNewPlate() {
        return newPlate;
    }

    public void setNewPlate(String newPlate) {
        this.newPlate = newPlate;
    }
}
