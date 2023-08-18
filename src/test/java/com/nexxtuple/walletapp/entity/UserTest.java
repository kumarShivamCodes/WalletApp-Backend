package com.nexxtuple.walletapp.entity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testGenerateUniqueAccNo() {
        User user = new User();
        int accNo = user.generateUniqueAccNo();

        assertTrue(accNo >= 10000 && accNo <= 99999);
    }

    @Test
    public void testConstructorAndGetterSetter() {
        String username = "testuser";
        String email = "testuser@example.com";
        String password = "test123";
        User user = new User(username, email, password);

        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertNotNull(user.getAccNo());
        assertEquals(0, user.getBalance());

        user.setBalance(100);
        assertEquals(100, user.getBalance());
    }

    @Test
    public void testConstructorWithGeneratedAccNo() {
        String username = "testuser";
        String email = "testuser@example.com";
        String password = "test123";
        User user = new User(username, email, password);

        assertNotNull(user.getAccNo());
        assertTrue(user.getAccNo() >= 10000 && user.getAccNo() <= 99999);
    }

    @Test
    public void testAccNoUniqueness() {
        User user1 = new User("user1", "user1@example.com", "password");
        User user2 = new User("user2", "user2@example.com", "password");

        System.out.println("1:"+user1.getAccNo()+"2:"+user2.getAccNo());
        Assert.assertTrue(user1.getAccNo()!= user2.getAccNo());
    }

    @Test
    public void testBalanceDefaultValue() {
        User user = new User();
        assertEquals(0, user.getBalance());
    }

    @Test
    public void testSetterAndGettersForAccNoAndBalance() {
        User user = new User();
        user.setAccNo(12345);
        user.setBalance(200);

        assertEquals(12345, user.getAccNo());
        assertEquals(200, user.getBalance());
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User("user1", "user1@example.com", "password");
        User user2 = new User("user1", "user1@example.com", "password");
        User user3 = new User("user2", "user2@example.com", "password");

        // Test equals
        assertTrue(user1.equals(user2));
        assertFalse(user1.equals(user3));

        // Test hashCode
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }



}
