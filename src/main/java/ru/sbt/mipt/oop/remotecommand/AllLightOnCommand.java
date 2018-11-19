package ru.sbt.mipt.oop.remotecommand;

import ru.sbt.mipt.oop.SmartHome;

public class AllLightOnCommand implements UndoubleCommand {

    private final SmartHome smartHome;

    public AllLightOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.turnLightStateInAllRooms(true);
    }

    @Override
    public void undo() {
        smartHome.turnLightStateInAllRooms(false);
    }
}
