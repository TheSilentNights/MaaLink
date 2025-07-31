package com.thesilentnights.maalinkserver.repo;

import com.sun.jna.Pointer;
import com.thesilentnights.maalinkserver.jna.MaaCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MaaInstance {
    @Autowired
    private MaaCore maaCore;
    private Pointer handle;//实例句柄

    public void setHandle(Pointer handle) {
        this.handle = handle;
    }

    public MaaCore getMaaCore() {
        return maaCore;
    }

    public Pointer getHandle() {
        return handle;
    }
}
