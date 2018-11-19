package com.coolcompany.smarthome.rc;

import ru.sbt.mipt.oop.remotecommand.Command;
import ru.sbt.mipt.oop.remotecommand.CommandHistory;
import ru.sbt.mipt.oop.remotecommand.UndoubleCommand;

import java.util.HashMap;
import java.util.Map;

public class SmartHomeRemoteControl implements RemoteControl {

    private final Map<String, Command> buttonCodeToCommandMap = new HashMap();
    private final CommandHistory commandHistory;
    private final String id;

    public SmartHomeRemoteControl(CommandHistory commandHistory, String id) {
        this.commandHistory = commandHistory;
        this.id = id;
    }

    public void addCommandOnButton(String buttonCode, Command command) {
        buttonCodeToCommandMap.put(buttonCode, command);
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        Command command = buttonCodeToCommandMap.get(buttonCode);
        command.execute();
        //commandExecutor.execute(command);
        if (command instanceof UndoubleCommand)
            commandHistory.saveCommand(id, (UndoubleCommand) command);
    }
}
