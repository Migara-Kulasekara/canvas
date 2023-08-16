package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.company.Constants.*;

public class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static CommandManager commandManager = null;
    static String[] input = null;

    public static void main(String[] args) {
        readConfigs();
        try {
            execute();
        } catch (IOException e) {
            System.out.println(ERROR_READING_COMMAND);
        }
    }

    static BufferedReader getBufferedReader() {
        return reader;
    }

    static void readConfigs() {
        try {
            PropertyReader.readPropValues();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }

    static void execute() throws IOException {
        while (true) {
            System.out.print(ENTER_COMMAND);
            input = getBufferedReader().readLine().trim().split(SPLIT_BY);//input string can contain leading spaces and multiple spaces between characters
            if (input != null) {
                if (commandManager == null) {
                    commandManager = new CommandManager();
                }
                try {
                    commandManager.executeCommand(input);
                } catch (CustomException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.print(ENTER_COMMAND);
            }
        }
    }
}

