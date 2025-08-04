package com.thesilentnights.maalinkserver.service;

import com.sun.jna.Pointer;
import com.thesilentnights.maalinkserver.jna.CallBackMethod;
import com.thesilentnights.maalinkserver.repo.MaaInstance;
import com.thesilentnights.maalinkserver.repo.MaaStatus;
import com.thesilentnights.maalinkserver.repo.TaskStorge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MaaLinkService {
    @Autowired
    MaaInstance maaInstance;
    @Value("D:\\Program Files\\Netease\\MuMu Player 12\\nx_device\\12.0\\shell\\.\\adb.exe")
    String adbPath;
    @Value("192.168.71.112:5555")
    String host;
    @Autowired
    TaskStorge taskStorge;
    @Autowired
    CallBackMethod callBackMethod;
    @Autowired
    MaaStatus maaStatus;

    public boolean start() {
        checkStatus();
        if (!maaInstance.getMaaCore().AsstRunning(maaInstance.getHandle())){
            return maaInstance.getMaaCore().AsstStart(maaInstance.getHandle());
        } else {

            return false;
        }
    }

    public boolean stop() {
        if (maaInstance.getMaaCore().AsstRunning(maaInstance.getHandle())) {
            return maaInstance.getMaaCore().AsstStop(maaInstance.getHandle());
        } else {
            return false;
        }
    }

    public boolean isRunning(){
        return maaInstance.getMaaCore().AsstRunning(maaInstance.getHandle());
    }

    //返回任务参数
    public int appendTask(String type, String params) {
        checkStatus();
        int taskId = maaInstance.getMaaCore().AsstAppendTask(maaInstance.getHandle(), type, params);
        if (taskId > 0) {
            taskStorge.addTask(taskId, type);
        }
        return taskId;
    }

    private void connect() {
        maaStatus.setConnected(maaInstance.getMaaCore().AsstConnect(maaInstance.getHandle(), adbPath, host, ""));
    }

    private boolean createHandle() {
        Pointer maaLink = maaInstance.getMaaCore().AsstCreateEx(callBackMethod, "MaaLink");
        if (maaLink != null) {
            maaInstance.setHandle(maaLink);
            return true;
        } else {
            return false;
        }
    }

    public String getCurrentTask() {
        return taskStorge.getTaskById(taskStorge.getCurrentTaskId());
    }

    //检查maa链接句斌是否被正确创建
    private void checkStatus() {
        if (maaInstance.getHandle() == null) {
            if (!createHandle()) {
                throw new RuntimeException("Failed to create MaaLink handle");
            }
            if (!maaStatus.isConnected()) {
                connect();
            }
        }
    }


}
