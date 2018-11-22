package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.observer.EventsManager;
import ru.sbt.mipt.oop.observer.HandlerManager;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

public class AdapterFromObserverEventManagerToApiEventManager implements EventsManager {

    private SensorEventsManager sensorEventsManager;
    private HandlerManager events;

    public AdapterFromObserverEventManagerToApiEventManager(HandlerManager events) {
        this.events = events;
        this.sensorEventsManager = new SensorEventsManager();
    }

    @Override
    public void runEventsCycle(SmartHome smartHome) {
        sensorEventsManager.registerEventHandler(apiEvent -> {
            SensorEvent event = new AdapterFromApiEventToSensorEvent(apiEvent).getSensorEvent();
            for (SensorEventType typeEventFromSubscribe : events.getListeners().keySet()) {
                if (typeEventFromSubscribe.equals(event.getType())) {
                    events.notify(event, smartHome);
                }
            }
        });
        sensorEventsManager.start();
    }
}
