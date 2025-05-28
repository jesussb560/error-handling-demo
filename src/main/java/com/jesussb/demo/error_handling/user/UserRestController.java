package com.jesussb.demo.error_handling.user;

import com.jesussb.demo.error_handling.user.dto.in.Create;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller class that exposes some endpoints to test the exceptions.
 *
 * @author jesussb
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserRestController {

    private final UserService userService;

    /**
     * Endpoint that simulates a database request for searching a user by its id.
     * @param id The user identifier.
     * @return A Map with the success message.
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    /**
     * Endpoint that simulates a database request for creating a user.
     * @param create An object that contains de user information and is validated by the jakarta validation library.
     * @return A Map with the success message.
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Create create){
        return new ResponseEntity<>(userService.create(create), HttpStatus.CREATED);
    }

}
