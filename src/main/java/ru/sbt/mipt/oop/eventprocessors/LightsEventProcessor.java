package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarmprocessors.ActivatedState;
import ru.sbt.mipt.oop.alarmprocessors.AlarmState;
import ru.sbt.mipt.oop.entity.Light;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_ON;

public class LightsEventProcessor implements EventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        //if (!isLightEvent(event)) return;
        if(!isActivatedState(smartHome)) {
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
        } else {
            AlarmState alarmState = new AlarmState(smartHome.getAlarm());
            smartHome.getAlarm().changeState(alarmState);
            System.out.println("ALARM! Somebody turn on light!");
        }
    }

    private void changeLightState(Light light, boolean b, String s) {
        light.setOn(b);
        System.out.println("Light " + light.getId() + s);
    }

    private boolean isActivatedState(SmartHome smartHome) {
        return smartHome.getAlarm().getState().getClass().equals(ActivatedState.class);
    }

//    private boolean isLightEvent(SensorEvent event) {
//        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
//    }
}
