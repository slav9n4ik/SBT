package ru.sbt.mipt.oop.processing;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.homeobjects.Door;
import ru.sbt.mipt.oop.homeobjects.Light;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.sensor.SensorCommand;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor {
    public static void processDoorEvent(SmartHome smartHome, SensorEvent event) {
        // событие от двери
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) {
                            for (Room homeRoom : smartHome.getRooms()) {
                                for (Light light : homeRoom.getLights()) {
                                    light.setOn(false);
                                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                    SensorComandExacutor.sendCommand(command);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
