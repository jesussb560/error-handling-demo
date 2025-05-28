package com.jesussb.demo.error_handling.user;

import com.jesussb.demo.error_handling.common.advice.GlobalControllerAdvice;
import com.jesussb.demo.error_handling.common.exception.ErrorStructure;
import com.jesussb.demo.error_handling.common.exception.ExceptionResolver;
import com.jesussb.demo.error_handling.common.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserRestController.class)
@ImportAutoConfiguration(GlobalControllerAdvice.class)
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private ExceptionResolver resolver;

    @Test
    void findById() throws Exception {

        long id = 1L;
        String uri = "/api/v1/users/find/" + id;

        mockMvc.perform(
                        MockMvcRequestBuilders.get(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

    }

    @Test
    void findByIdNotFound() throws Exception {

        long id = 11L;
        doThrow(new ServiceException(ErrorStructure.USER_NOT_FOUND)).when(userService).findById(id);

        String uri = "/api/v1/users/find/" + id;
        String response = "User with id " + id + " has been found.";

        mockMvc.perform(
                        MockMvcRequestBuilders.get(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ErrorStructure.USER_NOT_FOUND.getCode()));

    }

    @Test
    void create() throws Exception {

        String uri = "/api/v1/users/create";
        String body = """
                {
                    "name": "John",
                    "lastname": "Doe",
                    "email": "johndoe@gmail.com",
                    "age": 18
                }
                """;

        mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        )
                .andExpect(status().isCreated());

    }

    @Test
    void createArgumentNotValid() throws Exception {

        String uri = "/api/v1/users/create";
        String body = """
                {
                    "name": "John",
                    "lastname": "Doe",
                    "email": "johndoe @gmail.com",
                    "age": 12
                }
                """;

        mockMvc.perform(
                        MockMvcRequestBuilders.post(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.length()").value(2));
    }

}