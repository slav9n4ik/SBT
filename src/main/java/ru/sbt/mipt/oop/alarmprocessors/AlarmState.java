package ru.sbt.mipt.oop.alarmprocessors;

public interface AlarmState {
    default void activate(String password){
    }
    default void deactivate(String password){
    }
    default void startAlarm(){
    }

    boolean isActivates();
}