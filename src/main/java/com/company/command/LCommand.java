package com.company.command;

import com.company.CustomException;
import com.company.operator.LCommandCanvasOperator;

import static com.company.Constants.*;

public class LCommand implements Command {

    LCommandCanvasOperator lCommandCanvasOperator;
    int x1, x2, y1, y2 = 0;

    public LCommand(LCommandCanvasOperator lCommandCanvasOperator) {
        this.lCommandCanvasOperator = lCommandCanvasOperator;
    }

    @Override
    public void execute(String... arguments) throws CustomException {
        if (lCommandCanvasOperator.isCanvasInitialized()) {
            clearTemporaryData();
            if (isValidInput(arguments)) {
                if ((x1 == x2) && (y1 != y2)) {
                    if (y1 < y2) {
                        printVerticalLine(x1, y1, y2);
                    } else {
                        printVerticalLine(x1, y2, y1);
                    }
                } else if ((y1 == y2) && (x1 != x2)) {
                    if (x1 < x2) {
                        printHorizontalLine(y1, x1, x2);
                    } else {
                        printHorizontalLine(y1, x2, x1);
                    }
                } else {
                    throw new CustomException(SUPPORT_HORIZONTAL_AND_VERTICAL_LINES_ONLY);
                }
            }
        } else {
            throw new CustomException(ERROR_CANVAS_NOT_INITIALIZED);
        }
    }

    @Override
    public boolean isValidInput(String... arguments) throws CustomException {
        try {
            x1 = Integer.parseInt(arguments[0]);
            if (x1 <= 0) {
                throw new CustomException(ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO, "x1");
            } else if (x1 > lCommandCanvasOperator.getWidth()) {
                throw new CustomException(ERROR_X_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_WIDTH, "x1");
            }
        } catch (NumberFormatException nfe) {
            throw new CustomException(ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES, "x1");
        }
        try {
            y1 = Integer.parseInt(arguments[1]);
            if (y1 <= 0) {
                throw new CustomException(ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO, "y1");
            } else if (y1 > lCommandCanvasOperator.getHeight()) {
                throw new CustomException(ERROR_Y_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_HEIGHT, "y1");
            }
        } catch (NumberFormatException nfe) {
            throw new CustomException(ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES, "y1");
        }
        try {
            x2 = Integer.parseInt(arguments[2]);
            if (x2 <= 0) {
                throw new CustomException(ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO, "x2");
            } else if (x2 > lCommandCanvasOperator.getWidth()) {
                throw new CustomException(ERROR_X_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_WIDTH, "x2");
            }
        } catch (NumberFormatException nfe) {
            throw new CustomException(ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES, "x2");
        }
        try {
            y2 = Integer.parseInt(arguments[3]);
            if (y2 <= 0) {
                throw new CustomException(ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO, "y2");
            } else if (y2 > lCommandCanvasOperator.getHeight()) {
                throw new CustomException(ERROR_Y_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_HEIGHT, "y2");
            }
        } catch (NumberFormatException nfe) {
            throw new CustomException(ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES, "y2");
        }

        return true;
    }

    @Override
    public void clearTemporaryData() {
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
    }

    private void printVerticalLine(int x, int y1, int y2) throws CustomException {
        for (int i = y1; i <= y2; i++) {
            lCommandCanvasOperator.updateCanvasPoint(x, i, DEFAULT_DRAWING_CHARACTER);
        }
    }

    private void printHorizontalLine(int y, int x1, int x2) throws CustomException {
        for (int j = x1; j <= x2; j++) {
            lCommandCanvasOperator.updateCanvasPoint(j, y, DEFAULT_DRAWING_CHARACTER);
        }
    }

    public void printCanvas() throws CustomException {
        lCommandCanvasOperator.printCanvas();
    }
}
