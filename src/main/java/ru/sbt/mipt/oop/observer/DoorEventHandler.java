package ru.sbt.mipt.oop.observer;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.DoorEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;

public class DoorEventHandler implements EventHandler {
    @Override
    public void update(SensorEvent event, SmartHome smartHome) {
        new HallDoorEventHandler().update(event,smartHome);
        new DoorEventProcessor().processEvent(smartHome, event);
    }
}
