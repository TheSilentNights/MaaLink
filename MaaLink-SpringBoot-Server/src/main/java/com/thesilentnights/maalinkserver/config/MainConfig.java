package com.thesilentnights.maalinkserver.config;

import com.thesilentnights.maalinkserver.utils.FileChecker;
import com.thesilentnights.maalinkserver.utils.Scripts;
import org.springframework.stereotype.Repository;

import javax.script.ScriptException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Repository
public class MainConfig {
    long tokenExpiration;
    String adbPath, host;

    public MainConfig() throws ScriptException {
        File file = FileChecker.checkFile("./config.properties", "token.expiration=\nadb.path=\nservice.host=\n");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String property = properties.getProperty("token.expiration", Long.toString(7 * 24 * 60 * 60 * 1000));
        adbPath = properties.getProperty("adb.path", "null");
        host = properties.getProperty("service.host", "null");
        //执行计算代码
        tokenExpiration = (long) Scripts.eval(property);
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
