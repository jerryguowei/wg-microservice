package com.duduanan.commons.dto;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -7787143070394187323L;
    private Integer count;

    private int code;

    private List<T> data;

    public PageResult() {

    }
    public PageResult(List<T> data, int code, int count) {
        this.count = count;
        this.code = code;
        this.data = data;
    }

    public static <T> PageResult<T> build(List<T> data, int code, int count) {
        return new PageResult<>(data, code, count);
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
