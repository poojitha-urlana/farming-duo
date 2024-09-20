package com.farming.system.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;

import com.farming.system.Model.FarmModel;
import com.farming.system.Repository.FarmRepo;

@Service
public class FarmService {
	
	@Autowired
	private FarmRepo farmRepo;

	//Retrieve all farm list
	 public List<FarmModel> getAllFarmingDetails() {
	        return farmRepo.findAll();
	    }
	    
	    //for insertion  
	    public FarmModel save(FarmModel farm) {
	    	
	    	return farmRepo.save(farm);
	    }
	    
	    //for update
	    public FarmModel updateFarmDetail(FarmModel updateFarm,long id) {
	    	
	    	Optional<FarmModel>existingFarm = farmRepo.findById(id);
	    	
	    	if(existingFarm.isPresent()) {
	    		FarmModel farm = existingFarm.get();
	    		
	    		farm.setFarmName(updateFarm.getFarmName());
	    		farm.setFarmSize(updateFarm.getFarmSize());
	    		farm.setFarmStatus(updateFarm.getFarmStatus());
	    		farm.setFarmlocation(updateFarm.getFarmlocation());
	    		
	    		return farmRepo.save(farm);
	    	}else {
	    		
	    		throw new RuntimeException("Farm is not available");
	    	}
	    	
	    } 
	    
	    //Delete FarmBy Id
	    
	    public ResponseEntity<Void> DeleteFarmById(long id) {
	        Optional<FarmModel> existingFarm = farmRepo.findById(id);
	        
	        if (existingFarm.isPresent()) {
	            farmRepo.deleteById(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

        //Get Farm By id
		public Optional<FarmModel> getFarmingDetailById(long id) {

			return farmRepo.findById(id);
		}

	}