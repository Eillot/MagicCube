package com.simon.magiccube.facade.support;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  15:56
 * @File : CommonResult
 * @Software: IntelliJ IDEA 2018.3
 */
public class CommonResult<T> {

    /**
     * 前端请求响应码，0：成功，-1：失败
     */
    private String status;
    /**
     * 接口响应状态码（可选）
     */
    private String code;
    /**
     * 接口响应msg
     */
    private String msg;
    /**
     * 接口响应内容
     */
    private T data;

    public CommonResult() {

    }

    public CommonResult(String status, String code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public CommonResult(String status, String code, String msg, T data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
