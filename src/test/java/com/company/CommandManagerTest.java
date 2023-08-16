package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static com.company.Constants.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CommandManagerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    CommandManager commandManagerTest;
    String[] command;
    String expectedResult;
    String expectedExceptionMsg;

    public CommandManagerTest(String[] command, String expectedResult, String expectedExceptionMsg) {
        this.command = command;
        this.expectedResult = expectedResult;
        this.expectedExceptionMsg = expectedExceptionMsg;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"c", "20", "4"}, null, ERROR_INVALID_COMMAND},
                {new String[]{"z", "20", "4"}, null, ERROR_INVALID_COMMAND},
                {new String[]{"z", "20"}, null, ERROR_INVALID_COMMAND},
                {new String[]{"C", "20"}, null, ERROR_INVALID_NUM_OF_ARGUMENTS},
                {new String[]{"C", "20", "4", "5"}, null, ERROR_INVALID_NUM_OF_ARGUMENTS},
                {new String[]{"L", "1", "2", "6", "2"}, null, ERROR_CANVAS_NOT_INITIALIZED},
                {new String[]{"R", "14", "1", "18", "3"}, null, ERROR_CANVAS_NOT_INITIALIZED},
                {new String[]{"B", "10", "3", "0"}, null, ERROR_CANVAS_NOT_INITIALIZED},
                {new String[]{"C", "0", "4"}, null, ERROR_CANVAS_WIDTH_SHOULD_BE_GREATER_THAN_ZERO + EXCEPTION_MSG_SEPARATOR + "0"},
                {new String[]{"C", "-4", "4"}, null, ERROR_CANVAS_WIDTH_SHOULD_BE_GREATER_THAN_ZERO + EXCEPTION_MSG_SEPARATOR + "-4"},
                {new String[]{"C", "20", "0"}, null, ERROR_CANVAS_HEIGHT_SHOULD_BE_GREATER_THAN_ZERO + EXCEPTION_MSG_SEPARATOR + "0"},
                {new String[]{"C", "20", "-4"}, null, ERROR_CANVAS_HEIGHT_SHOULD_BE_GREATER_THAN_ZERO + EXCEPTION_MSG_SEPARATOR + "-4"},
                {new String[]{"C", "20", "4"}, "----------------------\r\n" +
                        "|                    |\r\n" +
                        "|                    |\r\n" +
                        "|                    |\r\n" +
                        "|                    |\r\n" +
                        "----------------------\r\n", null},
                {new String[]{"L", "1", "2", "6", "2"}, null, ERROR_CANVAS_NOT_INITIALIZED},
                {new String[]{"R", "1", "3", "6", "2"}, null, ERROR_CANVAS_NOT_INITIALIZED},
                {new String[]{"B", "1", "3", "o"}, null, ERROR_CANVAS_NOT_INITIALIZED}
        });

    }

    @Before
    public void setUp() {
        commandManagerTest = new CommandManager();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testExecuteCommandMethod() throws CustomException {
        if (expectedExceptionMsg != null) {
            exceptionRule.expect(CustomException.class);
            exceptionRule.expectMessage(expectedExceptionMsg);
            commandManagerTest.executeCommand(command);
        } else {
            commandManagerTest.executeCommand(command);
            assertEquals(expectedResult, outContent.toString());
        }
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

}
