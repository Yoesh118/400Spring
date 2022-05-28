package com.example.plates.repositories;

import com.example.plates.domain.CVR;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CVRRepository extends CrudRepository<CVR, Long> {

    @Query("SELECT c from CVR c where c.numPlate=:numPlate ORDER BY c.id DESC")
    List<CVR> findPaymentsByNumPlate(@Param("numPlate") String numPlate);

    @Modifying
    @Query("delete from CVR c where c.id=:id")
    void deleteC(@Param("id") Long id);

}
