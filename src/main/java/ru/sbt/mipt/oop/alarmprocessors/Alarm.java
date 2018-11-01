package ru.sbt.mipt.oop.alarmprocessors;

public class Alarm {
    private String password;
    private AlarmState state;

    public Alarm(){
        this.password = "12345"; // надо заинжектить
        state = new OffAlarmState(this, password);
    }

    protected void changeState(AlarmState state) {
        this.state = state;
    }

    public void activate(String password) {
        state.activate(password);
    }

    public void deactivate(String password) {
       state.deactivate(password);
    }

    public void startAlarm() {
        state.activate(password);
    }

    public boolean isActivated() {
        return state.getClass().equals(ActivatedState.class);
    }
}
