package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.entity.Door;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    public void processEvent(SmartHome smartHome, SensorEvent event) {
        //if (!isDoorEvent(event)) return;
        // событие от двери
            smartHome.execute(
                    object -> {
                        if (object instanceof Door) {
                            Door door = (Door) object;
                            if (door.getId().equals(event.getObjectId())) {
                                if (event.getType() == DOOR_OPEN) {
                                    changeDoorState(door, true, " was opened.");
                                } else {
                                    changeDoorState(door, false, " was closed.");
                                }
                            }
                        }
                    }
            );
    }

    private void changeDoorState(Door door, boolean opened, String s) {
        door.setOpen(opened);
        System.out.println("Door " + door.getId() + s);
    }


//    private boolean isDoorEvent(SensorEvent event) {
//        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
//    }
}
