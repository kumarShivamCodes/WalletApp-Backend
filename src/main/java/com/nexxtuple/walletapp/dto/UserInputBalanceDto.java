package com.nexxtuple.walletapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInputBalanceDto {
    private String username;
    private Integer amount;
    private String accNo;
}
