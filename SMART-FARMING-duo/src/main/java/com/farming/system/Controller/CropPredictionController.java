package com.farming.system.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.farming.system.Service.CropPredictionService;

@RestController
@RequestMapping("/api/crop")
@CrossOrigin(origins = "http://localhost:4200") 

public class CropPredictionController {

    @Autowired
    private CropPredictionService cropPredictionService;

    @PostMapping("/predict")
    public ResponseEntity<?> predictDisease(@RequestParam("image") MultipartFile imageFile) {
        try {
            String prediction = cropPredictionService.predictDisease(imageFile);
            return ResponseEntity.ok().body(Map.of("prediction", prediction));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}
