package com.thesilentnights.maalinkserver.repo;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TaskStorge {
    Map<Integer, String> currentTasks = new HashMap<>();
    int currentTaskId;

    public void addTask(int taskId, String taskType) {
        currentTasks.put(taskId, taskType);
    }

    public String getTaskById(int id) {
        return currentTasks.get(id);
    }

    public void finishTask(int taskId) {
        currentTasks.remove(taskId);
    }

    public boolean checkTask(String taskType) {
        return !currentTasks.containsValue(taskType);
    }

    public void setCurrentTaskId(int currentTaskId) {
        this.currentTaskId = currentTaskId;
    }

    public void clearTasks() {
        currentTasks.clear();
        currentTaskId = 0;
    }

    public int getCurrentTaskId() {
        return currentTaskId;
    }
}
