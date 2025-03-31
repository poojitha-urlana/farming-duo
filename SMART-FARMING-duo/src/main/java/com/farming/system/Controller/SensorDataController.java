package com.farming.system.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farming.system.Model.SensorData;
import com.farming.system.Service.SensorDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sensor-data")
public class SensorDataController {

    private static final Logger logger = LoggerFactory.getLogger(SensorDataController.class);
    private final SensorDataService sensorDataService;

    @Autowired
    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @PostMapping("/post")
    public ResponseEntity<String> receiveSensorData(@RequestBody SensorData sensorData) {
        logger.info("Received sensor data: Temperature = {}, Humidity = {}, Soil Moisture = {}, Relay State = {}",
                sensorData.getTemperature(), sensorData.getHumidity(), sensorData.getSoilMoisture(), sensorData.isRelayState());

        try {
            sensorDataService.saveSensorData(sensorData);
            return ResponseEntity.ok("Data received successfully!");
        } catch (Exception e) {
            logger.error("Error saving sensor data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save data.");
        }
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestSensorData() {
        SensorData latestData = sensorDataService.getLatestSensorData();
        if (latestData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No sensor data available.");
        }
        return ResponseEntity.ok(latestData);
    }

    @GetMapping("/alldata")
    public ResponseEntity<Map<String, Object>> getAllSensorData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<SensorData> dataPage = sensorDataService.getAllSensorData(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", dataPage.getContent());
        response.put("currentPage", dataPage.getNumber());
        response.put("totalItems", dataPage.getTotalElements());
        response.put("totalPages", dataPage.getTotalPages());

        return ResponseEntity.ok(response);
    }
    
 // Endpoint to get data for the last week
    @GetMapping("/data/week")
    public ResponseEntity<List<SensorData>> getDataForWeek() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minus(1, ChronoUnit.WEEKS);
        LocalDateTime now = LocalDateTime.now();
        List<SensorData> data = sensorDataService.getSensorDataByTimePeriod(oneWeekAgo, now);
        return ResponseEntity.ok(data);
    }

    // Endpoint to get data for the last month
    @GetMapping("/data/month")
    public ResponseEntity<List<SensorData>> getDataForMonth() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minus(1, ChronoUnit.MONTHS);
        LocalDateTime now = LocalDateTime.now();
        List<SensorData> data = sensorDataService.getSensorDataByTimePeriod(oneMonthAgo, now);
        return ResponseEntity.ok(data);
    }
}
