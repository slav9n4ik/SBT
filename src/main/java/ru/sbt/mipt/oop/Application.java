package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventprocessors.DoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.LightsEventProcessor;
import ru.sbt.mipt.oop.observer.EventManager;
import ru.sbt.mipt.oop.observer.HomeEventsObserver;
import ru.sbt.mipt.oop.sensors.RandomSensorEventProvider;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;

public class Application {

    private SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();
    private HomeEventsObserver observer;
    private EventManager listenersManager =  new EventManager(SensorEventType.values());

    public void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        this.smartHomeLoader = smartHomeLoader;
    }

    public static void main(String... args) throws IOException {
        new Application().startApplication();
    }

    private void startApplication() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        makeNotifications();
        observer.runEventsCycle(smartHome);
    }

    private void makeNotifications() {
        listenersManager.subscribe(SensorEventType.DOOR_OPEN, new DoorEventProcessor());
        listenersManager.subscribe(SensorEventType.DOOR_CLOSED, new DoorEventProcessor());
        listenersManager.subscribe(SensorEventType.LIGHT_ON, new LightsEventProcessor());
        listenersManager.subscribe(SensorEventType.LIGHT_OFF, new LightsEventProcessor());
        listenersManager.subscribe(SensorEventType.DOOR_CLOSED, new HallDoorEventProcessor());

        observer = new HomeEventsObserver(
                new RandomSensorEventProvider(),
                listenersManager
        );
    }

}
