package ru.sbt.mipt.oop.remotecommand;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.entity.Door;
import ru.sbt.mipt.oop.entity.Room;

public class CloseHallDoorCommand implements UndoubleCommand {

    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void undo() {
        findHallDoorsAndMakeComamnd(true);
    }

    @Override
    public void execute() {
        findHallDoorsAndMakeComamnd(false);
    }

    private void findHallDoorsAndMakeComamnd(boolean state) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (room.getName().equals("hall")) {
                    if(state) {
                        door.setOpen(state);
                        System.out.println("Door " + door.getId() + " was opened");
                    } else {
                        door.setOpen(!state);
                        System.out.println("Door " + door.getId() + " was closed");
                    }
                }
            }
        }

    }
}
