package com.simon.magiccube.dao.domain;
/*
*@author  simon
*@data 2020/7/23 14:18
*/

import lombok.Data;
import io.swagger.annotations.ApiModel;

import java.util.Date;

@Data
@ApiModel("参数化实体")
public class ParamInfoEntity {

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

    private PaginationQuery pagination;
}
