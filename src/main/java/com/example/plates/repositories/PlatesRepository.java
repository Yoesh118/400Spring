package com.example.plates.repositories;

import com.example.plates.domain.CVR;
import com.example.plates.domain.Insurance;
import com.example.plates.domain.Payments;
import com.example.plates.domain.Plates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlatesRepository extends JpaRepository<Plates, Long> {


    @Query("SELECT p from Plates p where p.numPlate=:numPlate ORDER BY p.id DESC")
    List<Plates> findByNumPlate(@Param("numPlate") String numPlate);


    @Query("SELECT c from CVR c where c.numPlate=:numPlate ORDER BY c.id DESC")
    List<CVR> findPaymentsByNumPlate(@Param("numPlate") String numPlate);

    @Query("SELECT i from Insurance i where i.plateNo=:plateNo ORDER BY i.id DESC")
    List<Insurance> findPaymentsByPlate(@Param("plateNo") String plateNo);

    @Query("SELECT p from Payments p where p.plateNo=:plateNo ORDER BY p.id DESC")
    List<Payments> findPaymentsByPlateNo(@Param("plateNo") String plateNo);

    @Query("SELECT p.numPlate from Plates p where p.id=:id")
    List<String> findByPId(@Param("id") Long id);

    @Modifying
    @Query("delete from Plates c where c.id=:id")
    void deleteP(@Param("id") Long id);

    @Modifying
    @Query("update Plates x set x.numPlate=:numPlate where x.id=:id")
    void updateP(@Param("numPlate") String numPlate, @Param(value = "id") Long id);
}

