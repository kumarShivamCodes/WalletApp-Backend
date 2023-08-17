package com.nexxtuple.walletapp.exception;

public class UserNotFoundException extends  RuntimeException {
    private String message;
    public UserNotFoundException(String msg)
    {
        super();
        this.message=msg;
    }

    public String getMessage(){
        return message;
    }
}
