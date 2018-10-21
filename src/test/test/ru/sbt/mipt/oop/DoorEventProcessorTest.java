package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.entity.Door;
import ru.sbt.mipt.oop.entity.Room;
import ru.sbt.mipt.oop.eventprocessors.DoorEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;

import static org.junit.Assert.*;

public class DoorEventProcessorTest {

    private DoorEventProcessor doorEventProcessor;
    private static SmartHomeLoader smartHomeLoader;
    private SmartHome smartHome;
    private SensorEvent event1;
    private SensorEvent event2;

    @Before
    public void initTest() throws IOException {
        doorEventProcessor = new DoorEventProcessor();
        smartHomeLoader = new FileSmartHomeLoader();
        smartHome = smartHomeLoader.loadSmartHome();
        event1 = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");
        event2 = new SensorEvent(SensorEventType.DOOR_OPEN, "3");

        //make a door state id 4
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("4")) {
                    door.setOpen(true);
                }
            }
        }

        //make a door state id 3
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("3")) {
                    door.setOpen(false);
                }
            }
        }
    }

    @Test
    public void processDoorEventCloseDoorId4() {
        doorEventProcessor.processEvent(smartHome, event1);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("4")) {
                    assertEquals(false, door.isOpen());
                }
            }
        }

    }

    @Test
    public void processDoorEventOpenDoorId3() {
        doorEventProcessor.processEvent(smartHome, event2);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("3")) {
                    assertEquals(true, door.isOpen());
                }
            }
        }
    }
}