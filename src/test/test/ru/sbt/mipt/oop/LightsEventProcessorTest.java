package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.entity.Light;
import ru.sbt.mipt.oop.entity.Room;
import ru.sbt.mipt.oop.eventprocessors.LightsEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;

import static org.junit.Assert.*;

public class LightsEventProcessorTest {

    private LightsEventProcessor lightEventProcessor;
    private static SmartHomeLoader smartHomeLoader;
    private SmartHome smartHome;
    private SensorEvent event1;
    private SensorEvent event2;

    @Before
    public void initTest() throws IOException {
        lightEventProcessor = new LightsEventProcessor();
        smartHomeLoader = new FileSmartHomeLoader();
        smartHome = smartHomeLoader.loadSmartHome();
        event1 = new SensorEvent(SensorEventType.LIGHT_OFF, "4");
        event2 = new SensorEvent(SensorEventType.LIGHT_ON, "1");

        //make a door state id 4
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals("4")) {
                    light.setOn(true);
                }
            }
        }

        //make a door state id 3
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals("1")) {
                    light.setOn(false);
                }
            }
        }
    }

    @Test
    public void processLightEventOffLightId4() {
        lightEventProcessor.processEvent(smartHome, event1);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals("4")) {
                    assertEquals(false, light.isOn());
                }
            }
        }

    }

    @Test
    public void processLightEventOnLightId1() {
        lightEventProcessor.processEvent(smartHome, event2);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals("1")) {
                    assertEquals(true, light.isOn());
                }
            }
        }
    }
}