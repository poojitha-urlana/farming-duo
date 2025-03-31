package com.farming.system.Service;

import com.farming.system.Model.SensorData;
import com.farming.system.Repository.SensorDataRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;

    @Autowired
    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public void saveSensorData(SensorData sensorData) {
        sensorDataRepository.save(sensorData);
    }

    public SensorData getLatestSensorData() {
        return sensorDataRepository.findTopByOrderByIdDesc();
    }

    public Page<SensorData> getAllSensorData(Pageable pageable) {
        return sensorDataRepository.findAll(pageable);
    }
    
 // Method to get data for a specific time period
    public List<SensorData> getSensorDataByTimePeriod(LocalDateTime start, LocalDateTime end) {
        return sensorDataRepository.findByTimestampBetween(start, end);
    }
}
