package com.thesilentnights.maalinkserver.jna;

import com.sun.jna.Pointer;
import org.springframework.beans.factory.annotation.Autowired;


public class MaaLinkHandlerInstance {
    @Autowired
    private MaaCore maaCore;
    private Pointer maaInstance;

    public void establish(MaaCore.AsstApiCallback callback, String connID) {
        maaInstance = maaCore.AsstCreateEx(callback, connID);
    }

    public void setMaaInstance(Pointer maaInstance) {
        this.maaInstance = maaInstance;
    }
}
