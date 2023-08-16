package com.company.operator;

import com.company.Canvas;
import com.company.CustomException;

public class CCommandCanvasOperator extends BaseCanvasOperator {
    public CCommandCanvasOperator(Canvas canvas) {
        super(canvas);
    }

    public void setCanvasWidth(int width) {
        canvas.setWidth(width);
    }

    public void setCanvasHeight(int height) {
        canvas.setHeight(height);
    }

    public void initCanvas() throws CustomException {
        canvas.initCanvas();
    }
}
