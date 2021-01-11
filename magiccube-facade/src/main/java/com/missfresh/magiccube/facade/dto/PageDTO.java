package com.simon.magiccube.facade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 分页参数
 * @author: simon
 * @Date: 2020/07/24 下午9:16
 */
@Data
@ApiModel("分页查询请求实体")
public class PageDTO {

    private static final long serialVersionUID = 357257318248093175L;

    @ApiModelProperty("当前页")
    private Integer pageNo = 1;

    @ApiModelProperty("每页条数")
    private Integer pageSize = 10;

    @ApiModelProperty("排序参数")
    private String orderBy;

}
