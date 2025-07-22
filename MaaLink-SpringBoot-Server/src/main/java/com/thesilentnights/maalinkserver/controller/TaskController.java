package com.thesilentnights.maalinkserver.controller;

import com.sun.jna.Pointer;
import com.thesilentnights.maalinkserver.jna.MaaCore;
import com.thesilentnights.maalinkserver.jna.MaaStatus;
import com.thesilentnights.maalinkserver.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    private MaaCore maaCore;
    @Autowired
    @Qualifier("MaaAsstInstance")
    private Pointer maaAsstInstance;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Response<Boolean> getStatus() {
        if (maaAsstInstance != null) {
            return new Response<>(MaaStatus.isStatus(), "status", 1);
        } else {
            return new Response<>(false,"failed",0);
        }
    }
}
