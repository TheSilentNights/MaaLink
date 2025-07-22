package com.thesilentnights.maalinkserver.dao;

import com.sun.jna.Pointer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MaaTask {
    Map<String, Pointer> pointerMap = new HashMap<>();

    public void addConn(String connId,Pointer pointer) {
        pointerMap.put(connId,pointer);
    }

    public void destroyConn(String connId){
        pointerMap.remove(connId);
    }
}
