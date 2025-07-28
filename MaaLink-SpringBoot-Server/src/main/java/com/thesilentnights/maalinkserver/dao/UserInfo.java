package com.thesilentnights.maalinkserver.dao;

import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

@Repository
public class UserInfo {
    String storedUserName;
    String storedPassword;

    public UserInfo() {
        Properties properties = new Properties();
        File file = new File("./userinfo.properties");
        if (!file.exists()){
            try {
                file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write("username=\npassword=");
                bufferedWriter.close();
                System.out.println("file created");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            properties.load(Files.newInputStream(file.toPath()));
            storedUserName = properties.getProperty("username","maaLinkAdmin");
            storedPassword = properties.getProperty("password","admin123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
