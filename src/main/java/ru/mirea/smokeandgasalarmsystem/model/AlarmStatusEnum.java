package ru.mirea.smokeandgasalarmsystem.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AlarmStatusEnum {
    ALARM_ON("ON"),
    ALARM_OFF("OFF");

    private String value;

    AlarmStatusEnum(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
