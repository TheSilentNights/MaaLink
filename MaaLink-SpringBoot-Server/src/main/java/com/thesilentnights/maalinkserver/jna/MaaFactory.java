package com.thesilentnights.maalinkserver.jna;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MaaFactory {
    @Value("./MaaCore.dll")
    private String pathToMaaCore;
    @Autowired
    private MaaCore maaCore;

    @Bean
    public MaaCore maaCore(){
        return Native.load(pathToMaaCore, MaaCore.class);
    }

    @Bean("MaaAsstInstance")
    public Pointer getAsstInstance(){
        return maaCore.AsstCreateEx(new MaaCore.AsstApiCallback() {
            @Override
            public void callback(int msg, String detail_json, String custom_arg) {
                maaCore.AsstLog("info","maalink Connected");
            }
        },"maalink_maaCore_instance");
    }
}
