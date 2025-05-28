package com.jesussb.demo.error_handling.user;

import com.jesussb.demo.error_handling.common.exception.ErrorStructure;
import com.jesussb.demo.error_handling.common.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private final UserService userService = new UserServiceImpl();

    @Test
    void findById(){
        assertThrows(ServiceException.class, () -> userService.findById(11L));
    }

}