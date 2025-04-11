package com.farming.system.Repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farming.system.Model.SensorData;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    SensorData findTopByOrderByIdDesc();
    Page<SensorData> findAll(Pageable pageable);
}