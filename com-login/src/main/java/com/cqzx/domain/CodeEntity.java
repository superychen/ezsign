package com.cqzx.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * describe:
 *
 * @author donting
 * @date 2019/07/26
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeEntity implements Serializable {

    private int code;
    private String message;
    private Object data;
    private Integer count;

    public CodeEntity() {
    }

    public CodeEntity(int code) {
        this.code = code;
        this.message = ReCode.map.get(code);
    }

    public CodeEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeEntity(int code, Integer count, String message) {
        this.code = code;
        this.count = count;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CodeEntity{" +
                "code=" + this.code +
                ", message='" + this.message + '\'' +
                '}';
    }
}


