package ru.sbt.mipt.oop.remotecommand;

import ru.sbt.mipt.oop.SmartHome;

public class AlarmAwareCommand implements UndoubleCommand {

    private final SmartHome smartHome;
    private final String password;

    public AlarmAwareCommand(SmartHome smartHome, String password) {
        this.smartHome = smartHome;
        this.password = password;
    }

    @Override
    public void undo() {
        smartHome.getAlarm().deactivate(password);
    }

    @Override
    public void execute() {
        smartHome.getAlarm().startAlarm();
        System.out.println("ALERT!!!");
    }
}
