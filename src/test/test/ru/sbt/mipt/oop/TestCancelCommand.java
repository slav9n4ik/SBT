package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.rc.SmartHomeRemoteControl;
import org.junit.Test;
import ru.sbt.mipt.oop.entity.Light;
import ru.sbt.mipt.oop.entity.Room;
import ru.sbt.mipt.oop.remotecommand.AllLightOffCommand;
import ru.sbt.mipt.oop.remotecommand.CancelCommand;
import ru.sbt.mipt.oop.remotecommand.CommandHistory;
import ru.sbt.mipt.oop.remotecommand.UndoubleCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class TestCancelCommand {

    @Test
    public void testCancelCommand() throws IOException {
        CommandHistory commandHistory = new CommandHistory();
        SmartHomeRemoteControl rc = new SmartHomeRemoteControl(commandHistory, "1");
        SmartHome smartHome = new SmartHome();
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", true);
        smartHome.addRoom(new Room(
                Arrays.asList(light1, light2),
                Collections.emptyList(), "room1"
        ));

        rc.addCommandOnButton("A", new AllLightOffCommand(smartHome));
        rc.addCommandOnButton("4", new CancelCommand(commandHistory));
        rc.onButtonPressed("A");
        rc.onButtonPressed("4");

        assertTrue(light1.isOn());
        assertTrue(light2.isOn());


    }
}
