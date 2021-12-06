package ru.mirea.smokeandgasalarmsystem.config;

public class AppConstants {
    public static final String TOPIC_MOTION = "/devices/wb-msw-v3_21/controls/Current Motion";
    public static final String TOPIC_VOC = "/devices/wb-msw-v3_21/controls/Voc";
    public static final String TOPIC_HUMIDITY = "/devices/wb-msw-v3_21/controls/Humidity";
    public static final String TOPIC_TEMPERATURE = "/devices/wb-msw-v3_21/controls/Temperature";
    public static final String [] TOPIC_NAMES = {TOPIC_MOTION, TOPIC_VOC, TOPIC_HUMIDITY, TOPIC_TEMPERATURE};

    public static final String TOPIC_GAS_SENSOR = "/devices/gas-sensor";
    public static final String TOPIC_SMOKE_SENSOR = "/devices/smoke-sensor";
    public static final String SERVER_URI = "tcp://localhost:1883";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "123456";

    public static final int DEVICE_ID = 21;
}
