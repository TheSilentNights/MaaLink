package com.thesilentnights.maalinkserver.service;

import com.thesilentnights.maalinkserver.dao.UserInfo;
import com.thesilentnights.maalinkserver.utils.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Login {
    @Autowired
    UserInfo userInfo;

    public String login(String username, String password){
        if (userInfo.getStoredUserName().equals(username) && userInfo.getStoredPassword().equals(password)){
            String token = Auth.createToken(username, 7 * 24 * 60 * 60);
            userInfo.setCurrentUserToken(token);
            return token;
        }
        return null;
    }

    public boolean verify(String token){
        return Auth.checkToken(token,userInfo.getCurrentUserToken(),userInfo.getStoredUserName());
    }
}
