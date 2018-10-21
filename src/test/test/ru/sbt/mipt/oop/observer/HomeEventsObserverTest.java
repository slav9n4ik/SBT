package ru.sbt.mipt.oop.observer;

import org.junit.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventProvider;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import static org.junit.Assert.assertEquals;

public class HomeEventsObserverTest {

    CountingEventProcessor countingEventProcessor = new CountingEventProcessor();

    public static class CountingEventProcessor implements EventProcessor {

        private int count = 0;

        @Override
        public void processEvent(SmartHome smartHome, SensorEvent event) {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    public static class OneTimeEventProvider implements SensorEventProvider {

        private int callsCount = 0;
        private SensorEvent sensorEvent;

        public OneTimeEventProvider(SensorEvent sensorEvent) {
            this.sensorEvent = sensorEvent;
        }

        @Override
        public SensorEvent getNextSensorEvent() {
            if (callsCount > 0) {
                return null;
            } else {
                callsCount++;
                return sensorEvent;
            }
        }
    }

    @Test
    public void test() {
        EventManager listenerManager = new EventManager(SensorEventType.values());
        makeNotifications(listenerManager);
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(
                new OneTimeEventProvider(sensorEvent),
                listenerManager
        );

        homeEventsObserver.runEventsCycle(new SmartHome());
        assertEquals(1, countingEventProcessor.getCount());
    }

    private void makeNotifications(EventManager listenerManager) {
        //listenerManager.subscribe(SensorEventType.DOOR_CLOSED, new DoorEventProcessor());
        //listenerManager.subscribe(SensorEventType.DOOR_CLOSED, new HallDoorEventProcessor());
        listenerManager.subscribe(SensorEventType.DOOR_CLOSED, countingEventProcessor);
    }
}