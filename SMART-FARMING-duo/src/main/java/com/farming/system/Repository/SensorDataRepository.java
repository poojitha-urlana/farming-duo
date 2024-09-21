package com.farming.system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farming.system.Model.SensorData;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    // Custom query methods (if needed) can be added here
}
