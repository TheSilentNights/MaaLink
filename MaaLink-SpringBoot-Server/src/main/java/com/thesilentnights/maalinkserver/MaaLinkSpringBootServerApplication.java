package com.thesilentnights.maalinkserver;

import com.thesilentnights.maalinkserver.jna.MaaCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MaaLinkSpringBootServerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MaaLinkSpringBootServerApplication.class, args);
        System.out.println(context.getBean(MaaCore.class).AsstGetVersion());
//        System.out.println(Native.load("D:\\maa\\MaaCore.dll", MaaCore.class).AsstGetVersion());
    }

}
