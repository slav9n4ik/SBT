package ru.sbt.mipt.oop.processing;

import ru.sbt.mipt.oop.PlotClass;
import ru.sbt.mipt.oop.RandomSensorEventProvider;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public class EventProcessing {
    public static void runEventsCycle(SmartHome smartHome) {
        // начинаем цикл обработки событий
        SensorEvent event = RandomSensorEventProvider.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            PlotClass.loadPlotOfExecuting(smartHome, event);
            event = RandomSensorEventProvider.getNextSensorEvent();
        }
    }

}
