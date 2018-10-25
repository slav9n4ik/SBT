package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.entity.Room;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) return;
        // событие от двери
        smartHome.execute(
                object -> {
                    if(object instanceof Room) {
                        Room room = (Room) object;
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) {
                            smartHome.turnOffLights();

                        }
                    }
                }
        );
    }
}
