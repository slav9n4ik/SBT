package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.alarmprocessors.AlarmState;
import ru.sbt.mipt.oop.eventprocessors.AlarmActivatedEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.DoorEventProcessor;
import ru.sbt.mipt.oop.observer.EventManager;
import ru.sbt.mipt.oop.observer.HomeEventsObserver;
import ru.sbt.mipt.oop.observer.HomeEventsObserverTest;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventProvider;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AlarmTest {

    public static class EventProvider implements SensorEventProvider {

        private int callsCount = 0;
        private List<SensorEvent> sensorEvent;

        public EventProvider(List<SensorEvent> sensorEvent) {
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
    public void testAlarmState() {
        EventManager listenerManager = new EventManager(SensorEventType.values());
        makeNotifications(listenerManager);

        ArrayList<SensorEvent> events = new ArrayList<>();
        events.add(new SensorEvent(SensorEventType.ALARM_ACTIVATED, "1"));
        events.add(new SensorEvent(SensorEventType.DOOR_CLOSED, "1"));
        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(
                new AlarmTest.EventProvider(events),
                listenerManager
        );

        SmartHome smartHome = new SmartHome();
        homeEventsObserver.runEventsCycle(smartHome);
        assertEquals(AlarmState.class, smartHome.getAlarm().getState().getClass());
    }

    private void makeNotifications(EventManager listenerManager) {
        listenerManager.subscribe(SensorEventType.ALARM_ACTIVATED, new AlarmActivatedEventProcessor());
        listenerManager.subscribe(SensorEventType.DOOR_CLOSED, new DoorEventProcessor());
    }
}
