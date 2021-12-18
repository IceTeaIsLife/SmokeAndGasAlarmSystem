package ru.mirea.smokeandgasalarmsystem.exception;

public class AlarmInfoNotFoundException extends Exception {
    public AlarmInfoNotFoundException() {
        super("Alarms not found!");
    }

    public AlarmInfoNotFoundException(Exception e) {
        super("Alarms not found!", e);
    }
}

