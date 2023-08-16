package com.company.operator;

import com.company.Canvas;
import com.company.CustomException;

public class BCommandCanvasOperator extends BaseCanvasOperator {
    public BCommandCanvasOperator(Canvas canvas) {
        super(canvas);
    }

    public int getWidth() {
        return canvas.getWidth();
    }

    public int getHeight() {
        return canvas.getHeight();
    }

    public char getCanvasPoint(int x, int y) throws CustomException {
        return canvas.getCanvasPoint(x - 1, y - 1);
    }
}
