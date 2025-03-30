package com.farming.system.Controller;


import org.springframework.web.bind.annotation.*;

import com.farming.system.Model.SensorData;
import com.farming.system.Service.SensorDataService;

import java.util.List;
@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/sensor-data")
public class SensorDataController {

    private final SensorDataService sensorDataService;

    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @PostMapping
    public SensorData saveSensorData(@RequestBody SensorData sensorData) {
        return sensorDataService.saveSensorData(sensorData);
    }

    @GetMapping
    public List<SensorData> getAllSensorData() {
        return sensorDataService.getAllSensorData();
    }
}
