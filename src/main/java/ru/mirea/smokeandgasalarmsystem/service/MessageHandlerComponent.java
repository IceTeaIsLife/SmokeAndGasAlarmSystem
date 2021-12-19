package ru.mirea.smokeandgasalarmsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mirea.smokeandgasalarmsystem.config.mqtt.MqttGateway;
import ru.mirea.smokeandgasalarmsystem.model.domain.GasData;
import ru.mirea.smokeandgasalarmsystem.model.domain.SensorStatusEnum;
import ru.mirea.smokeandgasalarmsystem.model.domain.SmokeData;
import ru.mirea.smokeandgasalarmsystem.service.devices.AlarmDevice;

import static ru.mirea.smokeandgasalarmsystem.config.AppConstants.*;

@Component
public class MessageHandlerComponent {

    @Autowired
    MqttGateway mqttGateway;

    @Autowired
    private AlarmDevice alarmDevice;

    public void handleSmokeSensorData(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SmokeData smokeData = objectMapper.readValue(message, SmokeData.class);
            if ((smokeData.getTemperature() > 70) || smokeData.getStatus().equals(SensorStatusEnum.CRITICAL)) {
                mqttGateway.sendToMqttBroker(SMOKE_ALARM, TOPIC_ALARM);
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
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void handleAlarmData(String message) {
        if (message.equals(GAS_ALARM)) {
            alarmDevice.enableGasAlarm(message);
        }
        else if (message.equals(SMOKE_ALARM)) {
            alarmDevice.enableSmokeAlarm(message);
        }
    }
}
