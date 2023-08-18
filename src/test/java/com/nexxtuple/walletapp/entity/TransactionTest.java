package com.nexxtuple.walletapp.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void testConstructorAndGetterSetter() {
        String username = "testuser";
        String type = "recharge";
        Integer transactionAmount = 50;
        String accNo = "12345678";

        Transaction transaction = new Transaction();
        transaction.setAccNo(accNo);
        transaction.setUsername(username);
        transaction.setType(type);
        transaction.setTransactionAmount(transactionAmount);

        Transaction transaction1=new Transaction(username,type,transactionAmount,accNo);

        assertEquals(username, transaction.getUsername());
        assertEquals(type, transaction.getType());
        assertEquals(transactionAmount, transaction.getTransactionAmount());
        assertEquals(accNo, transaction.getAccNo());
        assertNotNull(transaction1.getCreatedAt());
    }

    @Test
    public void testGetCurrentFormattedDate() {
        Transaction transaction = new Transaction("testuser", "recharge", 50, "12345678");
        String formattedDate = transaction.getCurrentFormattedDate();

        assertNotNull(formattedDate);
        assertTrue(formattedDate.matches("\\d{2}:\\d{2} \\d{2}-\\d{2}-\\d{4}"));
    }

    @Test
    public void testTransactionTypeValues() {
        Transaction transaction = new Transaction("testuser", "recharge", 50, "12345678");

        assertEquals("recharge", transaction.getType());

        transaction.setType("transfer");
        assertEquals("transfer", transaction.getType());


    }

    @Test
    public void testTransactionCreatedAtFormat() {
        Transaction transaction = new Transaction("testuser", "recharge", 50, "12345678");
        String formattedDate = transaction.getCurrentFormattedDate();

        assertTrue(formattedDate.matches("\\d{2}:\\d{2} \\d{2}-\\d{2}-\\d{4}"));

        transaction.setCreatedAt("12:34 01-01-2023");
        assertEquals("12:34 01-01-2023", transaction.getCreatedAt());

    }


    @Test
    public void testEqualsAndHashCode() {
        Transaction transaction1 = new Transaction("user1", "recharge", 50, "12345678");
        Transaction transaction2 = new Transaction("user1", "recharge", 50, "12345678");
        Transaction transaction3 = new Transaction("user2", "recharge", 50, "12345678");

        // Test equals method
        assertEquals(transaction1, transaction2);
        assertNotEquals(transaction1, transaction3);

        // Test hashCode method
        assertEquals(transaction1.hashCode(), transaction2.hashCode());
        assertNotEquals(transaction1.hashCode(), transaction3.hashCode());
    }
}
