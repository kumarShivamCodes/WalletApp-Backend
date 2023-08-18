package com.nexxtuple.walletapp.dto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class UserInputRegisterDtoTest {

    @Test
    public void testConstructorAndGettersSetters() {
        String username = "testuser";
        String email = "testuser@example.com";
        String password = "test123";

        UserInputRegisterDto registerDto = new UserInputRegisterDto();
        registerDto.setUsername(username);
        registerDto.setEmail(email);
        registerDto.setPassword(password);

        assertEquals(username, registerDto.getUsername());
        assertEquals(email, registerDto.getEmail());
        assertEquals(password, registerDto.getPassword());
    }

    @Test
    public void testSetterAndGetters() {
        UserInputRegisterDto registerDto = new UserInputRegisterDto();

        registerDto.setUsername("newuser");
        registerDto.setEmail("newuser@example.com");
        registerDto.setPassword("newpassword");

        assertEquals("newuser", registerDto.getUsername());
        assertEquals("newuser@example.com", registerDto.getEmail());
        assertEquals("newpassword", registerDto.getPassword());
    }
    @Test
    public void testEqualsAndHashCode() {
        UserInputRegisterDto dto1 = new UserInputRegisterDto("user1", "user1@example.com", "password");
        UserInputRegisterDto dto2 = new UserInputRegisterDto("user1", "user1@example.com", "password");
        UserInputRegisterDto dto3 = new UserInputRegisterDto("user2", "user2@example.com", "password");

        // Test equals()
        assertTrue(dto1.equals(dto2));
        assertFalse(dto1.equals(dto3));

        // Test hashCode()
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertFalse(dto1.hashCode() == dto3.hashCode());
    }

    @Test
    public void testCanEqual() {
        UserInputRegisterDto dto1 = new UserInputRegisterDto("user1", "user1@example.com", "password");
        UserInputRegisterDto dto2 = new UserInputRegisterDto("user1", "user1@example.com", "password");

        // Test canEqual() with a different class
        assertFalse(dto1.canEqual(new Object()));

        // Test canEqual() with the same class
        assertTrue(dto1.canEqual(dto2));
    }
}
