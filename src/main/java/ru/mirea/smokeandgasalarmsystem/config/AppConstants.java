package ru.mirea.smokeandgasalarmsystem.config;

public class AppConstants {
    public static final String TOPIC_GAS_SENSOR = "/devices/gas-sensor";
    public static final String TOPIC_SMOKE_SENSOR = "/devices/smoke-sensor";
    public static final String TOPIC_ALARM = "/devices/alarm";
    public static final String SERVER_URI = "tcp://localhost:1883";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "123456";

    public static final String SMOKE_ALARM = "SMOKE ALARM!";
    public static final String GAS_ALARM = "GAS ALARM!";
    public static final String RESOLVE_STATUS_NO = "NOT RESOLVED";
    public static final String RESOLVE_STATUS_OK = "RESOLVED";
}
