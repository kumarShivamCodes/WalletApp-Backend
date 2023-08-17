package com.nexxtuple.walletapp.exception;

public class PasswordMismatchException extends RuntimeException{
    private String message;

    public PasswordMismatchException(String msg)
    {
        super();
        this.message=msg;
    }

    public String getMessage(){
        return message;
    }
}
