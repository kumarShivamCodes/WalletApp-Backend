package com.nexxtuple.walletapp.service;

import com.nexxtuple.walletapp.dao.UserDao;
import com.nexxtuple.walletapp.dto.UserOutputDto;
import com.nexxtuple.walletapp.entity.User;
import com.nexxtuple.walletapp.exception.PasswordMismatchException;
import com.nexxtuple.walletapp.exception.UserNotFoundException;
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
}
