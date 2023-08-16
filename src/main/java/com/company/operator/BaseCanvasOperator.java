package com.company.operator;

import com.company.Canvas;
import com.company.CustomException;

public class BaseCanvasOperator {
    Canvas canvas;

    public BaseCanvasOperator(Canvas canvas) {
        this.canvas = canvas;
    }

    public void updateCanvasPoint(int x, int y, char character) throws CustomException {
        canvas.updateCanvasPoint(x - 1, y - 1, character);
    }

    public void printCanvas() throws CustomException {
        canvas.printCanvas();
    }

    public boolean isCanvasInitialized() {
        return canvas.isCanvasInitialized();
    }
}
