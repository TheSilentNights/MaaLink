package com.thesilentnights.maalinkserver.jna;

import com.alibaba.fastjson2.JSONObject;
import com.thesilentnights.maalinkserver.MaaLinkSpringBootServerApplication;
import com.thesilentnights.maalinkserver.dao.TaskStorge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class CallBackMethod implements MaaCore.AsstApiCallback {
    @Autowired
    TaskStorge currentTask;

    @Override
    public void callback(int msg, String detail_json, String custom_arg) {
        System.out.println(msg + "|||" + detail_json + "|||" + custom_arg);
        JSONObject object = JSONObject.parseObject(detail_json);
        if (msg == 0 || msg == 1) { //内部错误直接关闭
            Logger.getLogger("MaaLinkError").info("InternalError");
            SpringApplication.exit(MaaLinkSpringBootServerApplication.getContext());
        }

        //开始任务
        if (msg == 10001){
            System.out.println("starting task: "+object.getInteger("taskid"));
            currentTask.setCurrentTaskId(object.getInteger("taskid"));
        }

        //完成任务
        if (msg == 10002) {
            System.out.println("finishing Task" + object.getInteger("taskid"));
            Integer taskId = object.getInteger("taskid");
            currentTask.finishTask(taskId);
        }
    }
}
