package com.nexxtuple.walletapp.factory;

import com.nexxtuple.walletapp.entity.Transaction;

public class TransactionFactory {

    public static Transaction createRechargeTransaction(String username, Integer amount,String accNo) {
        return new Transaction(username, "recharge", amount,accNo);
    }

    public static Transaction createTransferTransaction(String username, Integer amount,String accNo) {
        return new Transaction(username, "transfer", amount,accNo);
    }
}
