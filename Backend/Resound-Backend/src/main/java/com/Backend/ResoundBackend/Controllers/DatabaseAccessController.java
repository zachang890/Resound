package com.Backend.ResoundBackend.Controllers;

import com.Backend.ResoundBackend.Feign.DatabaseAccessInterface;
import com.Backend.ResoundBackend.Models.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class DatabaseAccessController {

    @Autowired
    DatabaseAccessInterface databaseAccessInterface;

    @PostMapping("/user-info")
    public String postUserInfo(@RequestBody UserInfo userInfo) {
        return databaseAccessInterface.postUserInfo(userInfo);
    }
}
