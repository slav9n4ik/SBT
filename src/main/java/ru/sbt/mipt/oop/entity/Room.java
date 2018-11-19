package ru.sbt.mipt.oop.entity;

import ru.sbt.mipt.oop.composite.Action;
import ru.sbt.mipt.oop.composite.Executable;

import java.util.Collection;

public class Room implements Executable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    public Light getLightById(String objectId) {
        return null;
    }

    @Override
    public void execute(Action action) {
        action.executeAction(this);
        for (Door door : this.getDoors())
            door.execute(action);
        for (Light light : this.getLights())
            light.execute(action);
    }
}
