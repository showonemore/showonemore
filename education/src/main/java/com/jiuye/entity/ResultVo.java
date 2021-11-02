package com.jiuye.entity;

public class ResultVo {
    private int code;//状态码
    private String message;//显示信息
    private Object obj;//数据

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

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public ResultVo() {
    }

    public ResultVo(int code, String message, Object obj) {
        this.code = code;
        this.message = message;
        this.obj = obj;
    }
}
