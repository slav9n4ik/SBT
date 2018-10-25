package ru.sbt.mipt.oop.entity;

import ru.sbt.mipt.oop.alarmprocessors.ActivatedState;
import ru.sbt.mipt.oop.alarmprocessors.DeactivatedState;
import ru.sbt.mipt.oop.alarmprocessors.State;

public class Alarm {
    private String password;
    private State state;

    public Alarm(){
        this.password = "12345"; // надо заинжектить
        deactivateAlarm();
    }

    public void changeState(State state) {
        this.state = state;
    }

    public String getPassword() {
        return password;
    }

    public State getState() {
        return state;
    }

    public void activateAlarm() {
        new DeactivatedState(this).activate(password);
    }

    public void deactivateAlarm() {
        new ActivatedState(this).deactivate(password);
    }
}
