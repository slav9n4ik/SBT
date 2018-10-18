package ru.sbt.mipt.oop.observer;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.sensors.RandomSensorEventProvider;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

public class HomeEventsObserver {

    public EventManager events;

    public HomeEventsObserver() {
        this.events = new EventManager(SensorEventType.values());
    }

    public void runEventsCycle(SmartHome smartHome) {
        SensorEvent event = RandomSensorEventProvider.getNextSensorEvent();
//        Collection<EventProcessor> eventProcessors = configureEventProcessors();
        while (event != null) {
            System.out.println("Got event: " + event);
//            for (EventProcessor eventProcessor : eventProcessors) {
//                eventProcessor.processEvent(smartHome, event);
//            }
            for (SensorEventType typeEventFromSubscribe : events.listeners.keySet()) {
                if (typeEventFromSubscribe.equals(event.getType())) {
                    events.notify(event, smartHome);
                }
            }
            event = RandomSensorEventProvider.getNextSensorEvent();
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
