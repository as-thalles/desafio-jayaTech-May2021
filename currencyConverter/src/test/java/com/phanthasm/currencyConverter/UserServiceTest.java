package com.phanthasm.currencyConverter;

import com.phanthasm.currencyConverter.dto.UserDTO;
import com.phanthasm.currencyConverter.entities.User;
import com.phanthasm.currencyConverter.repositories.UserRepository;
import com.phanthasm.currencyConverter.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository repository;

    private UserService subject;

    @Before
    public void setup() {
        subject = new UserService(repository);
    }

    @Test
    public void findAllIsEmpty() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList());

        List<UserDTO> users = subject.findAll();
        Assert.assertTrue(users.isEmpty());
    }

    @Test
    public void findAllHasElements() {
        User u1 = new User(1L, "Christine Daae");
        User u2 = new User(2L, "Lisbeth Salander");
        User u3 = new User(3L, "Gregory House");
        User u4 = new User(4L, "Will Graham");
        User u5 = new User(5L, "Clarice Starling");
        List<User> mockUsers = Arrays.asList(u1, u2, u3, u4, u5);
        Mockito.when(repository.findAll()).thenReturn(mockUsers);

        List<UserDTO> users = subject.findAll();
        Assert.assertTrue(!users.isEmpty());

        for(int i=0; i < mockUsers.size(); ++i) {
            Assert.assertEquals(users.get(i).getId(), mockUsers.get(i).getId());
        }
    }
}
