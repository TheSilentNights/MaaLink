package com.thesilentnights.maalinkserver;

import com.thesilentnights.maalinkserver.repo.MaaInstance;
import com.thesilentnights.maalinkserver.repo.MaaStatus;
import com.thesilentnights.maalinkserver.jna.CallBackMethod;
import com.thesilentnights.maalinkserver.jna.MaaCore;
import com.thesilentnights.maalinkserver.service.MaaLinkService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MaaLinkSpringBootServerApplication {
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(MaaLinkSpringBootServerApplication.class, args);

        if (context.getBean(MaaCore.class).AsstLoadResource("./")) {
            MaaStatus.setStatus(true);
        }

        if (MaaStatus.isStatus()) {
            context.getBean(MaaInstance.class).setHandle(context.getBean(MaaCore.class).AsstCreateEx(context.getBean(CallBackMethod.class), "MaaLink_main"));
        }

        System.out.println(context.getBean(MaaLinkService.class).connect());
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
