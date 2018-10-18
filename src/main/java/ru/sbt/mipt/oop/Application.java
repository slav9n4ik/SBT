package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.observer.DoorEventHandler;
import ru.sbt.mipt.oop.observer.HomeEventsObserver;
import ru.sbt.mipt.oop.observer.LightEventHandler;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;

public class Application {

    private SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

//    public void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
//        Application.smartHomeLoader = smartHomeLoader;
//    }

    public static void main(String... args) throws IOException {
        new Application().startApplication();
    }

    private void startApplication() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();

        HomeEventsObserver observer = new HomeEventsObserver();
        observer.events.subscribe(SensorEventType.DOOR_OPEN, new DoorEventHandler());
        observer.events.subscribe(SensorEventType.DOOR_CLOSED, new DoorEventHandler());
        observer.events.subscribe(SensorEventType.LIGHT_ON, new LightEventHandler());
        observer.events.subscribe(SensorEventType.LIGHT_OFF, new LightEventHandler());

        //НЕЗАБУДЬ ПРО ТРЕТИЙ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        observer.runEventsCycle(smartHome);
    }

}
