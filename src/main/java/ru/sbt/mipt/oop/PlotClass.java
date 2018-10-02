package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.processing.DoorEventProcessor;
import ru.sbt.mipt.oop.processing.LightsEventProcessor;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import static ru.sbt.mipt.oop.sensor.SensorEventType.*;

public class PlotClass {
    public static void loadPlotOfExecuting(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            LightsEventProcessor.processLightEvent(smartHome, event);

        }
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            DoorEventProcessor.processDoorEvent(smartHome, event);

        }
    }
}
