package com.farming.system.Model;

import jakarta.persistence.*;

@Entity
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farmId;

    private String name;
    private String location;
    private Double area; // in acres or hectares
    private String soilType;
    private String cropName;
    private String status; // e.g., Active, Inactive
    private String farmSize; // e.g., Small, Medium, Large

    // Default constructor
    public Farm() {}

    // Parameterized constructor
    public Farm(String name, String location, Double area, String soilType, String cropName, String status, String farmSize) {
        this.name = name;
        this.location = location;
        this.area = area;
        this.soilType = soilType;
        this.cropName = cropName;
        this.status = status;
        this.farmSize = farmSize;
    }

    // Getters and Setters
    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFarmSize() {
        return farmSize;
    }

    public void setFarmSize(String farmSize) {
        this.farmSize = farmSize;
    }
}
