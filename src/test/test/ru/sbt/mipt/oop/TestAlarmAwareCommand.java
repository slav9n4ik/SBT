package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.rc.SmartHomeRemoteControl;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.entity.Light;
import ru.sbt.mipt.oop.entity.Room;
import ru.sbt.mipt.oop.remotecommand.AlarmAwareCommand;
import ru.sbt.mipt.oop.remotecommand.CommandHistory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class TestAlarmAwareCommand {
    private SmartHomeRemoteControl rc;
    private SmartHome smartHome;
    private Light light;

    @Before
    public void initTest() {
        CommandHistory commandHistory = new CommandHistory();
        rc = new SmartHomeRemoteControl(commandHistory, "1");
        smartHome = new SmartHome();
        light = new Light("1", false);
        smartHome.addRoom(new Room(
                Arrays.asList(light),
                Collections.emptyList(), "room1"
        ));
    }

    @Test
    public void testAlarmCommand() throws IOException {

        rc.addCommandOnButton("A", new AlarmAwareCommand(smartHome, "12345"));
        rc.onButtonPressed("A");
        assertTrue(smartHome.getAlarm().isActivated());
    }
}
