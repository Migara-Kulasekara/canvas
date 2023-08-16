package com.company.command;

import com.company.Constants;
import com.company.CustomException;
import com.company.operator.BCommandCanvasOperator;

import static com.company.Constants.*;

public class BCommand implements Command {
    BCommandCanvasOperator bCommandCanvasOperator;
    int x, y = 0;
    char color = '\0';

    public BCommand(BCommandCanvasOperator bCommandCanvasOperator) {
        this.bCommandCanvasOperator = bCommandCanvasOperator;
    }

    @Override
    public void execute(String... arguments) throws CustomException {
        if (bCommandCanvasOperator.isCanvasInitialized()) {
            clearTemporaryData();
            if (isValidInput(arguments)) {
                bucketFill(x, y, color);
                printCanvas();
            }
        } else {
            throw new CustomException(Constants.ERROR_CANVAS_NOT_INITIALIZED);
        }
    }

    @Override
    public boolean isValidInput(String... arguments) throws CustomException {
        try {
            x = Integer.parseInt(arguments[0]);
            if (x <= 0) {
                throw new CustomException(ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO, X);
            } else if (x > bCommandCanvasOperator.getWidth()) {
                throw new CustomException(ERROR_X_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_WIDTH, X);
            }
        } catch (NumberFormatException nfe) {
            throw new CustomException(ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES, X);
        }
        try {
            y = Integer.parseInt(arguments[1]);
            if (y <= 0) {
                throw new CustomException(ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO, Y);
            } else if (y > bCommandCanvasOperator.getHeight()) {
                throw new CustomException(ERROR_Y_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_HEIGHT, Y);
            }
        } catch (NumberFormatException nfe) {
            throw new CustomException(ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES, Y);
        }
        if (arguments[2].length() > 1) {
            throw new CustomException(ERROR_INVALID_COLOR, arguments[2]);
        }
        color = arguments[2].charAt(0);

        return true;
    }

    @Override
    public void clearTemporaryData() {
        x = 0;
        y = 0;
        color = '\0';
    }

    private void printCanvas() throws CustomException {
        bCommandCanvasOperator.printCanvas();
    }

    private void bucketFillUtil(int x, int y, int prevColor, char newColor) throws CustomException {

        if (x < 1 || x > bCommandCanvasOperator.getWidth() || y < 1 || y > bCommandCanvasOperator.getHeight())
            return;
        if (bCommandCanvasOperator.getCanvasPoint(x, y) != prevColor)
            return;

        bCommandCanvasOperator.updateCanvasPoint(x, y, newColor);

        // Recur for north, east, south and west
        bucketFillUtil(x + 1, y, prevColor, newColor);
        bucketFillUtil(x - 1, y, prevColor, newColor);
        bucketFillUtil(x, y + 1, prevColor, newColor);
        bucketFillUtil(x, y - 1, prevColor, newColor);
    }


    private void bucketFill(int x, int y, char color) throws CustomException {
        char prevColor = bCommandCanvasOperator.getCanvasPoint(x, y);
        if (prevColor == color) {
            return;
        }
        bucketFillUtil(x, y, prevColor, color);
    }
}
