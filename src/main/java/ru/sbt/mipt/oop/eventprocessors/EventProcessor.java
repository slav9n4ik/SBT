package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public interface EventProcessor {

    void processEvent(SmartHome smartHome, SensorEvent event);

}
