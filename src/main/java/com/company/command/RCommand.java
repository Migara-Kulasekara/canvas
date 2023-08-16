package com.company.command;

import com.company.Constants;
import com.company.CustomException;
import com.company.operator.RCommandCanvasOperator;

import static com.company.Constants.*;

public class RCommand implements Command {
    RCommandCanvasOperator rCommandCanvasOperator;
    LCommand lCommand;
    int x1, y1, x2, y2 = 0;
    String x01, x02, y01, y02 = EMPTY_STRING;

    public RCommand(RCommandCanvasOperator rCommandCanvasOperator, LCommand lCommand) {
        this.lCommand = lCommand;
        this.rCommandCanvasOperator = rCommandCanvasOperator;
    }

    @Override
    public void execute(String... arguments) throws CustomException {
        if (rCommandCanvasOperator.isCanvasInitialized()) {
            clearTemporaryData();
            if (isValidInput(arguments)) {
                lCommand.execute(x01, y01, x01, y02);
                lCommand.execute(x02, y01, x02, y02);
                lCommand.execute(x01, y01, x02, y01);
                lCommand.execute(x01, y02, x02, y02);

                rCommandCanvasOperator.printCanvas();
            }
        } else {
            throw new CustomException(Constants.ERROR_CANVAS_NOT_INITIALIZED);
        }
    }

    @Override
    public boolean isValidInput(String... arguments) throws CustomException {
        try {
            x1 = Integer.parseInt(arguments[0]);
            if (x1 <= 0) {
                throw new CustomException(ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO, X1);
            } else if (x1 > rCommandCanvasOperator.getWidth()) {
                throw new CustomException(ERROR_X_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_WIDTH, X1);
            }
            x01 = arguments[0];
        } catch (NumberFormatException nfe) {
            throw new CustomException(ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES, X1);
        }
        try {
            y1 = Integer.parseInt(arguments[1]);
            if (y1 <= 0) {
                throw new CustomException(ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO, Y1);
            } else if (y1 > rCommandCanvasOperator.getHeight()) {
                throw new CustomException(ERROR_Y_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_HEIGHT, Y1);
            }
            y01 = arguments[1];
        } catch (NumberFormatException nfe) {
            throw new CustomException(ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES, Y1);
        }
        try {
            x2 = Integer.parseInt(arguments[2]);
            if (x2 <= 0) {
                throw new CustomException(ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO, X2);
            } else if (x2 > rCommandCanvasOperator.getWidth()) {
                throw new CustomException(ERROR_X_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_WIDTH, X2);
            }
            x02 = arguments[2];
        } catch (NumberFormatException nfe) {
            throw new CustomException(ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES, X2);
        }
        try {
            y2 = Integer.parseInt(arguments[3]);
            if (y2 <= 0) {
                throw new CustomException(ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO, Y2);
            } else if (y2 > rCommandCanvasOperator.getHeight()) {
                throw new CustomException(ERROR_Y_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_HEIGHT, Y2);
            }
            y02 = arguments[3];
        } catch (NumberFormatException nfe) {
            throw new CustomException(ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES, Y2);
        }
        if (x1 == x2 || y1 == y2) {
            throw new CustomException(ERROR_CANNOT_DRAW_RECTANGLE_WITH_GIVEN_COORDINATES);
        }

        return true;
    }

    @Override
    public void clearTemporaryData() {
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
        x01 = EMPTY_STRING;
        x02 = EMPTY_STRING;
        y01 = EMPTY_STRING;
        y02 = EMPTY_STRING;
    }

}
