package ru.mirea.smokeandgasalarmsystem.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.decimal4j.util.DoubleRounder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mirea.smokeandgasalarmsystem.model.*;
import ru.mirea.smokeandgasalarmsystem.config.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static ru.mirea.smokeandgasalarmsystem.config.AppConstants.*;

@Component
public class MessagePackerService {

    private static final Logger logger = LoggerFactory.getLogger(MessagePackerService.class);

    @Autowired
    MqttGateway mqttGateway;

    private List<Message> data = new ArrayList<>();

    protected static Map<String, String> messageMap = new HashMap<>();

    public static void putMessageToMap(String topic, String message) {
        messageMap.put(topic, message);
    }




}
