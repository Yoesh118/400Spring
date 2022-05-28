package com.example.plates.repositories;

import com.example.plates.domain.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    @Modifying
    @Query("delete from Insurance i where i.id=:id")
    void deleteI(@Param("id") Long id);

}
