package com.nexxtuple.walletapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInputLoginDto {

    private String username;
    private String password;
}
