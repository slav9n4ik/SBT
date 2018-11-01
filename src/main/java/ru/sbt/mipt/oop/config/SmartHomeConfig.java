package ru.sbt.mipt.oop.config;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.FileSmartHomeLoader;
import ru.sbt.mipt.oop.SmartHomeLoader;
import ru.sbt.mipt.oop.eventprocessors.*;
import ru.sbt.mipt.oop.observer.EventManager;
import ru.sbt.mipt.oop.observer.HomeEventsObserver;
import ru.sbt.mipt.oop.sensors.SensorEventType;

/**
 * Конфигурационный класс Spring IoC контейнера
 */
@Configuration
public class SmartHomeConfig {

    private EventManager listenersManager =  new EventManager(SensorEventType.values());

    @Bean
    SmartHomeLoader smartHomeLoader() {
        return new FileSmartHomeLoader();
    }

    @Bean
    HomeEventsObserver observer() {
        makeNotifications();
        return new HomeEventsObserver(new SensorEventsManager(), listenersManager);
    }

    private void makeNotifications() {
        listenersManager.subscribe(SensorEventType.DOOR_OPEN, new AlarmAwareEventProcessor(new DoorEventProcessor()));
        listenersManager.subscribe(SensorEventType.DOOR_CLOSED, new AlarmAwareEventProcessor(new DoorEventProcessor()));
        listenersManager.subscribe(SensorEventType.LIGHT_ON, new AlarmAwareEventProcessor(new LightsEventProcessor()));
        listenersManager.subscribe(SensorEventType.LIGHT_OFF, new AlarmAwareEventProcessor(new LightsEventProcessor()));
        listenersManager.subscribe(SensorEventType.DOOR_CLOSED, new AlarmAwareEventProcessor(new HallDoorEventProcessor()));
        listenersManager.subscribe(SensorEventType.ALARM_ACTIVATED, new AlarmActivatedEventProcessor());
        listenersManager.subscribe(SensorEventType.ALARM_DEACTIVATED, new AlarmDeactivatedEventProcessor());
    }
}
