package ru.mirea.smokeandgasalarmsystem.service.devices.sensors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mirea.smokeandgasalarmsystem.config.mqtt.MqttGateway;
import ru.mirea.smokeandgasalarmsystem.model.domain.SensorStatusEnum;
import ru.mirea.smokeandgasalarmsystem.model.domain.SmokeData;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static ru.mirea.smokeandgasalarmsystem.config.AppConstants.TOPIC_SMOKE_SENSOR;

@Component
public class SmokeSensor {

    @Autowired
    private MqttGateway mqttGateway;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void sendDataToBroker() throws JsonProcessingException {
        Random random = new Random();
        SmokeData smokeData = new SmokeData();
        ObjectMapper objectMapper = new ObjectMapper();
        int randomValue = random.nextInt(30) + 1;
        float temperature = Math.round(((random.nextFloat() * 7F + 22F) * 100.0) / 100.0);
        smokeData.setTemperature(temperature);
        smokeData.setStatus(SensorStatusEnum.OK);
        if (randomValue == 5) {
            smokeData.setStatus(SensorStatusEnum.CRITICAL);
        } else if (randomValue == 15) {
            smokeData.setTemperature(temperature + 50F);
        } else if (randomValue == 25) {
            smokeData.setStatus(SensorStatusEnum.CRITICAL);
            smokeData.setTemperature(temperature + 50F);
        }
        mqttGateway.sendToMqttBroker(objectMapper.writeValueAsString(smokeData), TOPIC_SMOKE_SENSOR);
    }
}
