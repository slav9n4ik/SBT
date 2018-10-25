package ru.sbt.mipt.oop.alarmprocessors;

import ru.sbt.mipt.oop.entity.Alarm;

public class AlarmState implements State {

    private Alarm alarm;

    public AlarmState(Alarm alarm) {
        this.alarm = alarm;
    }


    @Override
    public void activate(String password) {

    }

    @Override
    public void deactivate(String password) {

    }
}
