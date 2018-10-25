package ru.sbt.mipt.oop.alarmprocessors;

import ru.sbt.mipt.oop.entity.Alarm;

public class ActivatedState implements State {

    private Alarm alarm;

    public ActivatedState(Alarm alarm) {
        this.alarm = alarm;
    }


    @Override
    public void activate(String password) {
    }

    @Override
    public void deactivate(String password) {
        if (alarm.getPassword().equals(password)) {
            alarm.changeState(new DeactivatedState(alarm));

            System.out.println("Home has turned on DEACTIVE_STATE");
        } else {
            alarm.changeState(new AlarmState(alarm));
            System.out.println("ALARM!! Wrong password!");
        }
    }
}
