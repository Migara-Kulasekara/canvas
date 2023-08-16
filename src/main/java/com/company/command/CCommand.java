package com.company.command;

import com.company.CustomException;
import com.company.operator.CCommandCanvasOperator;

import static com.company.Constants.*;

public class CCommand implements Command {

    CCommandCanvasOperator cCommandCanvasOperator;
    int width = 0;
    int height = 0;

    public CCommand(CCommandCanvasOperator cCommandCanvasOperator) {
        this.cCommandCanvasOperator = cCommandCanvasOperator;
    }

    @Override
    public void execute(String... arguments) throws CustomException {
        if (!cCommandCanvasOperator.isCanvasInitialized()) {
            clearTemporaryData();
            if (isValidInput(arguments)) {
                cCommandCanvasOperator.setCanvasWidth(width);
                cCommandCanvasOperator.setCanvasHeight(height);
                cCommandCanvasOperator.initCanvas();
                cCommandCanvasOperator.printCanvas();
            }
        } else {
            throw new CustomException(ERROR_CANVAS_IS_ALREADY_DRAWN);
        }
    }

    @Override
    public boolean isValidInput(String... arguments) throws CustomException {
        try {
            width = Integer.parseInt(arguments[0]);
            if (width <= 0) {
                throw new CustomException(ERROR_CANVAS_WIDTH_SHOULD_BE_GREATER_THAN_ZERO, arguments[0]);
            } else if (width > MAX_CANVAS_WIDTH) {
                throw new CustomException(ERROR_MAX_CANVAS_WIDTH + MAX_CANVAS_WIDTH);
            }
        } catch (NumberFormatException nfe) {
            throw new CustomException(ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_WIDTH, arguments[0]);
        }
        try {
            height = Integer.parseInt(arguments[1]);
            if (height <= 0) {
                throw new CustomException(ERROR_CANVAS_HEIGHT_SHOULD_BE_GREATER_THAN_ZERO, arguments[1]);
            } else if (height > MAX_CANVAS_HEIGHT) {
                throw new CustomException(ERROR_MAX_CANVAS_HEIGHT + MAX_CANVAS_HEIGHT);
            }
        } catch (NumberFormatException nfe) {
            throw new CustomException(ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_HEIGHT, arguments[1]);
        }
        return true;
    }

    @Override
    public void clearTemporaryData() {
        width = 0;
        height = 0;
    }
}