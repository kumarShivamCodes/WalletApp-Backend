package com.nexxtuple.walletapp.dao;


import com.nexxtuple.walletapp.entity.User;
import com.nexxtuple.walletapp.factory.UserFactory;
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
        User newUser = UserFactory.createUser(user.getUsername(), user.getEmail(), user.getPassword());
        return userRepository.save(newUser);
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

    @Override
    public User getUserByAccNo(Integer accNo) {
        return userRepository.findByAccNo(accNo);
    }

}
