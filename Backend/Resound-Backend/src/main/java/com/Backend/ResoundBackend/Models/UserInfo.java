package com.Backend.ResoundBackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class UserInfo {
    private String userId;
    private String firstName;
    private String lastName;

    public UserInfo() {
        userId = "";
        firstName = "";
        lastName = "";
    }
}

