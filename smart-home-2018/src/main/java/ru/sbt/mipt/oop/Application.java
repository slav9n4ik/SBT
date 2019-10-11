package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.rc.RemoteControlRegistry;
import com.coolcompany.smarthome.rc.SmartHomeRemoteControl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.config.SmartHomeConfig;
import ru.sbt.mipt.oop.observer.EventsManager;
import ru.sbt.mipt.oop.remotecommand.AllLightOffCommand;
import ru.sbt.mipt.oop.remotecommand.CommandHistory;

import java.io.IOException;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String... args) throws IOException {
        logger.info("Starting configuration...");
        ApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfig.class);

        //Откуда загружать дом можно выбрать в конфигах
        SmartHomeLoader smartHomeLoader = context.getBean(SmartHomeLoader.class);
        SmartHome smartHome = smartHomeLoader.loadSmartHome();

        //RandomEventsProvider или Api можно выбрать в конфигах
        EventsManager eventsManager = context.getBean(EventsManager.class);

        //Загрузка из конфигов пульта и его регистрация
        RemoteControlRegistry remoteControlRegistry = context.getBean(RemoteControlRegistry.class);
        SmartHomeRemoteControl smartHomeRemoteControl = context.getBean(SmartHomeRemoteControl.class);
        remoteControlRegistry.registerRemoteControl(smartHomeRemoteControl, "1");

        eventsManager.runEventsCycle(smartHome);
    }


}
