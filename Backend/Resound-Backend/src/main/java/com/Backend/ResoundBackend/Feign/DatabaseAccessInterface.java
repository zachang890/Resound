package com.Backend.ResoundBackend.Feign;

import com.Backend.ResoundBackend.Models.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "database-access", url = "http://localhost:8082/")
public interface DatabaseAccessInterface {

    @PostMapping("/user/user-info")
    public String postUserInfo(@RequestBody UserInfo userInfo);
}
