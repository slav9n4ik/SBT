package ru.sbt.mipt.oop.alarmprocessors;

public class ActivatedState implements AlarmState {

    private final String password;
    private Alarm alarm;

    public ActivatedState(Alarm alarm, String password) {
        this.password = password;
        this.alarm = alarm;
    }

    @Override
    public void deactivate(String deactivatePassword) {
        if(this.password.equals(deactivatePassword)) {
            alarm.changeState(new OffAlarmState(alarm, password));
            System.out.println("ALARM is deactivated");
        } else {
            alarm.changeState(new AlertAlarmState(alarm, password));
            System.out.println("ALERT!!!");
        }

    }

    @Override
    public boolean isActivates() {
        return true;
    }

}
