package com.Backend.DatabaseAccess.Repositories;

import com.Backend.DatabaseAccess.Models.UserInfo;
import com.Backend.DatabaseAccess.Services.DatabaseService;
import com.Backend.DatabaseAccess.Utils.Utils;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.SetOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepository {

    @Autowired
    DatabaseService databaseService;

    public String postUserInfo(UserInfo userInfo) {
        DocumentReference documentReference = databaseService.getDb()
                .collection(Utils.USERS)
                .document();

        userInfo.setUserId(documentReference.getId());
        documentReference.set(userInfo, SetOptions.merge());

        return userInfo.getUserId();
    }
}
