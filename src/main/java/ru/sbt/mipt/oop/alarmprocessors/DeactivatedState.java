package ru.sbt.mipt.oop.alarmprocessors;

import ru.sbt.mipt.oop.entity.Alarm;

public class DeactivatedState implements State {

    private Alarm alarm;

    public DeactivatedState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String password) {
        if (alarm.getPassword().equals(password)) {
            alarm.changeState(new ActivatedState(alarm));
            System.out.println("Home has turned on ACTIVE_STATE");
        } else {
            System.out.println("Wrong password!");
        }
    }

    @Override
    public void deactivate(String password) {
    }
}
