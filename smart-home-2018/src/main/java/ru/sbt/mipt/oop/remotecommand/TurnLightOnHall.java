package ru.sbt.mipt.oop.remotecommand;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.entity.Light;
import ru.sbt.mipt.oop.entity.Room;

public class TurnLightOnHall implements UndoubleCommand{

    private final SmartHome smartHome;

    public TurnLightOnHall(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void undo() {
        findHallLightsAndMakeComamnd(true);
    }

    @Override
    public void execute() {
        findHallLightsAndMakeComamnd(false);
    }

    private void findHallLightsAndMakeComamnd(boolean state) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (room.getName().equals("hall")) {
                    if(state) {
                        light.setOn(state);
                        System.out.println("Light " + light.getId() + " was opened");
                    } else {
                        light.setOn(!state);
                        System.out.println("Light " + light.getId() + " was closed");
                    }
                }
            }
        }

    }
}
