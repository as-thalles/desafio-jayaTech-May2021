package com.phanthasm.currencyConverter;

import com.phanthasm.currencyConverter.dto.UserDTO;
import com.phanthasm.currencyConverter.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void findAll() {
        List<UserDTO> users = userService.findAll();
        Assert.assertTrue(!users.isEmpty());
    }
}
