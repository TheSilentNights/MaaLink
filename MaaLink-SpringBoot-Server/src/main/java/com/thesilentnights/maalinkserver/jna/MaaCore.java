package com.thesilentnights.maalinkserver.jna;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

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

    boolean AsstStop(Pointer handle);

    boolean AsstRunning(Pointer handle);
}
