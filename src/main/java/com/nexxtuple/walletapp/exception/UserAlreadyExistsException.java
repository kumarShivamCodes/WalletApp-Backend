package com.nexxtuple.walletapp.exception;

public class UserAlreadyExistsException extends RuntimeException{

    private String message;
    public UserAlreadyExistsException(String msg)
    {
        super();
        this.message=msg;
    }

    public String getMessage(){
        return message;
    }
}
