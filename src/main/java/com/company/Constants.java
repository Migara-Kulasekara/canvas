package com.company;

public final class Constants {
    public static final char DEFAULT_DRAWING_CHARACTER = 'x';
    public static final String MAX_CANVAS_WIDTH_TEXT = "max.canvas.width";
    public static final String MAX_CANVAS_HEIGHT_TEXT = "max.canvas.height";
    public static final String ENTER_COMMAND = "enter command: ";
    public static final String SPLIT_BY = "\\s+";
    public static final String EMPTY_STRING = "";
    public static final String C_COMMAND = "C";
    public static final String L_COMMAND = "L";
    public static final String R_COMMAND = "R";
    public static final String Q_COMMAND = "Q";
    public static final String B_COMMAND = "B";
    public static final String X1 = "x1";
    public static final String X2 = "x2";
    public static final String Y1 = "y1";
    public static final String Y2 = "y2";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String CONFIG_FILE_NAME = "config.properties";
    public static final String EXCEPTION_MSG_SEPARATOR = " : ";
    public static final String ERROR_PROPERTY_FILE_NOT_FOUND = "Property file not found.";
    public static final String ERROR_INVALID_NUM_OF_ARGUMENTS = "Invalid number of arguments for the command.";
    public static final String ERROR_CANVAS_NOT_INITIALIZED = "Please draw canvas first.";
    public static final String ERROR_INVALID_COMMAND = "Please enter a valid command.";
    public static final String ERROR_CANVAS_WIDTH_SHOULD_BE_GREATER_THAN_ZERO = "Canvas width should be greater than zero.";
    public static final String ERROR_CANVAS_HEIGHT_SHOULD_BE_GREATER_THAN_ZERO = "Canvas height should be greater than zero.";
    public static final String ERROR_CANVAS_IS_ALREADY_DRAWN = "Canvas is already drawn.";
    public static final String ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_WIDTH = "Expect numeric value for canvas width.";
    public static final String ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_HEIGHT = "Expect numeric value for canvas height.";
    public static final String ERROR_EXPECT_NUMERIC_VALUES_FOR_CANVAS_COORDINATES = "Expect numeric values for canvas coordinates.";
    public static final String SUPPORT_HORIZONTAL_AND_VERTICAL_LINES_ONLY = "Program currently draws horizontal and vertical lines only.";
    public static final String ERROR_COORDINATE_VALUE_SHOULD_BE_GREATER_THAN_ZERO = "Canvas coordinate values should be greater than zero.";
    public static final String ERROR_X_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_WIDTH = "x coordinate value should be lower than or equal to canvas width.";
    public static final String ERROR_Y_COORDINATE_VALUE_SHOULD_BE_LOWER_OR_EQUAL_TO_CANVAS_HEIGHT = "y coordinate value should be lower than or equal to canvas height.";
    public static final String ERROR_CANNOT_DRAW_RECTANGLE_WITH_GIVEN_COORDINATES = "Cannot draw a rectangle with given coordinates.";
    public static final String ERROR_INVALID_COLOR = "Invalid color.Please enter a single character color.";
    public static final String ERROR_MAX_CANVAS_WIDTH = "Max canvas width : ";
    public static final String ERROR_MAX_CANVAS_HEIGHT = "Max canvas height : ";
    static final String ERROR_READING_COMMAND = "Error reading command.";
    public static int MAX_CANVAS_WIDTH = 100;
    public static int MAX_CANVAS_HEIGHT = 100;
    public static final String MAX_CANVAS_WIDTH_HEIGHT = "Max canvas width : " + MAX_CANVAS_WIDTH + ". Max canvas height : " + MAX_CANVAS_HEIGHT;
}
   