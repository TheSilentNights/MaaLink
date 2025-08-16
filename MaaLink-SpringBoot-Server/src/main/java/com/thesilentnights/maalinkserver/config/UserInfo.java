package com.thesilentnights.maalinkserver.config;

import com.thesilentnights.maalinkserver.utils.FileChecker;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

@Repository
public class UserInfo {
    String storedUserName;
    String storedPassword;
    String CurrentUserToken;

    public UserInfo() {
        Properties properties = new Properties();
        File file = FileChecker.checkFile("./userinfo.properties", "username=admin\npassword=admin123456\n");
        try {
            properties.load(Files.newInputStream(file.toPath()));
            storedUserName = properties.getProperty("username", "admin");
            storedPassword = properties.getProperty("password", "admin123456");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCurrentUserToken() {
        return CurrentUserToken;
    }

    public void setCurrentUserToken(String currentUserToken) {
        CurrentUserToken = currentUserToken;
    }

    public String getStoredUserName() {
        return storedUserName;
    }

    public void setStoredUserName(String storedUserName) {
        this.storedUserName = storedUserName;
    }

    public String getStoredPassword() {
        return storedPassword;
    }

    public void setStoredPassword(String storedPassword) {
        this.storedPassword = storedPassword;
    }
}
