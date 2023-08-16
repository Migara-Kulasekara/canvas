package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.company.Constants.*;

public class PropertyReader {

    static FileInputStream inputStream;
    static String maxCanvasWidth, maxCanvasHeight;

    public static void readPropValues() throws CustomException {
        try {
            Properties prop = new Properties();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            InputStream inputStream = classLoader.getResourceAsStream(CONFIG_FILE_NAME);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new CustomException(ERROR_PROPERTY_FILE_NOT_FOUND + MAX_CANVAS_WIDTH_HEIGHT);
            }

            maxCanvasWidth = prop.getProperty(MAX_CANVAS_WIDTH_TEXT);
            maxCanvasHeight = prop.getProperty(MAX_CANVAS_HEIGHT_TEXT);

            if (!maxCanvasWidth.equals(EMPTY_STRING)) {
                MAX_CANVAS_WIDTH = Integer.parseInt(maxCanvasWidth);
            }
            if (!maxCanvasHeight.equals(EMPTY_STRING)) {
                MAX_CANVAS_HEIGHT = Integer.parseInt(maxCanvasHeight);
            }

        } catch (Exception e) {
            //log error
            throw new CustomException(MAX_CANVAS_WIDTH_HEIGHT);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //log error
                }
            }
        }

    }

}

