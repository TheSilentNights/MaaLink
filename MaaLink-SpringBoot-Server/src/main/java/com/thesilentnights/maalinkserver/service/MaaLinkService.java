package com.thesilentnights.maalinkserver.service;

import com.sun.jna.Pointer;
import com.thesilentnights.maalinkserver.config.MainConfig;
import com.thesilentnights.maalinkserver.jna.CallBackMethod;
import com.thesilentnights.maalinkserver.repo.MaaInstance;
import com.thesilentnights.maalinkserver.repo.MaaStatus;
import com.thesilentnights.maalinkserver.repo.TaskStorge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaaLinkService {
    @Autowired
    MaaInstance maaInstance;
    @Autowired
    TaskStorge taskStorge;
    @Autowired
    CallBackMethod callBackMethod;
    @Autowired
    MaaStatus maaStatus;
    @Autowired
    MainConfig mainConfig;


    public boolean start() {
        checkHandle();
        //未连接
        if (!checkConnection()) {
            return false;
        }
        if (!maaInstance.getMaaCore().AsstRunning(maaInstance.getHandle())) {
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


    public boolean isRunning() {
        return maaInstance.getMaaCore().AsstRunning(maaInstance.getHandle());
    }

    //返回任务参数
    public int appendTask(String type, String params) {
        checkHandle();
        int taskId = maaInstance.getMaaCore().AsstAppendTask(maaInstance.getHandle(), type, params);
        if (taskId > 0) {
            taskStorge.addTask(taskId, type);
        }
        return taskId;
    }

    private boolean connect() {
        checkHandle();

        boolean b = maaInstance.getMaaCore().AsstConnect(maaInstance.getHandle(), mainConfig.getAdbPath(), mainConfig.getHost(), "");
        maaStatus.setConnected(b);
        return b;
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
    private void checkHandle() {
        if (maaInstance.getHandle() == null) {
            if (!createHandle()) {
                throw new RuntimeException("Failed to create MaaLink handle");
            }
        }
    }

    private boolean checkConnection() {
        if (!maaStatus.isConnected()) {
            return connect();
        }
        return true;
    }


}
