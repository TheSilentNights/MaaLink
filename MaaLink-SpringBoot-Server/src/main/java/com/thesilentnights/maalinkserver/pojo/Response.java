package com.thesilentnights.maalinkserver.pojo;


public class Response<T> {
    private int code;
    private String message;
    private T data;

    private Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功响应（无数据）
    public static Response<Void> success() {
        return new Response<>(200, "操作成功", null);
    }

    // 成功响应（带数据）
    public static <T> Response<T> success(T data) {
        return new Response<>(200, "操作成功", data);
    }

    // 自定义响应
    public static <T> Response<T> response(int code, String message, T data) {
        return new Response<>(code, message, data);
    }

    // 错误响应
    public static <T> Response<T> error(int code, String message) {
        return new Response<>(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
