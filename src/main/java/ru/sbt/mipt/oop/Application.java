package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.dataload.FileSmartHomeLoader;
import ru.sbt.mipt.oop.dataload.SmartHomeLoader;
import ru.sbt.mipt.oop.processing.EventProcessing;

import java.io.IOException;

public class Application {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    public static void main(String... args) throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        EventProcessing.runEventsCycle(smartHome);
    }

    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        Application.smartHomeLoader = smartHomeLoader;
    }

}
