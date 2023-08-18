package com.nexxtuple.walletapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserOutputDto {

    private String username;
    private Integer balance;
    private Integer accNo;
}
