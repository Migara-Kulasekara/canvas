package com.company.CommandTest;

import com.company.Canvas;
import com.company.CustomException;
import com.company.command.CCommand;
import com.company.operator.CCommandCanvasOperator;
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
public class CCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    CCommand cCommandTest;
    String[] command;
    String expectedResult;
    String expectedExceptionMsg;
    Canvas canvas;

    public CCommandTest(String[] command, String expectedResult, String expectedExceptionMsg) {
        this.command = command;
        this.expectedResult = expectedResult;
        this.expectedExceptionMsg = expectedExceptionMsg;
        this.canvas = new Canvas();
        this.cCommandTest = new CCommand(new CCommandCanvasOperator(canvas));
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"0", "4"}, null, ERROR_CANVAS_WIDTH_SHOULD_BE_GREATER_THAN_ZERO + EXCEPTION_MSG_SEPARATOR + "0"},
                {new String[]{"-1", "4"}, null, ERROR_CANVAS_WIDTH_SHOULD_BE_GREATER_THAN_ZERO + EXCEPTION_MSG_SEPARATOR + "-1"},
                {new String[]{"20", "0"}, null, ERROR_CANVAS_HEIGHT_SHOULD_BE_GREATER_THAN_ZERO + EXCEPTION_MSG_SEPARATOR + "0"},
                {new String[]{"20", "-1"}, null, ERROR_CANVAS_HEIGHT_SHOULD_BE_GREATER_THAN_ZERO + EXCEPTION_MSG_SEPARATOR + "-1"},
                {new String[]{"w", "4"}, null, ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_WIDTH + EXCEPTION_MSG_SEPARATOR + "w"},
                {new String[]{"20", "h"}, null, ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_HEIGHT + EXCEPTION_MSG_SEPARATOR + "h"},
                {new String[]{"20", "4"}, "----------------------\r\n" +
                        "|                    |\r\n" +
                        "|                    |\r\n" +
                        "|                    |\r\n" +
                        "|                    |\r\n" +
                        "----------------------\r\n", null},
                {new String[]{"20", "1"}, "----------------------\r\n" +
                        "|                    |\r\n" +
                        "----------------------\r\n", null},
                {new String[]{"1", "4"}, "---\r\n" +
                        "| |\r\n" +
                        "| |\r\n" +
                        "| |\r\n" +
                        "| |\r\n" +
                        "---\r\n", null},
                {new String[]{"30", "4"}, "--------------------------------\r\n" +
                        "|                              |\r\n" +
                        "|                              |\r\n" +
                        "|                              |\r\n" +
                        "|                              |\r\n" +
                        "--------------------------------\r\n", null},
                {new String[]{"101", "50"}, null, ERROR_MAX_CANVAS_WIDTH + MAX_CANVAS_WIDTH},
                {new String[]{"50", "101"}, null, ERROR_MAX_CANVAS_HEIGHT + MAX_CANVAS_HEIGHT},
        });

    }

    @Before
    public void setUp() {
        if (canvas == null) {
            Canvas canvas = new Canvas();
            canvas.setWidth(0);
            canvas.setHeight(0);

            cCommandTest = new CCommand(new CCommandCanvasOperator(canvas));
        }
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testExecuteMethod() throws CustomException {
        if (expectedExceptionMsg != null) {
            exceptionRule.expect(CustomException.class);
            exceptionRule.expectMessage(expectedExceptionMsg);
            cCommandTest.execute(command);
        } else {
            cCommandTest.execute(command);
            assertEquals(expectedResult, outContent.toString());
        }
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }
}
