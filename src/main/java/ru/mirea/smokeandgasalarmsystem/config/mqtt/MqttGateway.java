package ru.mirea.smokeandgasalarmsystem.config.mqtt;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway
public interface MqttGateway {
    @Gateway(requestChannel = "mqttOutboundChannel")
    void sendToMqttBroker(String data, @Header(MqttHeaders.TOPIC) String topic);
}
