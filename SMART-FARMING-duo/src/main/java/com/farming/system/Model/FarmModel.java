package com.farming.system.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class FarmModel {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String farmName;
	    private String  farmStatus;
	    private float farmSize;
	    
	    
	    public FarmModel() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getFarmName() {
			return farmName;
		}
		public void setFarmName(String farmName) {
			this.farmName = farmName;
		}
		public String getFarmStatus() {
			return farmStatus;
		}
		public void setFarmStatus(String farmStatus) {
			this.farmStatus = farmStatus;
		}
		public float getFarmSize() {
			return farmSize;
		}
		public void setFarmSize(float farmSize) {
			this.farmSize = farmSize;
		}
		public String getFarmlocation() {
			return farmlocation;
		}
		public void setFarmlocation(String farmlocation) {
			this.farmlocation = farmlocation;
		}
		private String farmlocation;


}
