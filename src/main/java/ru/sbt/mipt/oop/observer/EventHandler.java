package ru.sbt.mipt.oop.observer;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

public interface EventHandler {
    void update(SensorEvent event, SmartHome smartHome);
}
