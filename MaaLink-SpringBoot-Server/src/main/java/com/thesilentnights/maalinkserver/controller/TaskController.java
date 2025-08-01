package com.thesilentnights.maalinkserver.controller;

import com.thesilentnights.maalinkserver.pojo.Response;
import com.thesilentnights.maalinkserver.repo.MaaStatus;
import com.thesilentnights.maalinkserver.service.LoginService;
import com.thesilentnights.maalinkserver.service.MaaLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    MaaLinkService maaLinkService;
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Response<Boolean> getStatus() {
        if (MaaStatus.isConnected()) {
            return Response.success(true);
        } else {
            return Response.success(false);
        }
    }

    @RequestMapping(value = "/appendTask", method = RequestMethod.POST)
    public Response<Integer> appendTask(@RequestHeader("token") String token, @RequestParam("type") String type, @RequestParam("params") String params) {
        return Response.success(maaLinkService.appendTask(type, params));
    }

    @RequestMapping(value = "/start")
    public Response<Boolean> start() {
        return Response.success(maaLinkService.start());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        String token = loginService.login(username, password);
        return Response.response(token != null ? 1 : 0, token, token != null ? "success" : "failed");
    }

    @RequestMapping(value = "/getCurrentTask", method = RequestMethod.GET)
    public Response<String> getCurrentTask() {
        return Response.success(maaLinkService.getCurrentTask());
    }
}
