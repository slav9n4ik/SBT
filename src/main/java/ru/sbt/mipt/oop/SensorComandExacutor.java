package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.sensor.SensorCommand;

public class SensorComandExacutor {
    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
