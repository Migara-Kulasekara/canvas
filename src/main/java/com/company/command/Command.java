package com.company.command;

import com.company.CustomException;

interface Command {
    void execute(String... arguments) throws CustomException;

    boolean isValidInput(String... arguments) throws CustomException;

    void clearTemporaryData();
}
