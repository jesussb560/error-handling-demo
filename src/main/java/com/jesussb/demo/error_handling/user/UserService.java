package com.jesussb.demo.error_handling.user;

import com.jesussb.demo.error_handling.user.dto.in.Create;

import java.util.Map;

public interface UserService {

    Map<String, Object> findById(Long id);
    Map<String, Object> create(Create create);


}
