package com.nexxtuple.walletapp.controller;


import com.nexxtuple.walletapp.dao.UserDao;
import com.nexxtuple.walletapp.dto.*;
import com.nexxtuple.walletapp.entity.Transaction;
import com.nexxtuple.walletapp.entity.User;
import com.nexxtuple.walletapp.exception.*;
import com.nexxtuple.walletapp.service.TransactionService;
import com.nexxtuple.walletapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserDao userDao;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers_Success() {
        List<User> mockUsers = Arrays.asList(
                new User("user1", "user1@example.com", "password"),
                new User("user2", "user2@example.com", "password"),
                new User("user3", "user3@example.com", "password")

        );

        List<UserOutputDto> mockUserDtos = Arrays.asList(
                new UserOutputDto("user1", 100, 12345678),
                new UserOutputDto("user2", 150, 23456789),
                new UserOutputDto("user3", 100, 232256789)
        );

        when(userService.getAllUsers()).thenReturn(mockUsers);
        when(modelMapper.map(any(User.class), eq(UserOutputDto.class)))
                .thenReturn(mockUserDtos.get(0), mockUserDtos.get(1),mockUserDtos.get(2));

        ResponseEntity<List<UserOutputDto>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUserDtos, response.getBody());
    }

    @Test
    public void testGetAllUsers_EmptyList() {
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        ResponseEntity<List<UserOutputDto>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    public void testGetUser()
    {
        UserInputLoginDto inputLoginDto=new UserInputLoginDto();
        inputLoginDto.setUsername("shivam");
        inputLoginDto.setPassword("qwwerty123");

        UserOutputDto userOutputDto=new UserOutputDto();
        userOutputDto.setBalance(100);
        userOutputDto.setUsername("shivam");
        userOutputDto.setAccNo(123456);
        //when
        when(userService.getUser(inputLoginDto.getUsername(),inputLoginDto.getPassword())).thenReturn(userOutputDto);

        ResponseEntity<UserOutputDto> actualOutput=userController.getUser(inputLoginDto);

        assertEquals(HttpStatus.OK,actualOutput.getStatusCode());

    }

    @Test
    public void testGetUser_PasswordMismatch() {
        String username = "user1";
        String password = "password";

        when(userService.getUser(username, password))
                .thenThrow(new PasswordMismatchException("Password mismatch"));

        UserInputLoginDto userInputLoginDto = new UserInputLoginDto(username, password);
        assertThrows(PasswordMismatchException.class,()->userController.getUser(userInputLoginDto));
    }

    @Test
    public void testGetUser_UserNotFound() {
        String username = "user1";
        String password = "password";

        when(userService.getUser(username, password))
                .thenThrow(new UserNotFoundException("User not found: " + username));

        UserInputLoginDto userInputLoginDto = new UserInputLoginDto(username, password);
        assertThrows(UserNotFoundException.class,()->userController.getUser(userInputLoginDto));
    }

    @Test
    public void testAddUser_Success()
    {
        UserInputRegisterDto inputRegisterDto=new UserInputRegisterDto();
        inputRegisterDto.setEmail("sk@gmail.com");
        inputRegisterDto.setUsername("shivamk");
        inputRegisterDto.setPassword("12345sa");

        UserOutputDto expectedOutputDto=new UserOutputDto();
        expectedOutputDto.setAccNo(2345432);
        expectedOutputDto.setUsername("shivamk");
        expectedOutputDto.setBalance(0);

        when(userService.addUser(inputRegisterDto)).thenReturn(expectedOutputDto);

        ResponseEntity<UserOutputDto> actualOutputDto=userController.addUser(inputRegisterDto);

        assertEquals(HttpStatus.CREATED,actualOutputDto.getStatusCode());

    }

    @Test
    public void testAddUser_UserAlreadyExists() {
        UserInputRegisterDto userInputRegisterDto = new UserInputRegisterDto("existinguser", "existing@example.com", "password");

        when(userService.addUser(userInputRegisterDto))
                .thenThrow(new UserAlreadyExistsException("User already exists: " + userInputRegisterDto.getUsername()));

       assertThrows(UserAlreadyExistsException.class,()-> userController.addUser(userInputRegisterDto));
    }

    @Test
    public void testAddUser_EmailAlreadyRegistered() {
        UserInputRegisterDto userInputRegisterDto = new UserInputRegisterDto("newuser", "existing@example.com", "password");

        when(userService.addUser(userInputRegisterDto))
                .thenThrow(new UserAlreadyExistsException("Email is already registered: " + userInputRegisterDto.getEmail()));

      assertThrows( UserAlreadyExistsException.class ,()->userController.addUser(userInputRegisterDto));
    }

    @Test
    public void testRechargeBalance_Success() {
        UserInputBalanceDto userInput = new UserInputBalanceDto("username", 50, "12345678");
        UserOutputDto expectedUserOutput = new UserOutputDto();

        when(userService.updateUserBalance(anyString(), anyInt(), anyString()))
                .thenReturn(expectedUserOutput);

        ResponseEntity<UserOutputDto> response = userController.rechargeBalance(userInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUserOutput, response.getBody());
    }


    @Test
    public void testTransferAmount_Success() {
        UserInputBalanceDto userInput = new UserInputBalanceDto("sender", 50, "12345678");
        UserOutputDto expectedUserOutput = new UserOutputDto();

        when(userService.transferAmount(anyString(), anyInt(), anyString()))
                .thenReturn(expectedUserOutput);

        ResponseEntity<UserOutputDto> response = userController.transferAmount(userInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUserOutput, response.getBody());
    }

    @Test
    public void testGetAllTransactionsByUsername_Success() {
        String username = "user1";

        List<Transaction> mockTransactions = Arrays.asList(
                new Transaction("shivam","Recharge",100,"1223456"),
                new Transaction("shivam","Transfer",50,"1223457")
        );

        List<TransactionOutputDto> mockTransactionDtos = mockTransactions.stream()
                .map(transaction -> new TransactionOutputDto(/* mapped dto details */))
                .collect(Collectors.toList());

        when(transactionService.getAllTransactionsByUsername(username)).thenReturn(mockTransactions);
        when(modelMapper.map(any(Transaction.class), eq(TransactionOutputDto.class)))
                .thenReturn(mockTransactionDtos.get(0), mockTransactionDtos.get(1));

        ResponseEntity<List<TransactionOutputDto>> response = userController.getAllTransactionsByUsername(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockTransactionDtos, response.getBody());
    }

}