package com.thesilentnights.maalinkserver.config;

import com.thesilentnights.maalinkserver.utils.FileChecker;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.Properties;

@Repository
public class MainConfig {
    long tokenExpiration;
    String adbPath, host;

    public MainConfig() {
        File file = FileChecker.checkFile("./config.properties");
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("token.expiration=\nadb.path=\nservice.host=");
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String property = properties.getProperty("token.expiration", Long.toString(7 * 24 * 60 * 60 * 1000));
        adbPath = properties.getProperty("adb.path", "null");
        host = properties.getProperty("service.host","null");
        tokenExpiration = Long.parseLong(property);
    }

    public long getTokenExpiration() {
        return tokenExpiration;
    }

    public String getAdbPath() {
        return adbPath;
    }

    public String getHost() {
        return host;
    }
}
