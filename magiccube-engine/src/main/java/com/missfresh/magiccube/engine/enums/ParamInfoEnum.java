package com.simon.magiccube.engine.enums;
/*
*@author  simon
*@data 2020/7/24 15:53
*/

public enum ParamInfoEnum {

    SQL(1, "SQL "),
    CASE(2, "CASE "),
    INPUT(3, "INPUT ");

    private int code;
    private String msg;

    ParamInfoEnum( int code, String msg ) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static int getApprovalStatusEnum( int code ) {

        for (ParamInfoEnum approvalStatus : ParamInfoEnum.values()) {

            if (approvalStatus.code == code) {
                return approvalStatus.code;
            }
        }
        //不存在返回0
        return 0;
    }
}
