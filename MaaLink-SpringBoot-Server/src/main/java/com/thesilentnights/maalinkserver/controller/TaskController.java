package com.thesilentnights.maalinkserver.controller;

import com.thesilentnights.maalinkserver.jna.MaaStatus;
import com.thesilentnights.maalinkserver.pojo.Response;
import com.thesilentnights.maalinkserver.service.MaaLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    MaaLinkService maaLinkService;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Response<Boolean> getStatus() {
        if (MaaStatus.isStatus()) {
            return new Response<>(true, "success", 1);
        } else {
            return new Response<>(false, "failed", -1);
        }
    }

    @RequestMapping(value = "/appendTask", method = RequestMethod.POST)
    public Response<Integer> appendTask(@RequestParam("type") String type, @RequestParam("params") String params) {
        return new Response<>(maaLinkService.appendTask(type, params), "success", 1);
    }

    @RequestMapping(value = "/start")
    public Response<Boolean> start() {
        return new Response<>(maaLinkService.start(), "success", 1);
    }

    @RequestMapping(value = "/login")
    public Response<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return null;
    }
}
