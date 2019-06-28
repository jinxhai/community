package com.woniu.community.exception;

public enum CustomerErrorCodeEnum implements CustomerErrorCode {
    ;
    private String message;

    public String getMessage() {
        return message;
    }

    CustomerErrorCodeEnum(String message){
        this.message=message;
    }


}
