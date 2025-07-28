package com.thesilentnights.maalinkserver.service;

import com.thesilentnights.maalinkserver.dao.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Login {
    @Autowired
    UserInfo userInfo;

    public boolean verify(String username,String password){
        if (userInfo.getStoredUserName().equals(username) && userInfo.getStoredPassword().equals("password")){
            return true;
        }
        return false;
    }
}
