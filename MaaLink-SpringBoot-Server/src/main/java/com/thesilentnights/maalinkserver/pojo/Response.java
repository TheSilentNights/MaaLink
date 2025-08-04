package com.thesilentnights.maalinkserver.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL) // 值为null的字段不参与序列化
public class Response<T> {
    private int code;       // 状态码
    private String message; // 消息描述
    private T data;         // 泛型数据体

    // 私有化构造器（推荐使用静态工厂方法）
    private Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // ===================== 成功响应 =====================
    public static <T> Response<T> success() {
        return new Response<>(HttpStatus.OK.value(), "操作成功", null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(HttpStatus.OK.value(), "操作成功", data);
    }

    public static <T> Response<T> success(String message, T data) {
        return new Response<>(HttpStatus.OK.value(), message, data);
    }

    // ===================== 错误响应 =====================
    public static <T> Response<T> error(int code, String message) {
        return new Response<>(code, message, null);
    }

    public static <T> Response<T> error(HttpStatus status, String message) {
        return new Response<>(status.value(), message, null);
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }

    // ===================== 带数据的错误响应 =====================
    public static <T> Response<T> error(int code, String message, T errorData) {
        return new Response<>(code, message, errorData);
    }

    // ===================== Getter =====================
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
