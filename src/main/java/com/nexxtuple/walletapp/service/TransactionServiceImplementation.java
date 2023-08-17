package com.nexxtuple.walletapp.service;

import com.nexxtuple.walletapp.dao.TransactionDao;
import com.nexxtuple.walletapp.dao.UserDao;
import com.nexxtuple.walletapp.entity.Transaction;
import com.nexxtuple.walletapp.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImplementation implements TransactionService {
    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Transaction> getAllTransactionsByUsername(String username) {

        return transactionDao.getTransactionsByUsername(username);
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {

        //convert to transaction entity

        User existingUser=findUserByName(transaction.getUsername());
        if(existingUser!=null)
        {
            Transaction newTransaction=transactionDao.createTransaction(transaction);
            return newTransaction;
        }

        return null;

    }

    @Override
    public User findUserByName(String username) {
        return userDao.getByUserName(username);
    }


}
