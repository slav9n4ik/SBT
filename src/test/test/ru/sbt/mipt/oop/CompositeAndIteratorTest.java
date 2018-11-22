package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.entity.Door;
import ru.sbt.mipt.oop.entity.Light;
import ru.sbt.mipt.oop.entity.Room;
import ru.sbt.mipt.oop.eventprocessors.*;
import ru.sbt.mipt.oop.observer.HandlerManager;
import ru.sbt.mipt.oop.observer.HomeEventsObserver;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventProvider;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CompositeAndIteratorTest {

    public static class EventProvider implements SensorEventProvider {

        private int callsCount = 0;
        private List<SensorEvent> sensorEvent;

        EventProvider(List<SensorEvent> sensorEvent) {
            this.sensorEvent = sensorEvent;
        }

        @Override
        public SensorEvent getNextSensorEvent() {
            if (callsCount < sensorEvent.size()) {
                callsCount++;
                return sensorEvent.get(callsCount-1);
            }
            else {
                return null;
            }
        }
    }

    @Test
    public void testDoorState() throws IOException {
        HandlerManager listenerManager = new HandlerManager(SensorEventType.values());
        makeNotifications(listenerManager);

        ArrayList<SensorEvent> events = new ArrayList<>();

        events.add(new SensorEvent(SensorEventType.LIGHT_ON, "1"));
        events.add(new SensorEvent(SensorEventType.LIGHT_ON, "2"));
        events.add(new SensorEvent(SensorEventType.LIGHT_ON, "3"));
        events.add(new SensorEvent(SensorEventType.LIGHT_ON, "4"));
        events.add(new SensorEvent(SensorEventType.LIGHT_ON, "5"));
        events.add(new SensorEvent(SensorEventType.LIGHT_ON, "6"));

        events.add(new SensorEvent(SensorEventType.DOOR_CLOSED, "1"));
        events.add(new SensorEvent(SensorEventType.DOOR_CLOSED, "2"));
        events.add(new SensorEvent(SensorEventType.DOOR_CLOSED, "3"));
        events.add(new SensorEvent(SensorEventType.DOOR_CLOSED, "4"));

        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(
                new CompositeAndIteratorTest.EventProvider(events),
                listenerManager
        );

        SmartHome smartHome = new FileSmartHomeLoader().loadSmartHome();
        homeEventsObserver.runEventsCycle(smartHome);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                assertFalse(door.isOpen());
            }
        }

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }
    }

    private void makeNotifications(HandlerManager listenersManager) {
        listenersManager.subscribe(SensorEventType.DOOR_OPEN, new AlarmAwareEventProcessor(new DoorEventProcessor()));
        listenersManager.subscribe(SensorEventType.DOOR_CLOSED, new AlarmAwareEventProcessor(new DoorEventProcessor()));
        listenersManager.subscribe(SensorEventType.LIGHT_ON, new AlarmAwareEventProcessor(new LightsEventProcessor()));
        listenersManager.subscribe(SensorEventType.LIGHT_OFF, new AlarmAwareEventProcessor(new LightsEventProcessor()));
        listenersManager.subscribe(SensorEventType.DOOR_CLOSED, new AlarmAwareEventProcessor(new HallDoorEventProcessor()));
    }
}
