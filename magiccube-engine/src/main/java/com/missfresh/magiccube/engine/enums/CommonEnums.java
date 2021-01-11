package com.simon.magiccube.engine.enums;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 3:38 PM
 * @File : CommonEnums
 * @Software: IntelliJ IDEA 2018.1.8
 */
public enum CommonEnums {

    /**
     * is_delete 枚举值
     */
    IS_EFFECTIVE(1, "有效"),
    No_EFFECTIVE(0, "无效");

    private int code;
    private String message;

    CommonEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

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


}
