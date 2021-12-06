package ru.mirea.smokeandgasalarmsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mirea.smokeandgasalarmsystem.model.*;
import ru.mirea.smokeandgasalarmsystem.config.*;
import ru.mirea.smokeandgasalarmsystem.model.entity.dao.AlarmInfoDao;

import static ru.mirea.smokeandgasalarmsystem.config.AppConstants.*;

import java.util.*;

@Component
public class MessageHandlerComponent {

    private static final Logger logger = LoggerFactory.getLogger(MessageHandlerComponent.class);

    @Autowired
    MqttGateway mqttGateway;

    @Autowired
    private AlarmInfoDao alarmInfoDao;

    private List<Message> data = new ArrayList<>();

    protected static Map<String, String> messageMap = new HashMap<>();

    public static void putMessageToMap(String topic, String message) {
        messageMap.put(topic, message);
    }

    public static void handleIncomingData(String topic, String message) {
        if (topic.equals(TOPIC_SMOKE_SENSOR)) {

        } else if (topic.equals(TOPIC_GAS_SENSOR)) {

        }
    }



}
