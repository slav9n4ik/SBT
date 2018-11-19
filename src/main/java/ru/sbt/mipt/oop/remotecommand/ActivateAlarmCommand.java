package ru.sbt.mipt.oop.remotecommand;

import ru.sbt.mipt.oop.SmartHome;

public class ActivateAlarmCommand implements UndoubleCommand{

    private final SmartHome smartHome;
    private final String password;

    public ActivateAlarmCommand(SmartHome smartHome, String password) {
        this.smartHome = smartHome;
        this.password = password;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().activate(password);
    }

    @Override
    public void undo() {
        smartHome.getAlarm().deactivate(password);
    }
}
