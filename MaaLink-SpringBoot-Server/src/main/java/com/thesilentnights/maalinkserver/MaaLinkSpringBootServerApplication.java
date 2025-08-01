package com.thesilentnights.maalinkserver;

import com.thesilentnights.maalinkserver.jna.MaaCore;
import com.thesilentnights.maalinkserver.repo.MaaStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MaaLinkSpringBootServerApplication {
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(MaaLinkSpringBootServerApplication.class, args);

        if (context.getBean(MaaCore.class).AsstLoadResource("..\\")) {
            MaaStatus.setStatus(true);
        }

//        System.out.println(context.getBean(MaaLinkService.class).connect());
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
