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

    @Bean
    public MaaCore maaCore(){
        return Native.load(pathToMaaCore, MaaCore.class);
    }
}
