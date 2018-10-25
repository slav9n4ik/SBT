package ru.sbt.mipt.oop.sensors;

public enum SensorEventType {
    LIGHT_ON(""), LIGHT_OFF(""), DOOR_OPEN(""), DOOR_CLOSED(""), ALARM_ACTIVATED("12345"), ALARM_DEACTIVATED("12345");

    private final String password;

    SensorEventType(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
