package com.thesilentnights.maalinkserver.service;

import com.thesilentnights.maalinkserver.config.MainConfig;
import com.thesilentnights.maalinkserver.config.UserInfo;
import com.thesilentnights.maalinkserver.utils.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserInfo userInfo;
    @Autowired
    MainConfig mainConfig;

    public String login(String username, String password) {
        if (userInfo.getStoredUserName().equals(username) && userInfo.getStoredPassword().equals(password)) {
            String token = Auth.createToken(username, mainConfig.getTokenExpiration());
            userInfo.setCurrentUserToken(token);
            return token;
        }
        return null;
    }

    public boolean verify(String token) {
        return Auth.checkToken(token, userInfo.getStoredUserName() + userInfo.getStoredPassword());
    }
}
