package com.nexxtuple.walletapp.service;

import com.nexxtuple.walletapp.dao.UserDao;
import com.nexxtuple.walletapp.dto.UserInputRegisterDto;
import com.nexxtuple.walletapp.dto.UserOutputDto;
import com.nexxtuple.walletapp.entity.User;
import com.nexxtuple.walletapp.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImplementationTest {

    @Mock
    private UserDao userDao;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private UserServiceImplementation userService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers()
    {
        List<User> mockUsers=new ArrayList<>();

        for(int i =0;i<5;i++)
        {
            mockUsers.add(new User());
        }
        //when
        when(userDao.getAllUsers()).thenReturn(mockUsers);

        //action
        List<User> users=userDao.getAllUsers();

        assertEquals(5,users.size());
    }

    @Test
    public void testGetUser_ValidUserAndPassword()
    {
        String username="Shivam";
        String password="qwerty123";
        User user=new User();
        user.setPassword(password);

        //when
        when(userDao.getByUserName(username)).thenReturn(user);
        when(modelMapper.map(user, UserOutputDto.class)).thenReturn(new UserOutputDto());

        //action
        UserOutputDto userOutputDto=userService.getUser(username,password);

        //assert
        assertNotNull(userOutputDto);

    }

    @Test
    public void testGetUser_UserNotFound() {
        String username = "nonExistentUser";
        String password = "password";
        when(userDao.getByUserName(username)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userService.getUser(username, password));
    }

    @Test
    public void testGetUser_PasswordMismatch()
    {
        String username="shivam";
        String password="wrong password";
        User user=new User();
        user.setUsername(username);
        user.setPassword("1qqww22");

        when(userDao.getByUserName(username)).thenReturn(user);

       assertThrows(PasswordMismatchException.class,()->userService.getUser(username,password));


    }

    @Test
    public void testAddUser_ValidUser()
    {
        UserInputRegisterDto userInputRegisterDto=new UserInputRegisterDto();
        userInputRegisterDto.setEmail("sk@gmail.com");
        userInputRegisterDto.setPassword("qwert123");
        userInputRegisterDto.setUsername("shivam");

        User newUser= new User(userInputRegisterDto.getUsername(),userInputRegisterDto.getEmail(),userInputRegisterDto.getPassword());
        User savedUser = new User(newUser.getUsername(), newUser.getEmail(), newUser.getPassword());

        UserOutputDto expectedOutputDto = new UserOutputDto();
        expectedOutputDto.setUsername(savedUser.getUsername());
        expectedOutputDto.setBalance(savedUser.getBalance());
        expectedOutputDto.setAccNo(savedUser.getAccNo());

        //when
        when(modelMapper.map(userInputRegisterDto,User.class)).thenReturn(newUser);
        when(userDao.getByUserName(newUser.getUsername())).thenReturn(null);
        when(userDao.getUserByEmail(newUser.getEmail())).thenReturn(null);
        when(userDao.addUser(newUser)).thenReturn(savedUser);
        when(modelMapper.map(savedUser,UserOutputDto.class)).thenReturn(expectedOutputDto);

        //action
        UserOutputDto actualOutputDto= userService.addUser(userInputRegisterDto);

        //assert

        assertEquals(expectedOutputDto,actualOutputDto);


    }

    @Test
    public void testAddUser_ExistingUsername()
    {
        UserInputRegisterDto inputDto=new UserInputRegisterDto();
        inputDto.setUsername("existinguser");
        inputDto.setPassword("123eerr");
        inputDto.setEmail("sk@gmail.com");

        User newUser= new User(inputDto.getUsername(),inputDto.getEmail(),inputDto.getPassword());
        User existingUser=new User(inputDto.getUsername(),inputDto.getEmail(),inputDto.getPassword());

        //when
        when(modelMapper.map(inputDto,User.class)).thenReturn(newUser);
        when(userDao.getByUserName(newUser.getUsername())).thenReturn(existingUser);

        //assert
        assertThrows(UserAlreadyExistsException.class,()->userService.addUser(inputDto));

    }

    @Test
    public void testAddUser_EmailAlreadyExists() {
        UserInputRegisterDto inputDto = new UserInputRegisterDto();
        inputDto.setUsername("newuser");
        inputDto.setEmail("existing@example.com");
        inputDto.setPassword("password");

        User newUser= new User(inputDto.getUsername(),inputDto.getEmail(),inputDto.getPassword());
        User existingUser=new User(inputDto.getUsername(),inputDto.getEmail(),inputDto.getPassword());

        when(modelMapper.map(inputDto,User.class)).thenReturn(newUser);
        when(userDao.getByUserName(existingUser.getUsername())).thenReturn(null);
        when(userDao.getUserByEmail(existingUser.getEmail())).thenReturn(existingUser);

        assertThrows(UserAlreadyExistsException.class,()->userService.addUser(inputDto));
    }

    @Test
    public void testUpdateUserBalance_ValidUser()
    {
        String username="shivam";
        int initialBalance=100;
        int amountToAdd=50;
        int expectedBalance=initialBalance+amountToAdd;

        User user=new User(username,"sk@gmail.com","1q2w3e4r");
        user.setId("12234aa");
        user.setBalance(initialBalance);

        User userWithUpdatedBalance=new User(username,"sk@gmail.com","1q2w3e4r");
        userWithUpdatedBalance.setId("12234aa");
        userWithUpdatedBalance.setBalance(expectedBalance);

        UserOutputDto expectedOutputDto=new UserOutputDto();
        expectedOutputDto.setBalance(expectedBalance);
        expectedOutputDto.setUsername(username);
        expectedOutputDto.setAccNo(user.getAccNo());



        when(userDao.getByUserName(username)).thenReturn(user);
        when(userDao.updateUserBalance("12234aa",amountToAdd)).thenReturn(userWithUpdatedBalance);
        when(modelMapper.map(userWithUpdatedBalance, UserOutputDto.class)).thenReturn(expectedOutputDto);

        UserOutputDto actualOutputDto= userService.updateUserBalance(username,amountToAdd,"1234455");

        assertEquals(expectedOutputDto, actualOutputDto);
        verify(transactionService).createTransaction(any());
    }

    @Test
    public void testUpdateUserBalance_UserNotFound() {
        String username = "nonexistentuser";
        int amountToAdd = 50;

        when(userDao.getByUserName(username)).thenReturn(null);

        assertThrows(UserNotFoundException.class,()->userService.updateUserBalance(username, amountToAdd, "12345678"));
    }

    @Test
    public void testTransferAmount_Success() {
        String senderUsername = "sender";
        String receiverUsername = "receiver";
        Integer receiverAccNo=123344;
        int initialSenderBalance = 100;
        int initialReceiverBalance=20;
        int amountToTransfer = 50;
        int expectedSenderBalance = initialSenderBalance - amountToTransfer;
        int expectedReceiverBalance=initialReceiverBalance+amountToTransfer;

        User sender = new User(senderUsername, "sender@example.com", "password");
        sender.setId("sender-id");
        sender.setBalance(initialSenderBalance);


        User receiver = new User(receiverUsername, "receiver@example.com", "password");
        receiver.setId("receiver-id");
        receiver.setBalance(initialReceiverBalance);
        receiver.setAccNo(receiverAccNo);

        User senderWithUpdatedBalance = new User(senderUsername, "sender@example.com", "password");
        senderWithUpdatedBalance.setId("sender-id");
        senderWithUpdatedBalance.setBalance(expectedSenderBalance);

        User receiverWithUpdatedBalance = new User(receiverUsername, "receiver@example.com", "password");
        receiverWithUpdatedBalance.setId("receiver-id");
        receiverWithUpdatedBalance.setBalance(expectedReceiverBalance);



        when(userDao.getByUserName(senderUsername)).thenReturn(sender);
        when(userDao.getUserByAccNo(receiverAccNo)).thenReturn(receiver);
        when(userDao.transferAmount(sender.getId(), amountToTransfer)).thenReturn(senderWithUpdatedBalance);
        when(userDao.updateUserBalance(receiver.getId(), amountToTransfer)).thenReturn(receiverWithUpdatedBalance);


        when(modelMapper.map(senderWithUpdatedBalance, UserOutputDto.class))
                .thenReturn(new UserOutputDto(senderUsername, expectedSenderBalance, sender.getAccNo()));

        when(modelMapper.map(receiverWithUpdatedBalance, UserOutputDto.class))
                .thenReturn(new UserOutputDto(receiverUsername, expectedReceiverBalance, receiver.getAccNo()));


        //action
        UserOutputDto resultDto = userService.transferAmount(senderUsername, amountToTransfer, receiverAccNo.toString());

        //assert
        assertEquals(expectedSenderBalance, resultDto.getBalance());
        assertEquals(senderUsername, resultDto.getUsername());
        assertEquals(sender.getAccNo(), resultDto.getAccNo());

        verify(transactionService).createTransaction(any());
    }

    @Test()
    public void testTransferAmount_SenderNotFound() {
        String senderUsername = "sender";
        int amountToTransfer = 50;

        when(userDao.getByUserName(senderUsername)).thenReturn(null);

       assertThrows( UserNotFoundException.class,()-> userService.transferAmount(senderUsername, amountToTransfer, "12345678"));
    }

    @Test
    public void testTransferAmount_InsufficientBalance() {
        String senderUsername = "sender";
        int initialSenderBalance = 100;
        int amountToTransfer = 150; // More than balance

        User sender = new User(senderUsername, "sender@example.com", "password");
        sender.setId("sender-id");
        sender.setBalance(initialSenderBalance);

        when(userDao.getByUserName(senderUsername)).thenReturn(sender);

       assertThrows(InvalidAmountException.class,()-> userService.transferAmount(senderUsername, amountToTransfer, "12345678")) ;
    }

    @Test
    public void testTransferAmount_ReceiverNotFound() {
        String senderUsername = "sender";
        int initialSenderBalance = 100;
        int amountToTransfer = 50;

        User sender = new User(senderUsername, "sender@example.com", "password");
        sender.setId("sender-id");
        sender.setBalance(initialSenderBalance);

        when(userDao.getByUserName(senderUsername)).thenReturn(sender);
        when(userDao.getUserByAccNo(12345678)).thenReturn(null);

        assertThrows(UserNotFoundException.class,()->userService.transferAmount(senderUsername, amountToTransfer, "12345678"));
    }

    @Test
    public void testTransferAmount_SameAccount() {
        String senderUsername = "sender";
        int initialSenderBalance = 100;
        int amountToTransfer = 50;

        User sender = new User(senderUsername, "sender@example.com", "password");
        sender.setId("sender-id");
        sender.setBalance(initialSenderBalance);
        sender.setAccNo(12345678);

        when(userDao.getByUserName(senderUsername)).thenReturn(sender);
        when(userDao.getUserByAccNo(12345678)).thenReturn(sender);

       assertThrows(InvalidAccountException.class,()->userService.transferAmount(senderUsername, amountToTransfer, Integer.toString(sender.getAccNo())));
    }

}
