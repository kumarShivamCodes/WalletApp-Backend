package com.nexxtuple.walletapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TransactionOutputDtoTest {

    @Test
    public void testConstructorAndGettersSetters() {
        String username = "testuser";
        String type = "recharge";
        Integer transactionAmount = 50;
        String accNo = "12345678";
        String createdAt = "12:34 05-08-2023";

        TransactionOutputDto outputDto = new TransactionOutputDto();
        outputDto.setUsername(username);
        outputDto.setType(type);
        outputDto.setTransactionAmount(transactionAmount);
        outputDto.setAccNo(accNo);
        outputDto.setCreatedAt(createdAt);

        assertEquals(username, outputDto.getUsername());
        assertEquals(type, outputDto.getType());
        assertEquals(transactionAmount, outputDto.getTransactionAmount());
        assertEquals(accNo, outputDto.getAccNo());
        assertEquals(createdAt, outputDto.getCreatedAt());
    }

    @Test
    public void testSetterAndGetters() {
        TransactionOutputDto outputDto = new TransactionOutputDto();

        outputDto.setUsername("newuser");
        outputDto.setType("transfer");
        outputDto.setTransactionAmount(100);
        outputDto.setAccNo("87654321");
        outputDto.setCreatedAt("23:45 10-08-2023");

        assertEquals("newuser", outputDto.getUsername());
        assertEquals("transfer", outputDto.getType());
        assertEquals(100, outputDto.getTransactionAmount());
        assertEquals("87654321", outputDto.getAccNo());
        assertEquals("23:45 10-08-2023", outputDto.getCreatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        TransactionOutputDto dto1 = new TransactionOutputDto("user1", "recharge", 50, "12345678", "2023-08-17");
        TransactionOutputDto dto2 = new TransactionOutputDto("user1", "recharge", 50, "12345678", "2023-08-17");
        TransactionOutputDto dto3 = new TransactionOutputDto("user2", "transfer", 100, "87654321", "2023-08-18");

        // Test equals()
        assertTrue(dto1.equals(dto2));
        assertFalse(dto1.equals(dto3));

        // Test hashCode()
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertFalse(dto1.hashCode() == dto3.hashCode());
    }

    @Test
    public void testCanEqual() {
        TransactionOutputDto dto1 = new TransactionOutputDto("user1", "recharge", 50, "12345678", "2023-08-17");
        TransactionOutputDto dto2 = new TransactionOutputDto("user1", "recharge", 50, "12345678", "2023-08-17");

        // Test canEqual() with a different class
        assertFalse(dto1.canEqual(new Object()));

        // Test canEqual() with the same class
        assertTrue(dto1.canEqual(dto2));
    }

    @Test
    public void testToString() {
        TransactionOutputDto dto = new TransactionOutputDto("user1", "recharge", 50, "12345678", "2023-08-17");
        String expectedString = "TransactionOutputDto(username=user1, type=recharge, transactionAmount=50, accNo=12345678, createdAt=2023-08-17)";

        assertEquals(expectedString, dto.toString());
    }

}
