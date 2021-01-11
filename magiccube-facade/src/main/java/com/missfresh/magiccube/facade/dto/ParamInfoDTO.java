package com.simon.magiccube.facade.dto;
/*
*@author  simon
*@data 2020/7/23 14:18
*/

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("参数化实体")
public class ParamInfoDTO extends PageDTO  implements Serializable {

    private static final long serialVersionUID = 35762848093175L;

    private Integer id;

    private String paramName;

    private String paramValue;

    private Integer businessId;

    private String businessName;

    private Integer type;

    private Integer addressId;

    private String paramContent;

    private String access;

    private String remark;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer delFlag;
}
