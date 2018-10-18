package ru.sbt.mipt.oop.observer;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    Map<SensorEventType, List<EventHandler>> listeners = new HashMap<>();

    public EventManager(SensorEventType[] operations) {
        for (SensorEventType operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(SensorEventType eventType, EventHandler listener) {
        List<EventHandler> processors = listeners.get(eventType);
        processors.add(listener);
    }

    public void unsubscribe(SensorEventType eventType, EventHandler listener) {
        List<EventHandler> users = listeners.get(eventType);
        int index = users.indexOf(listener);
        users.remove(index);
    }

    public void notify(SensorEvent event, SmartHome smartHome) {
        List<EventHandler> processors = listeners.get(event.getType());
        for (EventHandler listener : processors) {
            listener.update(event, smartHome);
        }
    }
}
