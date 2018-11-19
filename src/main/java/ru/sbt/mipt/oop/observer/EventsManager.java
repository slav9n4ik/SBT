package ru.sbt.mipt.oop.observer;

import ru.sbt.mipt.oop.SmartHome;

public interface EventsManager {
    void runEventsCycle(SmartHome smartHome);
}
