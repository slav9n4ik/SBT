package ru.sbt.mipt.oop.alarmprocessors;

public class AlertAlarmState implements AlarmState {

    private final String password;
    private Alarm alarm;

    public AlertAlarmState(Alarm alarm, String password) {
        this.password = password;
        this.alarm = alarm;
    }

    @Override
    public void deactivate(String deactivatePassword) {
        if(this.password.equals(deactivatePassword)) {
            alarm.changeState(new OffAlarmState(alarm, password));
            System.out.println("ALARM is deeactivated");
        } else {
            alarm.changeState(this);
            System.out.println("ALERT!!!");
        }
    }

    @Override
    public boolean isActivates() {
        return true;
    }

}
