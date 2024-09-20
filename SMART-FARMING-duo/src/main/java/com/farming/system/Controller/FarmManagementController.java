package com.farming.system.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farming.system.Model.FarmModel;
import com.farming.system.Service.FarmService;


@RestController
public class FarmManagementController {
	
	private FarmService farmService;
	
	@GetMapping("/farm")
    public List<FarmModel> getAllFarmingDetails() {
        return farmService.getAllFarmingDetails();
    }
    
    @PostMapping("/farm/insert")
    public  FarmModel save(FarmModel farm) {
    	return farmService.save(farm);
    }
    //update
    @PutMapping("/farm/update/{id}")
   public FarmModel updateFarm(@PathVariable long id, @RequestBody FarmModel farm) {
    	
    	return farmService.updateFarmDetail(farm, id);
    }
    
    @DeleteMapping("/farm/delete/{id}")
    public ResponseEntity<Void> DeleteFarmById(@PathVariable long id) {
        return farmService.DeleteFarmById(id);
    }

}
