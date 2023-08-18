package com.nexxtuple.walletapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionOutputDto {

    private String username;
    private String type;
    private Integer transactionAmount;
    private String accNo;
    private String createdAt;
}
