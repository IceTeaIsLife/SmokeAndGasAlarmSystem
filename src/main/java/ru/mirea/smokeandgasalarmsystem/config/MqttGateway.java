package ru.mirea.smokeandgasalarmsystem.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

//@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
@MessagingGateway
public interface MqttGateway {
    @Gateway(requestChannel = "mqttOutboundChannel")
    void sendToMqttBroker(String data, @Header(MqttHeaders.TOPIC) String topic);
}
