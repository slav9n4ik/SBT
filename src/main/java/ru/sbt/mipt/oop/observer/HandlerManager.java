package ru.sbt.mipt.oop.observer;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerManager {
    private Map<SensorEventType, List<EventProcessor>> listeners = new HashMap<>();

    public HandlerManager(SensorEventType[] operations) {
        for (SensorEventType operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(SensorEventType eventType, EventProcessor listener) {
        List<EventProcessor> processors = listeners.get(eventType);
        processors.add(listener);
    }

//    public void unsubscribe(SensorEventType eventType, EventProcessor listener) {
//        List<EventProcessor> users = listeners.get(eventType);
//        int index = users.indexOf(listener);
//        users.remove(index);
//    }

    public void notify(SensorEvent event, SmartHome smartHome) {
        List<EventProcessor> processors = listeners.get(event.getType());
        for (EventProcessor listener : processors) {
            listener.processEvent(smartHome, event);
        }
    }

    public Map<SensorEventType, List<EventProcessor>> getListeners() {
        return listeners;
    }
}
