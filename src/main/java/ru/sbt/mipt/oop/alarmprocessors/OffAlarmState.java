package ru.sbt.mipt.oop.alarmprocessors;

public class OffAlarmState implements AlarmState {

    private Alarm alarm;
    private final String password;

    public OffAlarmState(Alarm alarm, String password) {
        this.alarm = alarm;
        this.password = password;
    }

    @Override
    public void activate(String password) {
        alarm.changeState( new ActivatedState(alarm, password));
        System.out.println("ALARM is activated");
    }

    @Override
    public boolean isActivates() {
        return false;
    }
}
