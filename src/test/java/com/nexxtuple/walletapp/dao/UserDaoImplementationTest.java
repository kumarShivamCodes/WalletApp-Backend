package com.nexxtuple.walletapp.dao;

import com.nexxtuple.walletapp.entity.User;
import com.nexxtuple.walletapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDaoImplementationTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDaoImplementation userDao;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsersWhenNoUser()
    {
        List<User> mockUsers=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            mockUsers.add(new User());
        }
        //when
        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> users=userDao.getAllUsers();

        assertEquals(10, users.size());
    }

    @Test
    public void testGetUserByName()
    {
        //when
        when(userRepository.findByUsername("testUser")).thenReturn(new User());

        //action
        User user=userDao.getByUserName("testUser");

        //assert
        assertNotNull(user);

    }
    @Test
    public void testGetUserByEmail()
    {
        //when
        when(userRepository.findByEmail("testuser@gmail.com")).thenReturn(new User());

        //action
        User user=userDao.getUserByEmail("testuser@gmail.com");

        //assert
        assertNotNull(user);

    }

    @Test
    public void testAddUser()
    {
        User newUser=new User();
        //when
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        //action
        User addedUser=userDao.addUser(newUser);

        //assert
        assertEquals(newUser,addedUser);
    }

    @Test
    public void testUpdateUserBalance()
    {
        String userId="user1234";
        User user=new User();

        user.setId(userId);

        //when
        when(userRepository.findById(userId)).thenReturn( Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        //action
        User updateUser=userDao.updateUserBalance(userId,10);

        //assert
        assertEquals(10,updateUser.getBalance());
    }

    @Test
    public void testTransferAmount() {
        String userId = "testId";
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User transferredUser = userDao.transferAmount(userId, 50);

        assertEquals(-50, transferredUser.getBalance());
    }

}
