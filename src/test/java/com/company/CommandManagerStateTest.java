package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.company.Constants.ERROR_CANVAS_IS_ALREADY_DRAWN;
import static org.junit.Assert.assertEquals;

public class CommandManagerStateTest {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    CommandManager commandManager;

    @Before
    public void setUp() throws CustomException {
        commandManager = new CommandManager();
        commandManager.executeCommand(new String[]{"C", "20", "4"});
    }

    @Test
    public void testExecuteMethodBCommand_01() throws CustomException {
        commandManager.executeCommand(new String[]{"L", "1", "2", "6", "2"});
        commandManager.executeCommand(new String[]{"L", "6", "3", "6", "4"});
        commandManager.executeCommand(new String[]{"R", "14", "1", "18", "3"});

        System.setOut(new PrintStream(outContent));
        commandManager.executeCommand(new String[]{"B", "10", "3", "o"});
        String expectedResult = "----------------------\r\n" +
                "|oooooooooooooxxxxxoo|\r\n" +
                "|xxxxxxooooooox   xoo|\r\n" +
                "|     xoooooooxxxxxoo|\r\n" +
                "|     xoooooooooooooo|\r\n" +
                "----------------------\r\n";
        assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void testExecuteMethodBCommand_02() throws CustomException {
        commandManager.executeCommand(new String[]{"L", "1", "2", "6", "2"});
        commandManager.executeCommand(new String[]{"L", "6", "1", "6", "4"});
        commandManager.executeCommand(new String[]{"R", "14", "1", "18", "3"});

        System.setOut(new PrintStream(outContent));
        commandManager.executeCommand(new String[]{"B", "10", "3", "o"});
        String expectedResult = "----------------------\r\n" +
                "|     xoooooooxxxxxoo|\r\n" +
                "|xxxxxxooooooox   xoo|\r\n" +
                "|     xoooooooxxxxxoo|\r\n" +
                "|     xoooooooooooooo|\r\n" +
                "----------------------\r\n";
        assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void testExecuteMethodBCommand_03() throws CustomException {
        commandManager.executeCommand(new String[]{"R", "1", "1", "20", "4"});

        System.setOut(new PrintStream(outContent));
        commandManager.executeCommand(new String[]{"B", "10", "3", "o"});
        String expectedResult = "----------------------\r\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\r\n" +
                "|xoooooooooooooooooox|\r\n" +
                "|xoooooooooooooooooox|\r\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\r\n" +
                "----------------------\r\n";
        assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void testExecuteMethodBCommand_04() throws CustomException {
        commandManager.executeCommand(new String[]{"L", "10", "1", "10", "3"});

        System.setOut(new PrintStream(outContent));
        commandManager.executeCommand(new String[]{"B", "10", "4", "o"});
        String expectedResult = "----------------------\r\n" +
                "|oooooooooxoooooooooo|\r\n" +
                "|oooooooooxoooooooooo|\r\n" +
                "|oooooooooxoooooooooo|\r\n" +
                "|oooooooooooooooooooo|\r\n" +
                "----------------------\r\n";
        assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void testExecuteMethodBCommand_05() throws CustomException {
        commandManager.executeCommand(new String[]{"L", "10", "1", "10", "3"});

        System.setOut(new PrintStream(outContent));
        commandManager.executeCommand(new String[]{"B", "10", "3", "o"});
        String expectedResult = "----------------------\r\n" +
                "|         o          |\r\n" +
                "|         o          |\r\n" +
                "|         o          |\r\n" +
                "|                    |\r\n" +
                "----------------------\r\n";
        assertEquals(expectedResult, outContent.toString());
    }

    @Test(expected = CustomException.class)
    public void testExecuteMethodCCommandErrorCanvasAlreadyInitialized() throws CustomException {
        commandManager.executeCommand(new String[]{"C", "20", "4"});

        System.setOut(new PrintStream(outContent));
        exceptionRule.expect(CustomException.class);
        exceptionRule.expectMessage(ERROR_CANVAS_IS_ALREADY_DRAWN);
        commandManager.executeCommand(new String[]{"C", "30", "4"});
    }

    @Test
    public void testExecuteMethodQCommand() throws CustomException {
        exit.expectSystemExitWithStatus(0);
        commandManager.executeCommand(new String[]{"Q"});
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }
}
