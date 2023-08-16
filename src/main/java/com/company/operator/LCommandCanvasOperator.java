package com.company.operator;

import com.company.Canvas;

public class LCommandCanvasOperator extends BaseCanvasOperator {

    public LCommandCanvasOperator(Canvas canvas) {
        super(canvas);
    }

    public int getWidth() {
        return canvas.getWidth();
    }

    public int getHeight() {
        return canvas.getHeight();
    }
}
