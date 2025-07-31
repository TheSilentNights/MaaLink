package com.thesilentnights.maalinkserver.service;

import com.thesilentnights.maalinkserver.repo.TaskStorge;
import com.thesilentnights.maalinkserver.repo.MaaInstance;
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

    public boolean start() {
        return maaInstance.getMaaCore().AsstStart(maaInstance.getHandle());
    }

    //返回任务参数
    public int appendTask(String type, String params) {
        return maaInstance.getMaaCore().AsstAppendTask(maaInstance.getHandle(), type, params);
    }

    public boolean connect() {
        return maaInstance.getMaaCore().AsstConnect(maaInstance.getHandle(), adbPath, host, "");
    }

    public String getCurrentTask(){
        return taskStorge.getTaskById(taskStorge.getCurrentTaskId());
    }


}
