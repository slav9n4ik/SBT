package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

public class AlarmAwareEventProcessor implements EventProcessor {
    private EventProcessor wrappedProcessor;

    public AlarmAwareEventProcessor(EventProcessor wrappedProcessor) {
        this.wrappedProcessor = wrappedProcessor;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (isAlarmActivatedState(smartHome)) {
            smartHome.getAlarm().startAlarm();
            System.out.println(event.getType() + " tried. ALERT!!!");
        } else {
            wrappedProcessor.processEvent(smartHome, event);
        }
    }

    private boolean isAlarmActivatedState(SmartHome smartHome) {
        return smartHome.getAlarm().isActivated();

    }
}
