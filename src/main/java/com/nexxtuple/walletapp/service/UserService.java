package com.nexxtuple.walletapp.service;

import com.nexxtuple.walletapp.dto.UserInputRegisterDto;
import com.nexxtuple.walletapp.dto.UserOutputDto;
import com.nexxtuple.walletapp.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    UserOutputDto getUser(String username,String password);

    UserOutputDto addUser(UserInputRegisterDto userInputRegisterDto);

    UserOutputDto updateUserBalance(String username,Integer amount,String accNo);

    UserOutputDto transferAmount(String username,Integer amount, String accNo);
}
