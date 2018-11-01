package ru.sbt.mipt.oop.sensors;

public class SensorEvent {
    private final SensorEventType type;
    private final String objectId;
    private String password;

    public SensorEvent(SensorEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
        this.password = "12345";
    }

    public SensorEventType getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
