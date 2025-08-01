package com.thesilentnights.maalinkserver.config;

import com.thesilentnights.maalinkserver.utils.FileChecker;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Repository
public class MainConfig {
    long tokenExpiration;

    public MainConfig() {
        File file = FileChecker.checkFile("./config.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String property = properties.getProperty("token.expiration", Long.toString(7 * 24 * 60 * 60 * 1000));
        tokenExpiration = Long.parseLong(property);
    }

    public long getTokenExpiration() {
        return tokenExpiration;
    }
}
