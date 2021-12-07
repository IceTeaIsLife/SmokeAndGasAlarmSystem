package ru.mirea.smokeandgasalarmsystem.model.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SensorStatusEnum {
    CRITICAL("CRITICAL"),
    OK("OK");

    private String value;

    SensorStatusEnum(String value) {
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
