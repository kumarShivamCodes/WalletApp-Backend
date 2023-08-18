package com.nexxtuple.walletapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInputRegisterDto {

    private String username;
    private String email;
    private String password;

}
