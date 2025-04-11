package com.farming.system.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farming.system.Model.SensorData;
import com.farming.system.Repository.SensorDataRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sensor-data")
public class SensorDataController {

    private static final Logger logger = LoggerFactory.getLogger(SensorDataController.class);

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @PostMapping("/post")
    public ResponseEntity<String> receiveSensorData(@RequestBody SensorData sensorData) {
        logger.info("Received sensor data: Temperature = {}, Humidity = {}, Soil Moisture = {}, Relay State = {}",
                sensorData.getTemperature(), sensorData.getHumidity(), sensorData.getSoilMoisture(), sensorData.isRelayState());

        sensorDataRepository.save(sensorData);
        return ResponseEntity.ok("Data received successfully!");
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestSensorData() {
        SensorData latestData = sensorDataRepository.findTopByOrderByIdDesc();
        if (latestData == null) {
            return ResponseEntity.status(404).body("No sensor data available.");
        }
        return ResponseEntity.ok(latestData);
    }

    @GetMapping("/alldata")
    public ResponseEntity<List<SensorData>> getAllSensorData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<SensorData> dataPage = sensorDataRepository.findAll(pageable);

        return ResponseEntity.ok(dataPage.getContent());
    }
}