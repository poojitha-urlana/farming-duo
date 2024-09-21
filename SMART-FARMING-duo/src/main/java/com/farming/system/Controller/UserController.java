package com.farming.system.Controller;

import com.farming.system.Model.Farm;
import com.farming.system.Model.Sensor;
import com.farming.system.Service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/farms")
public class UserController {

    @Autowired
    private FarmService farmService;

    // Get all farms
    @GetMapping
    public List<Farm> getAllFarms() {
        return farmService.getAllFarms();
    }

    // Get a farm by ID
    @GetMapping("/{id}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long id) {
        Optional<Farm> farm = farmService.getFarmById(id);
        return farm.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create or update a farm
    @PostMapping
    public Farm saveFarm(@RequestBody Farm farm) {
        return farmService.saveFarm(farm);
    }

    // Delete a farm
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
        return ResponseEntity.noContent().build();
    }

    // Add sensor to a specific farm
    @PostMapping("/{farmId}/sensors")
    public ResponseEntity<Sensor> addSensorToFarm(@PathVariable Long farmId, @RequestBody Sensor sensor) {
        Sensor createdSensor = farmService.addSensorToFarm(farmId, sensor);
        if (createdSensor != null) {
            return ResponseEntity.ok(createdSensor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get sensors for a specific farm
    @GetMapping("/{farmId}/sensors")
    public List<Sensor> getSensorsByFarmId(@PathVariable Long farmId) {
        return farmService.getSensorsByFarmId(farmId);
    }
}
