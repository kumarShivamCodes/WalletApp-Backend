package com.nexxtuple.walletapp.dao;

import com.nexxtuple.walletapp.entity.Transaction;

import java.util.List;

public interface TransactionDao {

    List<Transaction> getTransactionsByUsername(String username);
    Transaction createTransaction(Transaction transaction);
}
