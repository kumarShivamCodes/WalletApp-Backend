package com.nexxtuple.walletapp.repository;

import com.nexxtuple.walletapp.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction,String> {

    List<Transaction> findByUsername(String username);
}
