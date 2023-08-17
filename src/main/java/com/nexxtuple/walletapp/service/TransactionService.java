package com.nexxtuple.walletapp.service;

import com.nexxtuple.walletapp.entity.Transaction;
import com.nexxtuple.walletapp.entity.User;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactionsByUsername(String username);

    Transaction createTransaction(Transaction transaction);

    User findUserByName(String username);

}
