package ru.mirea.smokeandgasalarmsystem.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SmokeData {
    @JsonProperty("status")
    private SensorStatusEnum status;
    @JsonProperty("temperature")
    private float temperature;
}
