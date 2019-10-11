package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.entity.Door;
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

import static org.junit.Assert.assertEquals;

public class AlarmTest {

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
    public void testAlarmState() throws IOException {
        HandlerManager listenerManager = new HandlerManager(SensorEventType.values());
        makeNotifications(listenerManager);

        ArrayList<SensorEvent> events = new ArrayList<>();
        events.add(new SensorEvent(SensorEventType.DOOR_CLOSED, "3"));
        events.add(new SensorEvent(SensorEventType.ALARM_ACTIVATED, "1"));
        events.add(new SensorEvent(SensorEventType.DOOR_OPEN, "3"));
        events.add(new SensorEvent(SensorEventType.DOOR_CLOSED, "3"));
        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(
                new AlarmTest.EventProvider(events),
                listenerManager
        );

        SmartHome smartHome = new FileSmartHomeLoader().loadSmartHome();
        homeEventsObserver.runEventsCycle(smartHome);
        assertEquals(true, smartHome.getAlarm().isActivated());
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("4")) {
                    assertEquals(false, door.isOpen());
                }
            }
        }
    }

    private void makeNotifications(HandlerManager listenersManager) {
        listenersManager.subscribe(SensorEventType.DOOR_OPEN, new AlarmAwareEventProcessor(new DoorEventProcessor()));
        listenersManager.subscribe(SensorEventType.DOOR_CLOSED, new AlarmAwareEventProcessor(new DoorEventProcessor()));
        listenersManager.subscribe(SensorEventType.DOOR_CLOSED, new AlarmAwareEventProcessor(new HallDoorEventProcessor()));
        listenersManager.subscribe(SensorEventType.ALARM_ACTIVATED, new AlarmActivatedEventProcessor());
        listenersManager.subscribe(SensorEventType.ALARM_DEACTIVATED, new AlarmDeactivatedEventProcessor());
    }
}
