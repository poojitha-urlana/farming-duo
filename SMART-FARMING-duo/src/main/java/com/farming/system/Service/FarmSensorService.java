package com.farming.system.Service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farming.system.Model.SensorData;
import com.farming.system.Repository.SensorDataRepository;

@Service
public class FarmSensorService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    public void saveSensorData(SensorData sensorData) {
        if (sensorData.getTimestamp() == null) {
            sensorData.setTimestamp(LocalDateTime.now());
        }
        sensorDataRepository.save(sensorData);
    }
}
