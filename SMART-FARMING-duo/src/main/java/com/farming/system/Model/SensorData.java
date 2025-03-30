package com.farming.system.Model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_data")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float temperature;
    private float humidity;
    private int soilMoisture;
    private boolean relayState;

    private LocalDateTime timestamp;

    public SensorData() {
        this.timestamp = LocalDateTime.now();
    }

    public SensorData(float temperature, float humidity, int soilMoisture, boolean relayState) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.soilMoisture = soilMoisture;
        this.relayState = relayState;
    
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public float getTemperature() { return temperature; }
    public void setTemperature(float temperature) { this.temperature = temperature; }

    public float getHumidity() { return humidity; }
    public void setHumidity(float humidity) { this.humidity = humidity; }

    public int getSoilMoisture() { return soilMoisture; }
    public void setSoilMoisture(int soilMoisture) { this.soilMoisture = soilMoisture; }

    public boolean isRelayState() { return relayState; }
    public void setRelayState(boolean relayState) { this.relayState = relayState; }

 

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    @Override
    public String toString() {
        return "SensorData{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", soilMoisture=" + soilMoisture +
                ", relayState=" + relayState +
                '}';
    }
}
