package com.company;

import static com.company.Constants.EXCEPTION_MSG_SEPARATOR;

public class CustomException extends Exception {
    private String data;

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException(String msg, String data) {
        super(msg);
        this.data = data;
    }

    public String getMessage() {
        if (this.data == null) {
            return super.getMessage();
        } else {
            return super.getMessage() + EXCEPTION_MSG_SEPARATOR + data;
        }
    }

}
