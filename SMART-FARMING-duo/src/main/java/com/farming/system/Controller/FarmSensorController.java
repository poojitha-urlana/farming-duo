package com.farming.system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farming.system.Model.SensorData;
import com.farming.system.Service.FarmSensorService;

@RestController
@RequestMapping("/api/sensors")
public class FarmSensorController {

    @Autowired
    private FarmSensorService sensorService;

    @PostMapping("/data")
    public ResponseEntity<String> receiveSensorData(@RequestBody SensorData sensorData) {
        sensorService.saveSensorData(sensorData);
        return new ResponseEntity<>("Sensor data received", HttpStatus.OK);
    }
}
