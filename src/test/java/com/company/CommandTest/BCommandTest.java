package com.company.CommandTest;

import com.company.Canvas;
import com.company.CustomException;
import com.company.command.BCommand;
import com.company.operator.BCommandCanvasOperator;
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
public class BCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    BCommand bCommandTest;
    Canvas canvas;
    String[] command;
    String expectedResult;
    String expectedExceptionMsg;

    public BCommandTest(String[] command, String expectedResult, String expectedExceptionMsg) {
        this.command = command;
        this.expectedResult = expectedResult;
        this.expectedExceptionMsg = expectedExceptionMsg;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"0", "4", "o"}, null, ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO + EXCEPTION_MSG_SEPARATOR + X},
                {new String[]{"-1", "4", "o"}, null, ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO + EXCEPTION_MSG_SEPARATOR + X},
                {new String[]{"20", "0", "o"}, null, ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO + EXCEPTION_MSG_SEPARATOR + Y},
                {new String[]{"20", "-1", "o"}, null, ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO + EXCEPTION_MSG_SEPARATOR + Y},
                {new String[]{"w", "4", "o"}, null, ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES + EXCEPTION_MSG_SEPARATOR + X},
                {new String[]{"20", "h", "o"}, null, ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES + EXCEPTION_MSG_SEPARATOR + Y},
                {new String[]{"1", "2", "oo"}, null, ERROR_INVALID_COLOR + EXCEPTION_MSG_SEPARATOR + "oo"},
                {new String[]{"21", "2", "o"}, null, ERROR_X_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_WIDTH + EXCEPTION_MSG_SEPARATOR + X},
                {new String[]{"20", "5", "o"}, null, ERROR_Y_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_HEIGHT + EXCEPTION_MSG_SEPARATOR + Y},
                {new String[]{"20", "4", "o"}, "----------------------\r\n" +
                        "|oooooooooooooooooooo|\r\n" +
                        "|oooooooooooooooooooo|\r\n" +
                        "|oooooooooooooooooooo|\r\n" +
                        "|oooooooooooooooooooo|\r\n" +
                        "----------------------\r\n", null},
                {new String[]{"1", "1", "o"}, "----------------------\r\n" +
                        "|oooooooooooooooooooo|\r\n" +
                        "|oooooooooooooooooooo|\r\n" +
                        "|oooooooooooooooooooo|\r\n" +
                        "|oooooooooooooooooooo|\r\n" +
                        "----------------------\r\n", null},

        });

    }

    @Before
    public void setUp() throws CustomException {
        canvas = new Canvas();
        canvas.setWidth(20);
        canvas.setHeight(4);
        canvas.initCanvas();

        bCommandTest = new BCommand(new BCommandCanvasOperator(canvas));
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testExecuteMethod() throws CustomException {
        if (expectedExceptionMsg != null) {
            exceptionRule.expect(CustomException.class);
            exceptionRule.expectMessage(expectedExceptionMsg);
            bCommandTest.execute(command);
        } else {
            bCommandTest.execute(command);
            assertEquals(expectedResult, outContent.toString());
        }
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

}
