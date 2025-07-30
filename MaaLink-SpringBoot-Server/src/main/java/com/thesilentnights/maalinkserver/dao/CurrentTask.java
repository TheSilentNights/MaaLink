package com.thesilentnights.maalinkserver.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CurrentTask {
    Map<String,String> currentTasks = new HashMap<>();

    public void addTask(String taskId,String taskType){
        currentTasks.put(taskId,taskType);
    }

    public void finishTask(String taskId){
        currentTasks.remove(taskId);
    }

    public boolean checkTask(String taskType){
        return !currentTasks.containsValue(taskType);
    }
}
