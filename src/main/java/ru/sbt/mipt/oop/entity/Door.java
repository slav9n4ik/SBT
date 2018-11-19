package ru.sbt.mipt.oop.entity;

import ru.sbt.mipt.oop.composite.Action;
import ru.sbt.mipt.oop.composite.Executable;

public class Door implements Executable {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void execute(Action action) {
        action.executeAction(this);
    }
}
