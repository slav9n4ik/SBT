package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.eventprocessors.*;
import ru.sbt.mipt.oop.observer.HandlerManager;
import ru.sbt.mipt.oop.observer.HomeEventsObserver;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventProvider;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import static junit.framework.Assert.assertEquals;

public class HomeEventsObserverTest {

    private CountingEventProcessor countingEventProcessor = new CountingEventProcessor();

    public static class CountingEventProcessor implements EventProcessor {

        private int count = 0;

        @Override
        public void processEvent(SmartHome smartHome, SensorEvent event) {
            count++;
        }

        int getCount() {
            return count;
        }
    }

    public static class OneTimeEventProvider implements SensorEventProvider {

        private int callsCount = 0;
        private SensorEvent sensorEvent;

        OneTimeEventProvider(SensorEvent sensorEvent) {
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
        HandlerManager listenerManager = new HandlerManager(SensorEventType.values());
        makeNotifications(listenerManager);
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(
                new OneTimeEventProvider(sensorEvent),
                listenerManager
        );

        homeEventsObserver.runEventsCycle(new SmartHome());
        assertEquals(1, countingEventProcessor.getCount());
    }

    private void makeNotifications(HandlerManager listenersManager) {
        listenersManager.subscribe(SensorEventType.DOOR_CLOSED, new AlarmAwareEventProcessor(new HallDoorEventProcessor()));
        listenersManager.subscribe(SensorEventType.DOOR_CLOSED, countingEventProcessor);
    }
}