package com.thesilentnights.maalinkserver.jna;

import com.alibaba.fastjson2.JSONObject;
import com.thesilentnights.maalinkserver.MaaLinkSpringBootServerApplication;
import com.thesilentnights.maalinkserver.pojo.AsstMsg;
import com.thesilentnights.maalinkserver.repo.TaskStorge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

//callback方法类
@Component
public class CallBackMethod implements MaaCore.AsstApiCallback {
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CallBackMethod.class);
    @Autowired
    TaskStorge currentTask;

    @Override
    public void callback(int msg, String detail_json, String custom_arg) {
        logger.info("callback: {} {} {}", msg, detail_json, custom_arg);
        JSONObject object = JSONObject.parseObject(detail_json);

        //处理错误
        if (msg == AsstMsg.INTERNAL_ERROR.getValue() || msg == AsstMsg.INIT_FAILED.getValue()) { //内部错误直接关闭
            Logger.getLogger("MaaLinkError").info("InternalError");
            SpringApplication.exit(MaaLinkSpringBootServerApplication.getContext());
        }

        //开始任务
        if (msg == AsstMsg.TASK_CHAIN_START.getValue()) {
            System.out.println("starting task: " + object.getInteger("taskid"));
            currentTask.setCurrentTaskId(object.getInteger("taskid"));
        }

        //完成任务
        if (msg == AsstMsg.TASK_CHAIN_COMPLETED.getValue()) {
            System.out.println("finishing Task" + object.getInteger("taskid"));
            Integer taskId = object.getInteger("taskid");
            currentTask.finishTask(taskId);
        }

        //全部完成
        if (msg == AsstMsg.ALL_TASKS_COMPLETED.getValue()) {
            logger.info("all task completed");
            currentTask.clearTasks();
        }


    }
}
