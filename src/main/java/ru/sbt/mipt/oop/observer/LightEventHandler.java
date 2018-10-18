package ru.sbt.mipt.oop.observer;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.LightsEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;

public class LightEventHandler implements EventHandler {
    @Override
    public void update(SensorEvent event, SmartHome smartHome) {
        new LightsEventProcessor().processEvent(smartHome, event);
    }
}
