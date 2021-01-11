package com.simon.magiccube.engine.enums;

/**
 * @author: yuangp
 * @Date: 2019/10/11 下午8:16
 */
public enum ResponseEnum {

    /**
     * SUCCESS,成功
     */
    SUCCESS200(200, "操作成功", "success"),

    /**
     * SUCCESS,成功
     */
    SUCCESS(0, "成功", "success"),


    INVALID_PARAMS(100, "参数非法", "fail"),

    FAILED(500, "操作失败", "fail"),

    /**
     * ERROR,系统异常
     */
    ERROR(1000, "系统异常", "fail"),

    NULL(101, "参数不能为空", "fail"),

    NON(104, "信息不存在", "fail"),

    ;


    private Integer code;

    private String message;

    private String status;

    ResponseEnum(int code) {
        this.code = code;
    }

    ResponseEnum(int code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{\"code\":"+this.code+",\"message\":\""+this.message+"\"}";
    }

}
