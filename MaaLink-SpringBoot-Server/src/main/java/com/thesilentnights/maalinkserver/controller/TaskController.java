package com.thesilentnights.maalinkserver.controller;

import com.thesilentnights.maalinkserver.pojo.Response;
import com.thesilentnights.maalinkserver.repo.MaaStatus;
import com.thesilentnights.maalinkserver.service.LoginService;
import com.thesilentnights.maalinkserver.service.MaaLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class TaskController {
    @Autowired
    MaaLinkService maaLinkService;
    @Autowired
    LoginService loginService;
    @Autowired
    MaaStatus maaStatus;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Response<Map<String,Boolean>> getStatus() {
        return Response.success(new HashMap<String, Boolean>() {{
            put("isRunning",maaStatus.isRunning());
            put("isLoaded",maaStatus.isLoaded());
            put("isConnected",maaStatus.isConnected());
        }});
    }

    @RequestMapping(value = "/appendTask", method = RequestMethod.POST)
    public Response<Map<String, Integer>> appendTask(@RequestParam("type") String type, @RequestParam("params") String params) {
        return Response.success(new HashMap<String, Integer>() {{
            put("taskId", maaLinkService.appendTask(type, params));
        }});
    }

    @RequestMapping(value = "/start")
    public Response<Map<String, Boolean>> start() {
        return Response.success(new HashMap<String, Boolean>() {{
            ;
            put("result", maaLinkService.start());
        }});
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<Map<String, String>> login(@RequestBody Map<String, String> body) {
        String token = loginService.login(body.get("username"), body.get("password"));
        if (token == null) {
            return Response.success("登录失败，请检查用户名或密码", null);
        }
        return Response.success(new HashMap<String, String>() {{
            put("token", token);
        }});
    }

    @RequestMapping(value = "/getCurrentTask", method = RequestMethod.GET)
    public Response<Map<String, String>> getCurrentTask() {
        Map<String, String> result = new HashMap<>();
        result.put("taskType", maaLinkService.getCurrentTask());
        return Response.success(result);
    }
}
