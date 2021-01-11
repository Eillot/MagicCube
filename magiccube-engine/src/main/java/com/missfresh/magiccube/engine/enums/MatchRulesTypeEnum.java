package com.simon.magiccube.engine.enums;

import java.util.Objects;

/**
 * 结果判断
 */
public enum MatchRulesTypeEnum {

    NOT_NULL(1,"内容非空"),
    CONTENT_EQUALS(2,"内容相等"),
    LENGTH_EQUALS(3,"长度相等"),
    JSON_EQUALS(4,"字符串相等"),
    DEFAULT(-1,"断言失败"),
    ;

    private Integer code;
    private String desc;

    MatchRulesTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static MatchRulesTypeEnum getEnumByCode(Integer code) {
        for (MatchRulesTypeEnum value : MatchRulesTypeEnum.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        return MatchRulesTypeEnum.DEFAULT;
    }
}
