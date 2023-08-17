package com.nexxtuple.walletapp.dto;

import lombok.Data;

@Data
public class TransactionOutputDto {

    private String username;
    private String type;
    private Integer transactionAmount;
    private String accNo;
    private String createdAt;
}
