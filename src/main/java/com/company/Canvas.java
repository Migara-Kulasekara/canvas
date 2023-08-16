package com.company;

import static com.company.Constants.ERROR_CANVAS_IS_ALREADY_DRAWN;
import static com.company.Constants.ERROR_CANVAS_NOT_INITIALIZED;

public class Canvas {
    private int width;
    private int height;
    private char[][] canvasArray;
    private boolean isCanvasInitialized;

    public Canvas() {
        width = 0;
        height = 0;
        canvasArray = null;
        isCanvasInitialized = false;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void initCanvas() throws CustomException {
        if (!isCanvasInitialized) {
            canvasArray = new char[width][height];
            setCanvasInitFlag(true);
        } else {
            throw new CustomException(ERROR_CANVAS_IS_ALREADY_DRAWN);
        }
    }

    public boolean isCanvasInitialized() {
        return this.isCanvasInitialized;
    }

    public void setCanvasInitFlag(boolean value) {
        this.isCanvasInitialized = value;
    }

    public void updateCanvasPoint(int x, int y, char character) throws CustomException {
        if (isCanvasInitialized()) {
            canvasArray[x][y] = character;
        } else {
            throw new CustomException(ERROR_CANVAS_NOT_INITIALIZED);
        }
    }

    public char getCanvasPoint(int x, int y) throws CustomException {
        if (isCanvasInitialized()) {
            return canvasArray[x][y];
        } else {
            throw new CustomException(ERROR_CANVAS_NOT_INITIALIZED);
        }
    }

    public void printCanvas() throws CustomException {
        if (canvasArray == null) {
            throw new CustomException(ERROR_CANVAS_NOT_INITIALIZED);
        } else {
            if (height > 0 && width > 0) {
                for (int i = 0; i <= height + 1; i++) {
                    for (int j = 0; j <= width + 1; j++) {
                        if (i == 0 || i == (height + 1)) {
                            System.out.print('-');
                        } else if (j == 0 || j == (width + 1)) {
                            System.out.print('|');
                        } else {
                            if (canvasArray[j - 1][i - 1] != 0) {//check for 0 input
                                System.out.print(canvasArray[j - 1][i - 1]);
                            } else {
                                System.out.print(' ');
                            }
                        }
                    }
                    System.out.println();
                }
            }
        }

    }

}
