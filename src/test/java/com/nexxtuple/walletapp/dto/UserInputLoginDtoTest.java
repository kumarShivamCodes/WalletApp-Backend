package com.nexxtuple.walletapp.dto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class UserInputLoginDtoTest {


    @Test
    public void testConstructorAndGettersSetters() {
        String username = "testuser";
        String password = "test123";

        UserInputLoginDto loginDto = new UserInputLoginDto();
        loginDto.setUsername(username);
        loginDto.setPassword(password);

        assertEquals(username, loginDto.getUsername());
        assertEquals(password, loginDto.getPassword());
    }

    @Test
    public void testSetterAndGetters() {
        UserInputLoginDto loginDto = new UserInputLoginDto();

        loginDto.setUsername("newuser");
        loginDto.setPassword("newpassword");

        assertEquals("newuser", loginDto.getUsername());
        assertEquals("newpassword", loginDto.getPassword());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserInputLoginDto dto1 = new UserInputLoginDto("user1", "password123");
        UserInputLoginDto dto2 = new UserInputLoginDto("user1", "password123");
        UserInputLoginDto dto3 = new UserInputLoginDto("user2", "password456");

        // Test equals()
        assertTrue(dto1.equals(dto2));
        assertFalse(dto1.equals(dto3));

        // Test hashCode()
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertFalse(dto1.hashCode() == dto3.hashCode());
    }

    @Test
    public void testCanEqual() {
        UserInputLoginDto dto1 = new UserInputLoginDto("user1", "password123");
        UserInputLoginDto dto2 = new UserInputLoginDto("user1", "password123");

        // Test canEqual() with a different class
        assertFalse(dto1.canEqual(new Object()));

        // Test canEqual() with the same class
        assertTrue(dto1.canEqual(dto2));
    }
}
