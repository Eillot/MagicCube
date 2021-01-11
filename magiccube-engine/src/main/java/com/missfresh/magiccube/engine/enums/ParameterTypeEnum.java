package com.simon.magiccube.engine.enums;

import java.util.Objects;

/**
 * Created by mac on 2020/4/30.
 */
public enum ParameterTypeEnum {

    STRING("string", "java.lang.String"),
    LIST("list", "java.util.List"),
    DATE("date", "java.util.Date"),
    SHORT("short", "short"),
    LONG("long", "long"),
    FLOAT("float", "float"),
    INT("int","int"),
    BOOLEAN("boolean", "boolean"),
    ARRAY("array","java.lang.String[]");

    private String paramCode;
    private String paramClass;

    ParameterTypeEnum(String paramCode, String paramClass) {
        this.paramCode = paramCode;
        this.paramClass = paramClass;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamClass() {
        return paramClass;
    }

    public void setParamClass(String paramClass) {
        this.paramClass = paramClass;
    }

    public static String getParamClassByCode(String code) {
        if (null == code) {
            return ParameterTypeEnum.STRING.getParamClass();
        }
        for (ParameterTypeEnum value : ParameterTypeEnum.values()) {
            if (Objects.equals(value.getParamCode(), code.toLowerCase())) {
                return value.getParamClass();
            }
        }
        return ParameterTypeEnum.STRING.getParamClass();
    }
}
