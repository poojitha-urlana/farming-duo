package com.farming.system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.farming.system.Model.SensorData;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
	   SensorData findTopByOrderByIdDesc();
	    Page<SensorData> findAll(Pageable pageable);
	    List<SensorData> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
	    

}
