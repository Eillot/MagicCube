package com.simon.magiccube.facade.dto;

import java.util.Date;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2020/1/4 2:45 PM
 * @File : PreOrPosActionDTO
 * @Software: IntelliJ IDEA 2018.1.8
 */
public class PreOrPosActionDTO {

    private long id;
    private String actionName;
    private String remark;
    private long actionType;
    private String actionContent;
    private String createrName;
    private Date createrTime;
    private String modifyName;
    private Date modifyTime;
    private long isDelete;
}
