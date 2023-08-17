package com.nexxtuple.walletapp.exception;

public class InvalidAmountException extends RuntimeException {
    private String message;

    public InvalidAmountException(String msg)
    {
        super();
        this.message=msg;
    }
    public String getMessage(){
        return message;
    }
}
