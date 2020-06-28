

package com.kuke.common.network;


import com.kuke.common.base.basebean.BaseBean;

/**
 * API接口调用
 *
 * @date: 2020-03-31
 * @version: 1.0
 * @author: wangchenxing
 */
public class ErrorBean extends BaseBean {

    /**
     * code : 401
     * data : {}
     * errorInfo : please use standard token!
     * msg : key_token_invalid
     * status : 401
     */

    private int code;
    private String errorInfo;
    private String msg;
    private String message;
    private int status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
