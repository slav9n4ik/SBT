package ru.sbt.mipt.oop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.config.SmartHomeConfig;
import ru.sbt.mipt.oop.observer.EventsManager;

import java.io.IOException;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String... args) throws IOException {
        logger.info("Starting configuration...");
        ApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfig.class);
        SmartHomeLoader smartHomeLoader = context.getBean(SmartHomeLoader.class);
        EventsManager eventsManager = context.getBean(EventsManager.class);
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        eventsManager.runEventsCycle(smartHome);
    }

}
