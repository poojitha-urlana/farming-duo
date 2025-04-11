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

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();  // Auto-set timestamp before persisting
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
}