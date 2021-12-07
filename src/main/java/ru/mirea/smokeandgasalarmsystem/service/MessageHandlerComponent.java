package ru.mirea.smokeandgasalarmsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mirea.smokeandgasalarmsystem.config.MqttGateway;
import ru.mirea.smokeandgasalarmsystem.model.GasData;
import ru.mirea.smokeandgasalarmsystem.model.SensorStatusEnum;
import ru.mirea.smokeandgasalarmsystem.model.SmokeData;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static ru.mirea.smokeandgasalarmsystem.config.AppConstants.*;

@Component
public class MessageHandlerComponent {

    private static final Logger logger = LoggerFactory.getLogger(MessageHandlerComponent.class);

    @Autowired
    MqttGateway mqttGateway;

    @Autowired
    private AlarmDevice alarmDevice;

    protected static Map<String, String> messageMap = new HashMap<>();

    public static void putMessageToMap(String topic, String message) {
        messageMap.put(topic, message);
    }

    @Scheduled(initialDelay = 5, fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void handleIncomingData() {
        handleSmokeSensorData(messageMap.get(TOPIC_SMOKE_SENSOR));
        handleGasSensorData(messageMap.get(TOPIC_GAS_SENSOR));
        if (messageMap.get(TOPIC_ALARM) != null) {
            handleAlarmData(messageMap.get(TOPIC_ALARM));
        }
    }

    public void handleSmokeSensorData(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SmokeData smokeData = objectMapper.readValue(message, SmokeData.class);
            if ((smokeData.getTemperature() > 70) || smokeData.getStatus().equals(SensorStatusEnum.CRITICAL)) {
                mqttGateway.sendToMqttBroker(SMOKE_ALARM, TOPIC_ALARM);
            } else {
                mqttGateway.sendToMqttBroker(OK, TOPIC_ALARM);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void handleGasSensorData(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            GasData gasData = objectMapper.readValue(message, GasData.class);
            if (gasData.getStatus().equals(SensorStatusEnum.CRITICAL)) {
                mqttGateway.sendToMqttBroker(GAS_ALARM, TOPIC_ALARM);
            } else {
                mqttGateway.sendToMqttBroker(OK, TOPIC_ALARM);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void handleAlarmData(String message) {
        if (message.equals(GAS_ALARM) || message.equals(SMOKE_ALARM)) {
            alarmDevice.enableAlarm(message);
        }
    }
}
