package com.simon.magiccube.facade.dto;


/**
 * Created by mac on 2020/4/30.
 */
public class ParameterDTO {
    private String paramType;
    private Object parameter;

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "ParameterDTO{" +
                "paramType='" + paramType + '\'' +
                ", parameter=" + parameter +
                '}';
    }
}
