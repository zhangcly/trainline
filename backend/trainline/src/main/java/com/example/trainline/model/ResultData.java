package com.example.trainline.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 处理结果封装
 * @author zhangc
 * @date 20190626
 */
@Data
public class ResultData<T> implements Serializable {
    private String message;
    private T data;
    private int success;
    public void set(String message, T data, int success){
        this.message = message;
        this.data = data;
        this.success = success;
    }
}
