package com.nexxtuple.walletapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int StatusCode;
    private String message;

    public ErrorResponse(String msg)
    {
        super();
        this.message=msg;
    }
}
