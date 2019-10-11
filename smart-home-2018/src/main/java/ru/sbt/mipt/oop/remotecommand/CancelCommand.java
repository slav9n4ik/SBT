package ru.sbt.mipt.oop.remotecommand;

public class CancelCommand implements Command {

    private final CommandHistory commandHistory;

    public CancelCommand(CommandHistory commandHistory) {
        this.commandHistory = commandHistory;
    }

    @Override
    public void execute() {
        UndoubleCommand undoubleCommand = commandHistory.popLastCommand();
        undoubleCommand.undo();
    }

}
