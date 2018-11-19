package ru.sbt.mipt.oop.remotecommand;

import ru.sbt.mipt.oop.SmartHome;

public class AllLightOffCommand implements UndoubleCommand {

    private final SmartHome smartHome;

    public AllLightOffCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.turnLightStateInAllRooms(false);
    }

    @Override
    public void undo() {
        smartHome.turnLightStateInAllRooms(true);
    }
}
