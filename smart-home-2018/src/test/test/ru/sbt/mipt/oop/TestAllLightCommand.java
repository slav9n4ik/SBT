package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.rc.SmartHomeRemoteControl;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.entity.Light;
import ru.sbt.mipt.oop.entity.Room;
import ru.sbt.mipt.oop.remotecommand.AllLightOffCommand;
import ru.sbt.mipt.oop.remotecommand.AllLightOnCommand;
import ru.sbt.mipt.oop.remotecommand.CommandHistory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestAllLightCommand {

    private SmartHomeRemoteControl rc;
    private SmartHome smartHome;
    private Light light1, light2, light3, light4;

    @Before
    public void initTest() {
        CommandHistory commandHistory = new CommandHistory();
        rc = new SmartHomeRemoteControl(commandHistory, "1");
        smartHome = new SmartHome();
        light1 = new Light("1", false);
        light2 = new Light("2", false);
        light3 = new Light("3", false);
        light4 = new Light("4", false);
        smartHome.addRoom(new Room(
                Arrays.asList(light1, light2, light3, light4),
                Collections.emptyList(), "room1"
        ));
    }

    @Test
    public void testAllLightOnCommand() throws IOException {

        rc.addCommandOnButton("A", new AllLightOnCommand(smartHome));
        rc.onButtonPressed("A");

        assertTrue(light1.isOn());
        assertTrue(light2.isOn());
        assertTrue(light3.isOn());
        assertTrue(light4.isOn());

    }

    @Test
    public void testAllLightOffCommand() throws IOException {

        light1 = new Light("1", true);
        light2 = new Light("2", true);
        light3 = new Light("3", true);
        light4 = new Light("4", true);

        smartHome.addRoom(new Room(
                Arrays.asList(light1, light2, light3, light4),
                Collections.emptyList(), "room1"
        ));

        rc.addCommandOnButton("A", new AllLightOffCommand(smartHome));
        rc.onButtonPressed("A");

        assertFalse(light1.isOn());
        assertFalse(light2.isOn());
        assertFalse(light3.isOn());
        assertFalse(light4.isOn());

    }
}
