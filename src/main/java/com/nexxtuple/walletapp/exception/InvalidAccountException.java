package com.nexxtuple.walletapp.exception;

public class InvalidAccountException extends  RuntimeException{

    private String message;

    public InvalidAccountException(String msg)
    {
        super();
        this.message=msg;
    }
    public String getMessage(){
        return message;
    }
}
