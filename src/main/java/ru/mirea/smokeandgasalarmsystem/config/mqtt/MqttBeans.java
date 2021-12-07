package ru.mirea.smokeandgasalarmsystem.config.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.mirea.smokeandgasalarmsystem.service.MessageHandlerComponent;

import static ru.mirea.smokeandgasalarmsystem.config.AppConstants.*;

@Configuration
@EnableScheduling
@EnableIntegration
public class MqttBeans {
    @Autowired
    private MessageHandlerComponent messageHandlerComponent;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

        options.setServerURIs(new String[]{SERVER_URI});
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());
        options.setCleanSession(true);
        factory.setConnectionOptions(options);

        return factory;
    }

    @Bean
    public IntegrationFlow mqttInboundSmoke() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("smokeIn",
                mqttClientFactory(), TOPIC_SMOKE_SENSOR);

        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        return IntegrationFlows.from(adapter)
                .handle(message -> messageHandlerComponent.handleSmokeSensorData((String) message.getPayload()))
                .get();
    }

    @Bean
    public IntegrationFlow mqttInboundGas() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("gasIn",
                mqttClientFactory(), TOPIC_GAS_SENSOR);

        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        return IntegrationFlows.from(adapter)
                .handle(message -> messageHandlerComponent.handleGasSensorData((String) message.getPayload()))
                .get();
    }

    @Bean
    public IntegrationFlow mqttInboundAlarm() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("alarmIn",
                mqttClientFactory(), TOPIC_ALARM);

        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        return IntegrationFlows.from(adapter)
                .handle(message -> messageHandlerComponent.handleAlarmData((String) message.getPayload()))
                .get();
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("serverOut", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("#");
        messageHandler.setDefaultRetained(false);
        return messageHandler;
    }
}
