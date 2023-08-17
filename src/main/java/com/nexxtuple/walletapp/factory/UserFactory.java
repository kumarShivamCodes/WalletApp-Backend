package com.nexxtuple.walletapp.factory;

import com.nexxtuple.walletapp.entity.User;

public class UserFactory {
    public static User createUser(String username, String email, String password) {
        return new User(username, email, password);
    }
}