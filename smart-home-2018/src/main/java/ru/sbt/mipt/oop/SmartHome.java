package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.composite.Action;
import ru.sbt.mipt.oop.composite.Executable;
import ru.sbt.mipt.oop.alarmprocessors.Alarm;
import ru.sbt.mipt.oop.entity.Light;
import ru.sbt.mipt.oop.entity.Room;
import ru.sbt.mipt.oop.sensors.CommandType;
import ru.sbt.mipt.oop.sensors.SensorCommand;
import ru.sbt.mipt.oop.sensors.SensorCommandExecutor;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Executable {

    private Alarm alarm;

    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
        alarm = new Alarm();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void turnLightStateInAllRooms(boolean state) {
        for (Room homeRoom : getRooms()) {
            for (Light light : homeRoom.getLights()) {
                SensorCommand command;
                if (state) {
                    light.setOn(true);
                    command = new SensorCommand(CommandType.LIGHT_ON, light.getId());
                } else {
                    light.setOn(false);
                    command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                }
                SensorCommandExecutor.executeCommand(command);
            }
        }
    }

    @Override
    public void execute(Action action) {
        for (Room room : this.getRooms()) {
            //action.executeAction(room);
            room.execute(action);
        }
    }
}
