package ru.sbt.mipt.oop.alarmprocessors;

import ru.sbt.mipt.oop.entity.Alarm;

public interface State {
    void activate(String password);
    void deactivate(String password);
}
