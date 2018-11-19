package ru.sbt.mipt.oop.sensors;

import com.coolcompany.smarthome.events.CCSensorEvent;

public class
AdapterFromApiEventToSensorEvent {

    private CCSensorEvent apiEvent;

    public AdapterFromApiEventToSensorEvent(CCSensorEvent apiEvent) {
        this.apiEvent = apiEvent;
    }

    //Это надо как-то исправить
    private SensorEventType transformTypes(String eventType) {
        switch (eventType) {
            case("LightIsOn"):
                return SensorEventType.LIGHT_ON;
            case("LightIsOff"):
                return SensorEventType.LIGHT_OFF;
            case("DoorIsOpen"):
                return SensorEventType.DOOR_OPEN;
            case("DoorIsClosed"):
                return SensorEventType.DOOR_CLOSED;
            case("DoorIsLocked"):
                return null;
            case("DoorIsUnlocked"):
                return null;
            default:
                return null;
                //Что делать с Alarms events?
        }
    }


    public SensorEvent getSensorEvent() {
        SensorEventType sensorEventType = transformTypes(apiEvent.getEventType());
        SensorEvent sensorEvent = new SensorEvent(sensorEventType, apiEvent.getObjectId());
        return sensorEvent;
    }
}
