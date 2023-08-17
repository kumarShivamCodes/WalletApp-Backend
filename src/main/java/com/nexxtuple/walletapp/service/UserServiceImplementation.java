package com.nexxtuple.walletapp.service;

import com.nexxtuple.walletapp.dao.UserDao;
import com.nexxtuple.walletapp.dto.UserInputRegisterDto;
import com.nexxtuple.walletapp.dto.UserOutputDto;
import com.nexxtuple.walletapp.entity.Transaction;
import com.nexxtuple.walletapp.entity.User;
import com.nexxtuple.walletapp.exception.InvalidAmountException;
import com.nexxtuple.walletapp.exception.PasswordMismatchException;
import com.nexxtuple.walletapp.exception.UserAlreadyExistsException;
import com.nexxtuple.walletapp.exception.UserNotFoundException;
import com.nexxtuple.walletapp.factory.TransactionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserDao userdao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransactionService transactionService;

    @Override
    public List<User> getAllUsers() {
        return userdao.getAllUsers();
    }

    @Override
    public UserOutputDto getUser(String username,String password) {
        User user = userdao.getByUserName(username);

        if (user == null) {
            throw new UserNotFoundException("User not found: "+username);
        } else if (!password.equals(user.getPassword())) {
            throw new PasswordMismatchException("Password mismatch");
        }
        UserOutputDto userOutputDto=modelMapper.map(user,UserOutputDto.class);
        return userOutputDto;
    }

    @Override
    public UserOutputDto addUser(UserInputRegisterDto userInputRegisterDto) {
        User user=modelMapper.map(userInputRegisterDto,User.class);
        User existingUser=userdao.getByUserName(user.getUsername());
        if(existingUser!=null)
        {
            throw new UserAlreadyExistsException("User already exists: "+ user.getUsername());
        }
        //check if any user with same email already exists
        User existingUser2=userdao.getUserByEmail(userInputRegisterDto.getEmail());
        if(existingUser2!=null)
        {
            throw new UserAlreadyExistsException("Email is already registered:"+userInputRegisterDto.getEmail());
        }

        User savedUser=userdao.addUser(user);
        //Use model mapper to map user to UserDtoOutput
        UserOutputDto userOutputDto=modelMapper.map(savedUser,UserOutputDto.class);

        return userOutputDto;
    }

    @Override
    public UserOutputDto updateUserBalance(String username, Integer amount,String accNo) {
        User user=userdao.getByUserName(username);
        if (user == null) {
            throw new UserNotFoundException("User not found: "+username);
        }
        User userWithUpdatedBalance=userdao.updateUserBalance(user.getId(),amount);

        Transaction rechargeTransaction= TransactionFactory.createRechargeTransaction(username,amount,accNo);
        transactionService.createTransaction(rechargeTransaction);

        UserOutputDto userOutputDto=modelMapper.map(userWithUpdatedBalance,UserOutputDto.class);
        return userOutputDto ;
    }

    @Override
    public UserOutputDto transferAmount(String username, Integer amount,String accNo) {

        User user=userdao.getByUserName(username);
        if (user == null) {
            throw new UserNotFoundException("User not found: "+username);
        }
        else if(amount > user.getBalance())
        {
            throw new InvalidAmountException("Amount should be equal to or less current balance");
        }
        else
        {
            User userWithUpdatedBalance= userdao.transferAmount(user.getId(),amount);
            UserOutputDto userOutputDto=modelMapper.map(userWithUpdatedBalance,UserOutputDto.class);
            Transaction transferTransaction = TransactionFactory.createTransferTransaction(username, amount,accNo);
            transactionService.createTransaction(transferTransaction);
            return userOutputDto ;
        }

    }
}
