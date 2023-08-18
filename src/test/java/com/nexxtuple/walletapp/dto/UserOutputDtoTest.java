package com.nexxtuple.walletapp.dto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserOutputDtoTest {

    @Test
    public void testConstructorAndGettersSetters() {
        String username = "testuser";
        Integer balance = 100;
        Integer accNo = 123456;

        UserOutputDto outputDto = new UserOutputDto();
        outputDto.setUsername(username);
        outputDto.setBalance(balance);
        outputDto.setAccNo(accNo);

        assertEquals(username, outputDto.getUsername());
        assertEquals(balance, outputDto.getBalance());
        assertEquals(accNo, outputDto.getAccNo());
    }

    @Test
    public void testSetterAndGetters() {
        UserOutputDto outputDto = new UserOutputDto();

        outputDto.setUsername("newuser");
        outputDto.setBalance(150);
        outputDto.setAccNo(789012);

        assertEquals("newuser", outputDto.getUsername());
        assertEquals(150, outputDto.getBalance());
        assertEquals(789012, outputDto.getAccNo());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserOutputDto dto1 = new UserOutputDto("user1", 100, 12345678);
        UserOutputDto dto2 = new UserOutputDto("user1", 100, 12345678);
        UserOutputDto dto3 = new UserOutputDto("user2", 150, 87654321);

        // Test equals()
        assertTrue(dto1.equals(dto2));
        assertFalse(dto1.equals(dto3));

        // Test hashCode()
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertFalse(dto1.hashCode() == dto3.hashCode());
    }

    @Test
    public void testCanEqual() {
        UserOutputDto dto1 = new UserOutputDto("user1", 100, 12345678);
        UserOutputDto dto2 = new UserOutputDto("user1", 100, 12345678);

        // Test canEqual() with a different class
        assertFalse(dto1.canEqual(new Object()));

        // Test canEqual() with the same class
        assertTrue(dto1.canEqual(dto2));
    }
}
