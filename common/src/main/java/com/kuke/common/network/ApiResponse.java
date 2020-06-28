package com.kuke.common.network;

/**
 * Created by Jessica on 2020/02/12.
 */

public class ApiResponse<T> {

    public boolean flag;
    public String code;
    public String msg;
    public Object codeDesc;
    public T data;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(Object codeDesc) {
        this.codeDesc = codeDesc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return data;
    }

    public void setResult(T data) {
        this.data = data;
    }
}
