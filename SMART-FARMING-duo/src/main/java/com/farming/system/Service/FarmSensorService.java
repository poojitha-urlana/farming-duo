package com.farming.system.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farming.system.Model.Farm;
import com.farming.system.Model.SensorData;
import com.farming.system.Repository.FarmRepository;
import com.farming.system.Repository.SensorDataRepository;

@Service
public class FarmSensorService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    public void saveSensorData(SensorData sensorData) {
        sensorData.setTimestamp(LocalDateTime.now()); // Set the current timestamp
        sensorDataRepository.save(sensorData); // Save the data into the database
    }
}
