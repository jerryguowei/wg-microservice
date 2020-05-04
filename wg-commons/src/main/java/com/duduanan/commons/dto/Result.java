package com.duduanan.commons.dto;

import org.springframework.http.MediaType;

import java.io.Serializable;

public class Result<T>  implements Serializable {
    private static final long serialVersionUID = 175096578722461995L;
    private T data;
    private Integer code;
    private String message;

    public Result(T data, Integer code, String message){
        this.data = data;
        this.code = code;
        this.message = message;
    }
    public static <T> Result<T> success(String message) {
        return  of(null, 200, message);
    }

    public  static <T> Result<T> success(T data){
        return of(data, 200, "");
    }

    public static <T> Result<T> of(T data, Integer code, String message) {
        return new Result<>(data, code, message);
    }

    public static <T> Result<T> failed(String message) {
        return of(null, 400, message);
    }

    public  static <T> Result<T> failed(T data, String message) {
        return of(data, 400, message);
    }
}
