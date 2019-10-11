package ru.sbt.mipt.oop.observer;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventProvider;
import ru.sbt.mipt.oop.sensors.SensorEventType;

public class HomeEventsObserver implements EventsManager{

    private HandlerManager events;
    private SensorEventProvider sensorEventProvider;

    public HomeEventsObserver(SensorEventProvider sensorEventProvider, HandlerManager events) {
        this.events = events;
        this.sensorEventProvider = sensorEventProvider;
    }

    @Override
    public void runEventsCycle(SmartHome smartHome) {

        SensorEvent event = sensorEventProvider.getNextSensorEvent();
        while (event != null) {
            System.out.println("Event type [" + event.getType() + "] from object with id=" + event.getObjectId() + "]");
            for (SensorEventType typeEventFromSubscribe : events.getListeners().keySet()) {
                if (typeEventFromSubscribe.equals(event.getType())) {
                    events.notify(event, smartHome);
                }
            }
            event = sensorEventProvider.getNextSensorEvent();
        }
    }
}
