package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.entity.Light;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_ON;

public class LightsEventProcessor implements EventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        //if (!isLightEvent(event)) return;
            smartHome.execute(
                    object -> {
                        if (object instanceof Light) {
                            Light light = (Light) object;
                            if (light.getId().equals(event.getObjectId())) {
                                if (event.getType() == LIGHT_ON) {
                                    changeLightState(light, true, " was turned on.");
                                } else {
                                    changeLightState(light, false, " was turned off.");
                                }
                            }
                        }
                    }
            );
    }

    private void changeLightState(Light light, boolean b, String s) {
        light.setOn(b);
        System.out.println("Light " + light.getId() + s);
    }

//    private boolean isLightEvent(SensorEvent event) {
//        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
//    }
}
