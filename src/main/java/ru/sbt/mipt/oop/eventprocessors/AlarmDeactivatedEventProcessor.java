package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

public class AlarmDeactivatedEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        smartHome.getAlarm().deactivateAlarm();
    }
}
