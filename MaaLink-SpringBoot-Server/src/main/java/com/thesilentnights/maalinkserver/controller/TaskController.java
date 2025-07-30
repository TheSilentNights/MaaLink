package com.thesilentnights.maalinkserver.controller;

import com.thesilentnights.maalinkserver.dao.MaaStatus;
import com.thesilentnights.maalinkserver.pojo.Response;
import com.thesilentnights.maalinkserver.service.Login;
import com.thesilentnights.maalinkserver.service.MaaLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    MaaLinkService maaLinkService;
    @Autowired
    Login login;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Response<Boolean> getStatus() {
        if (MaaStatus.isConnected()) {
            return new Response<>(true, "success", 1);
        } else {
            return new Response<>(false, "failed", -1);
        }
    }

    @RequestMapping(value = "/appendTask", method = RequestMethod.POST)
    public Response<Integer> appendTask(@RequestHeader("token") String token, @RequestParam("type") String type, @RequestParam("params") String params) {
        if (login.verify(token)) {
            return new Response<>(maaLinkService.appendTask(type, params), "success", 1);
        } else {
            return new Response<>(-1, "failed", 505);
        }
    }

    @RequestMapping(value = "/start")
    public Response<Boolean> start() {
        return new Response<>(maaLinkService.start(), "success", 1);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        String token = login.login(username, password);
        return new Response<>(token, token != null ? "success" : "failed", token != null ? 1 : 0);
    }

    @RequestMapping(value = "/getCurrentTask", method = RequestMethod.GET)
    public Response<String> getCurrentTask() {
        return new Response<>(maaLinkService.getCurrentTask(), "success", 1);
    }
}
