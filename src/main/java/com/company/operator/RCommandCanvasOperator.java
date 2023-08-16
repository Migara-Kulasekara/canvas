package com.company.operator;

import com.company.Canvas;

public class RCommandCanvasOperator extends BaseCanvasOperator {
    public RCommandCanvasOperator(Canvas canvas) {
        super(canvas);
    }

    public int getWidth() {
        return canvas.getWidth();
    }

    public int getHeight() {
        return canvas.getHeight();
    }
}
