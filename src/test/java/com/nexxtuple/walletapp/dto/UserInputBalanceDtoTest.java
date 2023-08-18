package com.nexxtuple.walletapp.dto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserInputBalanceDtoTest {

    @Test
    public void testConstructorAndGettersSetters() {
        String username = "testuser";
        Integer amount = 50;
        String accNo = "12345678";

        UserInputBalanceDto inputDto = new UserInputBalanceDto(username, amount, accNo);

        assertEquals(username, inputDto.getUsername());
        assertEquals(amount, inputDto.getAmount());
        assertEquals(accNo, inputDto.getAccNo());

        inputDto.setUsername("newuser");
        inputDto.setAmount(100);
        inputDto.setAccNo("87654321");

        assertEquals("newuser", inputDto.getUsername());
        assertEquals(100, inputDto.getAmount());
        assertEquals("87654321", inputDto.getAccNo());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserInputBalanceDto dto1 = new UserInputBalanceDto("user1", 50, "12345678");
        UserInputBalanceDto dto2 = new UserInputBalanceDto("user1", 50, "12345678");
        UserInputBalanceDto dto3 = new UserInputBalanceDto("user2", 100, "87654321");

        // Test equals()
        assertTrue(dto1.equals(dto2));
        assertFalse(dto1.equals(dto3));

        // Test hashCode()
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertFalse(dto1.hashCode() == dto3.hashCode());
    }

    @Test
    public void testCanEqual() {
        UserInputBalanceDto dto1 = new UserInputBalanceDto("user1", 50, "12345678");
        UserInputBalanceDto dto2 = new UserInputBalanceDto("user1", 50, "12345678");

        // Test canEqual() with a different class
        assertFalse(dto1.canEqual(new Object()));

        // Test canEqual() with the same class
        assertTrue(dto1.canEqual(dto2));
    }

}
