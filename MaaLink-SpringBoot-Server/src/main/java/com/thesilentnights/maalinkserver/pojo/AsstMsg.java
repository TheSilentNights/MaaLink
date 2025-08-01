package com.thesilentnights.maalinkserver.pojo;

public enum AsstMsg {
    /* Global Info */
    INTERNAL_ERROR(0),           // 内部错误
    INIT_FAILED(1),               // 初始化失败
    CONNECTION_INFO(2),           // 连接相关信息
    ALL_TASKS_COMPLETED(3),       // 全部任务完成
    ASYNC_CALL_INFO(4),           // 外部异步调用信息
    DESTROYED(5),                 // 实例已销毁

    /* TaskChain Info */
    TASK_CHAIN_ERROR(10000),      // 任务链执行/识别错误
    TASK_CHAIN_START(10001),      // 任务链开始
    TASK_CHAIN_COMPLETED(10002),  // 任务链完成
    TASK_CHAIN_EXTRA_INFO(10003), // 任务链额外信息
    TASK_CHAIN_STOPPED(10004),    // 任务链手动停止

    /* SubTask Info */
    SUB_TASK_ERROR(20000),        // 原子任务执行/识别错误
    SUB_TASK_START(20001),        // 原子任务开始
    SUB_TASK_COMPLETED(20002),    // 原子任务完成
    SUB_TASK_EXTRA_INFO(20003),   // 原子任务额外信息
    SUB_TASK_STOPPED(20004);      // 原子任务手动停止

    private final int value;

    AsstMsg(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}