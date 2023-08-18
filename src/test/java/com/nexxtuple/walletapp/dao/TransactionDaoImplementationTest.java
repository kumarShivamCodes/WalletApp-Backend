package com.nexxtuple.walletapp.dao;

import com.nexxtuple.walletapp.entity.Transaction;
import com.nexxtuple.walletapp.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionDaoImplementationTest {

    @InjectMocks
    private TransactionDaoImplementation transactionDao;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public  void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTransactionsByUsername_Success() {
        String username = "user1";

        Transaction transaction1 = new Transaction( username,  "Recharge",100,"12345");
        Transaction transaction2 = new Transaction( username,  "Transfer",200,"12346");

        List<Transaction> expectedTransactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findByUsername(username)).thenReturn(expectedTransactions);

        List<Transaction> actualTransactions = transactionDao.getTransactionsByUsername(username);

        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    public void testCreateTransaction_Success() {
        Transaction transaction = new Transaction( "shivam",  "Recharge",100,"12345");

        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction createdTransaction = transactionDao.createTransaction(transaction);

        assertEquals(transaction, createdTransaction);
    }
}
