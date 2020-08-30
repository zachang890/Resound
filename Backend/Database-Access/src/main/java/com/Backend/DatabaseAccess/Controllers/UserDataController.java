package com.Backend.DatabaseAccess.Controllers;

import com.Backend.DatabaseAccess.Models.UserInfo;
import com.Backend.DatabaseAccess.Repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserDataController {

    //TODO: Swagger annotations
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user-info")
    public String postUserInfo(@RequestBody UserInfo userInfo) {
        return userRepository.postUserInfo(userInfo);
    }
}
