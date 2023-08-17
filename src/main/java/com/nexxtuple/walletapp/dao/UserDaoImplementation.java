package com.nexxtuple.walletapp.dao;


import com.nexxtuple.walletapp.entity.User;
import com.nexxtuple.walletapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImplementation implements UserDao{

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUserBalance(String userId, Integer amount) {
        User user=userRepository.findById(userId).orElse(null);
        if(user!=null)
        {
            user.setBalance(user.getBalance()+amount);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User transferAmount(String userId, Integer amount) {
        User user=userRepository.findById(userId).orElse(null);

        if(user!=null)
        {
            user.setBalance(user.getBalance()-amount);
            return userRepository.save(user);
        }
        return null;
    }

}
