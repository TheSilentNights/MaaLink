package com.thesilentnights.maalinkserver.dao;

import com.sun.jna.Pointer;
import com.thesilentnights.maalinkserver.jna.MaaCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class MaaInstance {
    @Autowired
    private MaaCore maaCore;
    private Pointer handle;//实例句柄

    public void AppendTask(String type, String params) {
        maaCore.AsstAppendTask(handle, type, params);
    }

    public void setHandle(Pointer handle) {
        this.handle = handle;
    }

    public static void callBack(int msg, String detail_json, String custom_arg) {
        Logger.getLogger("global").info(msg + detail_json + custom_arg);
    }
}
