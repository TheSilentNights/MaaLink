package com.thesilentnights.maalinkserver.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CurrentTask {
    Map<Integer,String> currentTasks = new HashMap<>();

    public void addTask(int taskId,String taskType){
        currentTasks.put(taskId,taskType);
    }

    public void finishTask(int taskId){
        currentTasks.remove(taskId);
    }

    public boolean checkTask(String taskType){
        return !currentTasks.containsValue(taskType);
    }
}
