package com.company.command;

public class QCommand implements Command {
    @Override
    public void execute(String... arguments) {
        System.exit(0);
    }

    @Override
    public boolean isValidInput(String... arguments) {
        return true;
    }

    @Override
    public void clearTemporaryData() {

    }

}
