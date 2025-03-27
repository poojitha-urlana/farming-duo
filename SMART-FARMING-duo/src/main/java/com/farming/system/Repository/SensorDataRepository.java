package com.farming.system.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.farming.system.Model.SensorData;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
 
    SensorData findTopByOrderByIdDesc();
}