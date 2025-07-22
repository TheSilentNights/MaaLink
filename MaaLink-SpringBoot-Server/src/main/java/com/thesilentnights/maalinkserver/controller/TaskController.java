package com.thesilentnights.maalinkserver.controller;

import com.thesilentnights.maalinkserver.jna.MaaCore;
import com.thesilentnights.maalinkserver.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    private MaaCore maaCore;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Response<Boolean> getStatus() {

    }
}
