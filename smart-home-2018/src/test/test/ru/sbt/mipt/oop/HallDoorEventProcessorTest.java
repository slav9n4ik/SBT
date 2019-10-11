package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.entity.Light;
import ru.sbt.mipt.oop.entity.Room;
import ru.sbt.mipt.oop.eventprocessors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;

import static org.junit.Assert.*;

public class HallDoorEventProcessorTest {

    private HallDoorEventProcessor hallEventProcessor;
    private static SmartHomeLoader smartHomeLoader;
    private SmartHome smartHome;
    private SensorEvent event1;
    //private SensorEvent event2;

    @Before
    public void initTest() throws IOException {
        hallEventProcessor = new HallDoorEventProcessor();
        smartHomeLoader = new FileSmartHomeLoader();
        smartHome = smartHomeLoader.loadSmartHome();
        event1 = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");
        //event2 = new SensorEvent(SensorEventType.DOOR_OPEN, "4");
    }

    @Test
    public void processHallEventCloseDoorId4() {
        hallEventProcessor.processEvent(smartHome, event1);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }

    }

}