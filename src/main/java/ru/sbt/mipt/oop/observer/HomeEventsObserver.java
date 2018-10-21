package ru.sbt.mipt.oop.observer;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventProvider;
import ru.sbt.mipt.oop.sensors.SensorEventType;

public class HomeEventsObserver {

    private EventManager events;
    private SensorEventProvider sensorEventProvider;

    public HomeEventsObserver(SensorEventProvider sensorEventProvider, EventManager events) {
        this.events = events;
        this.sensorEventProvider = sensorEventProvider;
    }


    public void runEventsCycle(SmartHome smartHome) {
        SensorEvent event = sensorEventProvider.getNextSensorEvent();
//        Collection<EventProcessor> eventProcessors = configureEventProcessors();
        while (event != null) {
            System.out.println("Got event: " + event);
//            for (EventProcessor eventProcessor : eventProcessors) {
//                eventProcessor.processEvent(smartHome, event);
//            }
            for (SensorEventType typeEventFromSubscribe : events.getListeners().keySet()) {
                if (typeEventFromSubscribe.equals(event.getType())) {
                    events.notify(event, smartHome);
                }
            }
            event = sensorEventProvider.getNextSensorEvent();
        }
    }

//    public Collection<EventProcessor> configureEventProcessors() {
//        Collection<EventProcessor> eventProcessors = new ArrayList<>();
//        eventProcessors.add(new LightsEventProcessor());
//        eventProcessors.add(new DoorEventProcessor());
//        eventProcessors.add(new HallDoorEventProcessor());
//        return eventProcessors;
//    }
}
