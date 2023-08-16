package com.company.CommandTest;

import com.company.Canvas;
import com.company.CustomException;
import com.company.command.LCommand;
import com.company.operator.LCommandCanvasOperator;
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
public class LCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    LCommand lCommandTest;
    Canvas canvas;
    String[] command;
    String expectedResult;
    String expectedExceptionMsg;

    public LCommandTest(String[] command, String expectedResult, String expectedExceptionMsg) {
        this.command = command;
        this.expectedResult = expectedResult;
        this.expectedExceptionMsg = expectedExceptionMsg;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"0", "2", "6", "2"}, null, ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO},
                {new String[]{"1", "0", "6", "2"}, null, ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO},
                {new String[]{"1", "2", "0", "2"}, null, ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO},
                {new String[]{"1", "2", "6", "0"}, null, ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO},
                {new String[]{"-1", "2", "6", "2"}, null, ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO},
                {new String[]{"1", "-1", "6", "2"}, null, ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO},
                {new String[]{"1", "2", "-1", "2"}, null, ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO},
                {new String[]{"1", "2", "6", "-1"}, null, ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO},
                {new String[]{"a", "2", "6", "2"}, null, ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES},
                {new String[]{"1", "b", "1", "2"}, null, ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES},
                {new String[]{"1", "2", "c", "2"}, null, ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES},
                {new String[]{"1", "2", "1", "d"}, null, ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES},
                {new String[]{"1", "2", "3", "4"}, null, SUPPORT_HORIZONTAL_AND_VERTICAL_LINES_ONLY},
                {new String[]{"1", "2", "1", "2"}, null, SUPPORT_HORIZONTAL_AND_VERTICAL_LINES_ONLY},
                {new String[]{"1", "2", "8", "2"}, "----------------------\r\n" +
                        "|                    |\r\n" +
                        "|xxxxxxxx            |\r\n" +
                        "|                    |\r\n" +
                        "|                    |\r\n" +
                        "----------------------\r\n", null},
                {new String[]{"1", "1", "1", "4"}, "----------------------\r\n" +
                        "|x                   |\r\n" +
                        "|x                   |\r\n" +
                        "|x                   |\r\n" +
                        "|x                   |\r\n" +
                        "----------------------\r\n", null},
                {new String[]{"1", "1", "20", "1"}, "----------------------\r\n" +
                        "|xxxxxxxxxxxxxxxxxxxx|\r\n" +
                        "|                    |\r\n" +
                        "|                    |\r\n" +
                        "|                    |\r\n" +
                        "----------------------\r\n", null},
                {new String[]{"21", "2", "3", "2"}, null, ERROR_X_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_WIDTH + EXCEPTION_MSG_SEPARATOR + X1},
                {new String[]{"20", "4", "23", "4"}, null, ERROR_X_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_WIDTH + EXCEPTION_MSG_SEPARATOR + X2},
                {new String[]{"20", "5", "20", "4"}, null, ERROR_Y_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_HEIGHT + EXCEPTION_MSG_SEPARATOR + Y1},
                {new String[]{"20", "2", "20", "5"}, null, ERROR_Y_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_HEIGHT + EXCEPTION_MSG_SEPARATOR + Y2}
        });

    }

    @Before
    public void setUp() throws CustomException {
        if (canvas == null) {
            canvas = new Canvas();
            canvas.setWidth(20);
            canvas.setHeight(4);
            canvas.initCanvas();
            this.lCommandTest = new LCommand(new LCommandCanvasOperator(canvas));
        }
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testExecuteMethod() throws CustomException {
        if (expectedExceptionMsg != null) {
            exceptionRule.expect(CustomException.class);
            exceptionRule.expectMessage(expectedExceptionMsg);
            lCommandTest.execute(command);
            lCommandTest.printCanvas();
        } else {
            lCommandTest.execute(command);
            lCommandTest.printCanvas();
            assertEquals(expectedResult, outContent.toString());
        }
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

}

