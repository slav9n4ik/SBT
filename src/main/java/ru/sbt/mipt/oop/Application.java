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
        SmartHomeLoader smartHomeLoader = context.getBean(SmartHomeLoader.class);
        EventsManager eventsManager = context.getBean(EventsManager.class);
        SmartHome smartHome = smartHomeLoader.loadSmartHome();

        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        SmartHomeRemoteControl smartHomeRemoteControl = setUpPult1(smartHome);
        remoteControlRegistry.registerRemoteControl(smartHomeRemoteControl, "1");

        eventsManager.runEventsCycle(smartHome);
    }

    private static SmartHomeRemoteControl setUpPult1(SmartHome smartHome) {
        CommandHistory commandHistory = new CommandHistory();
        SmartHomeRemoteControl smartHomeRemoteControl = new SmartHomeRemoteControl(commandHistory, "1");
        smartHomeRemoteControl.addCommandOnButton("A",
                new AllLightOffCommand(smartHome));
        return smartHomeRemoteControl;
    }

}
