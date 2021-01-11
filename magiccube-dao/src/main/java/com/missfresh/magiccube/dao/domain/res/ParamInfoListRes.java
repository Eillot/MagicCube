package com.simon.magiccube.dao.domain.res;

import com.simon.magiccube.dao.domain.ParamInfoEntity;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

/*
*@author  simon
*@data 2020/7/24 11:38
*/
@Data
public class ParamInfoListRes implements Serializable {

    private static final long serialVersionUID = -14914109117982124L;

    private List<ParamInfoEntity> paramInfoEntities;
    private Integer total;
}

