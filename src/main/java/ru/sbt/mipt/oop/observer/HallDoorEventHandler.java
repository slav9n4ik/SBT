package ru.sbt.mipt.oop.observer;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;

public class HallDoorEventHandler implements EventHandler {
    @Override
    public void update(SensorEvent event, SmartHome smartHome) {
        new HallDoorEventProcessor().processEvent(smartHome, event);
    }
}
