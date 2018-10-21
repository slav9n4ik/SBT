package ru.sbt.mipt.oop.composite;

@FunctionalInterface
public interface Action {
    void executeAction(Executable object);
}
