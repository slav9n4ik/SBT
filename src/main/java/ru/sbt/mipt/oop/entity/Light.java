package ru.sbt.mipt.oop.entity;

import ru.sbt.mipt.oop.composite.Action;
import ru.sbt.mipt.oop.composite.Executable;

public class Light implements Executable {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void execute(Action action) {
        action.executeAction(this);
    }
}
