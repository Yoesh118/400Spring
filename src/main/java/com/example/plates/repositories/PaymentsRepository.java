package com.example.plates.repositories;

import com.example.plates.domain.CVR;
import com.example.plates.domain.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {
    @Query("SELECT p from Payments p where p.plateNo=:plateNo ORDER BY p.id DESC")
    List<Payments> findPaymentsByPlateNo(@Param("plateNo") String plateNo);

    @Query("SELECT c from CVR c where c.numPlate=:numPlate ORDER BY c.id DESC")
    List<CVR> findLimitByPlateNo(@Param("numPlate") String numPlate);

    @Query("SELECT p from Payments p ORDER BY p.id DESC")
    List<Payments> findAllById();

    @Modifying
    @Query("delete from Payments p where p.id=:id")
    void deletePA(@Param("id") Long id);


}
