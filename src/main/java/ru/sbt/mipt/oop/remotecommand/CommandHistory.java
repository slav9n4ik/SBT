package ru.sbt.mipt.oop.remotecommand;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
        private final List<UndoubleCommand> history = new ArrayList();
    private String lastRcId;

    public void saveCommand(String rcId, UndoubleCommand command) {

        if(!rcId.equals(lastRcId)) {
            history.clear();
        }

        history.add(command);
        lastRcId = rcId;
    }

    public UndoubleCommand popLastCommand() {
        if (history.isEmpty()) return null;
        int lastCmdIndex = history.size() - 1;
        UndoubleCommand undoubleCommand = history.get(lastCmdIndex);
        history.remove(lastCmdIndex);
        return undoubleCommand;
    }
}
