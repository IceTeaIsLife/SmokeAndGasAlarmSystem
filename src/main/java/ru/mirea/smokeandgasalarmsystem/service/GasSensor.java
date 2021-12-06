package ru.mirea.smokeandgasalarmsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mirea.smokeandgasalarmsystem.config.MqttGateway;
import ru.mirea.smokeandgasalarmsystem.model.GasData;
import ru.mirea.smokeandgasalarmsystem.model.SensorStatusEnum;

import static ru.mirea.smokeandgasalarmsystem.config.AppConstants.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class GasSensor {

    @Autowired
    private MqttGateway mqttGateway;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void sendDataToBroker() throws JsonProcessingException {
        Random random = new Random();
        GasData gasData = new GasData();
        ObjectMapper objectMapper = new ObjectMapper();
        int randomValue = random.nextInt(10) + 1;
        if (randomValue == 9) {
            gasData.setStatus(SensorStatusEnum.CRITICAL);
        } else {
            gasData.setStatus(SensorStatusEnum.OK);
        }
        mqttGateway.sendToMqttBroker(objectMapper.writeValueAsString(gasData), TOPIC_GAS_SENSOR);
    }
}
