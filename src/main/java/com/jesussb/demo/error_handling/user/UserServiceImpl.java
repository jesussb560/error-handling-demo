package com.jesussb.demo.error_handling.user;

import com.jesussb.demo.error_handling.common.exception.ErrorStructure;
import com.jesussb.demo.error_handling.common.exception.ServiceException;
import com.jesussb.demo.error_handling.user.dto.in.Create;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Test implementation of UserService for error handling scenarios.
 *
 * <p>
 *      This service simulates user operations with predefined error conditions
 *      to test exception handling and error response formatting.
 * </p>
 */
@Service
public class UserServiceImpl implements UserService {


    /**
     * Simulates user search by ID with controlled error scenarios.
     *
     * @param id User identifier with the following behavior:
     *           - 1-10: Returns success message
     *           - 11-20: Throws USER_NOT_FOUND exception
     *           - 21+: Throws INTERNAL_ERROR exception
     * @return Map containing success message if user is "found"
     * @throws ServiceException with USER_NOT_FOUND for IDs 11-20
     * @throws ServiceException with GEN_INTERNAL_ERROR for IDs 21+
     */
    @Override
    public Map<String, Object> findById(Long id) {

        if (id > 10 && id < 21) {
            throw new ServiceException(ErrorStructure.USER_NOT_FOUND, id);
        }

        if (id > 20) {
            throw new ServiceException(ErrorStructure.GEN_INTERNAL_ERROR);
        }

        return Map.of(
                "message",
                "User with id " + id + " has been found."
        );
    }

    /**
     * Simulates user creation that is always successful if the bindingResult validation in the controller is successful as well.
     *
     * @param create User creation data
     * @return Map containing success message
     */
    @Override
    public Map<String, Object> create(Create create) {
        return Map.of(
                "message",
                "User created successfully " + create.toString()
        );
    }

}
