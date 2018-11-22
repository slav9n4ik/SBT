package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.rc.SmartHomeRemoteControl;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.entity.Door;
import ru.sbt.mipt.oop.entity.Room;
import ru.sbt.mipt.oop.remotecommand.CloseHallDoorCommand;
import ru.sbt.mipt.oop.remotecommand.CommandHistory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertFalse;

public class TestCloseHallDoorCommand {
    private SmartHomeRemoteControl rc;
    private SmartHome smartHome;
    private Door door;

    @Before
    public void initTest() {
        CommandHistory commandHistory = new CommandHistory();
        rc = new SmartHomeRemoteControl(commandHistory, "1");
        smartHome = new SmartHome();
        door = new Door(true, "1");
        smartHome.addRoom(new Room(
                Collections.emptyList(),
                Arrays.asList(door),
                 "hall"
        ));
    }

    @Test
    public void testCloseHallDoorCommand() throws IOException {

        rc.addCommandOnButton("A", new CloseHallDoorCommand(smartHome));
        rc.onButtonPressed("A");
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall"))
            for (Door door : room.getDoors()) {
                assertFalse(door.isOpen());
            }
        }
    }
}
