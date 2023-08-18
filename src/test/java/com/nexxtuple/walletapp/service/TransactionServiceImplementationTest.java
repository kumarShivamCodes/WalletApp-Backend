package com.nexxtuple.walletapp.service;

import com.nexxtuple.walletapp.dao.TransactionDao;
import com.nexxtuple.walletapp.dao.UserDao;
import com.nexxtuple.walletapp.entity.Transaction;
import com.nexxtuple.walletapp.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


public class TransactionServiceImplementationTest {

    @InjectMocks
    private TransactionServiceImplementation transactionService;

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private UserDao userDao;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTransactionsByUsername_Success() {
        String username = "user1";

        Transaction transaction1 = new Transaction( username,  "Recharge",100,"12345");
        Transaction transaction2 = new Transaction( username,  "Transfer",200,"12346");

        List<Transaction> expectedTransactions = Arrays.asList(transaction1, transaction2);

        when(transactionDao.getTransactionsByUsername(username)).thenReturn(expectedTransactions);

        List<Transaction> actualTransactions = transactionService.getAllTransactionsByUsername(username);

        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    public void testCreateTransaction_Success() {
        Transaction transaction = new Transaction( "user1",  "Recharge",100,"12345");
        User existingUser = new User("user1", "user1@example.com", "password");

        when(userDao.getByUserName(transaction.getUsername())).thenReturn(existingUser);
        when(transactionDao.createTransaction(transaction)).thenReturn(transaction);

        Transaction createdTransaction = transactionService.createTransaction(transaction);

        assertEquals(transaction, createdTransaction);
    }

    @Test
    public void testCreateTransaction_UserNotFound() {
        Transaction transaction = new Transaction( "user1",  "Recharge",100,"12345");

        when(userDao.getByUserName(transaction.getUsername())).thenReturn(null);

        Transaction createdTransaction = transactionService.createTransaction(transaction);

        assertNull(createdTransaction);
    }
}
