package com.farming.system.Controller;

import com.farming.system.Model.SensorData;
import com.farming.system.Repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sensor-data")
public class SensorDataController {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    // Fetch all sensor data
    @GetMapping
    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }

    // Fetch the latest sensor data
    @GetMapping("/latest")
    public SensorData getLatestSensorData() {
        return sensorDataRepository.findTopByOrderByIdDesc();
    }

    // Save sensor data
    @PostMapping
    public SensorData saveSensorData(@RequestBody SensorData sensorData) {
        return sensorDataRepository.save(sensorData);
    }
}
