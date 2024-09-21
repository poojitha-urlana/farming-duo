package com.farming.system.Service;

import com.farming.system.Model.Farm;
import com.farming.system.Repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmService {

    @Autowired
    private FarmRepository farmRepository;

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    public Optional<Farm> getFarmById(Long id) {
        return farmRepository.findById(id);
    }

    public Farm createFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    public Farm updateFarm(Long id, Farm updatedFarm) {
        updatedFarm.setFarmId(id);
        return farmRepository.save(updatedFarm);
    }

    public void deleteFarm(Long id) {
        farmRepository.deleteById(id);
    }
}
