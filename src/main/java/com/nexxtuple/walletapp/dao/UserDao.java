package com.nexxtuple.walletapp.dao;

import com.nexxtuple.walletapp.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getByUserName(String username);

    User getUserByEmail(String email);

    User addUser(User user);

    User updateUserBalance(String userId,Integer amount);

    User transferAmount(String userId,Integer amount);
}