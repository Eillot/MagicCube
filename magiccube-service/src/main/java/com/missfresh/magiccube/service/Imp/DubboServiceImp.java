package com.simon.magiccube.service.Imp;

import com.simon.magiccube.engine.enums.ParameterTypeEnum;
import com.simon.magiccube.service.DubboService;

/**
 * Created by wangxl03 on 2020/5/3.
 */
public class DubboServiceImp implements DubboService {

    @Override
    public String getParamTypeByCode(String paramTypeCode) {
        String paramTypeClass;
        switch (paramTypeCode) {
            case "string": paramTypeClass = ParameterTypeEnum.STRING.getParamClass();break;
            case "int" : paramTypeClass = ParameterTypeEnum.INT.getParamClass();break;
            case "date" : paramTypeClass = ParameterTypeEnum.DATE.getParamClass();break;
            case "long" : paramTypeClass = ParameterTypeEnum.LONG.getParamClass();break;
            case "float" : paramTypeClass = ParameterTypeEnum.FLOAT.getParamClass();break;
            case "list" : paramTypeClass = ParameterTypeEnum.LIST.getParamClass();break;
            case "boolean" : paramTypeClass = ParameterTypeEnum.BOOLEAN.getParamClass();break;
            case "short" : paramTypeClass = ParameterTypeEnum.SHORT.getParamClass();break;
            default: paramTypeClass = paramTypeCode;
        }
        return paramTypeClass;
    }
}
