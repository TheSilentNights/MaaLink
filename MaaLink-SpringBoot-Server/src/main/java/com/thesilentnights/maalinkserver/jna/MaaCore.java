package com.thesilentnights.maalinkserver.jna;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface MaaCore extends Library {
    String AsstGetVersion();

    boolean AsstLoadResource(String path);

    interface AsstApiCallback extends Callback {
        void callback(int msg, String detail_json, String custom_arg);
    }

    void AsstLog(String level, String message);

    //获取实例
    Pointer AsstCreateEx(AsstApiCallback callback, String custom_arg);

    int AsstAppendTask(Pointer handle, String type, String params);

    boolean AsstStart(Pointer handle);

    boolean AsstConnect(Pointer handle, String adb, String host, String config);


    void AsstDestroy(Pointer pointer);
}
