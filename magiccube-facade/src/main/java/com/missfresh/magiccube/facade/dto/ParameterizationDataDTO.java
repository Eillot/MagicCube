package com.simon.magiccube.facade.dto;

import java.util.Date;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2020/1/4 2:43 PM
 * @File : ParameterizationDataDTO
 * @Software: IntelliJ IDEA 2018.1.8
 */
public class ParameterizationDataDTO {

    private long id;
    private String pruductId;
    private String parameterName;
    private String remark;
    private long parameterType;
    private String parameterContent;
    private String selectField;
    private String resultValue;
    private String createrName;
    private Date createrTime;
    private String modifyName;
    private Date modifyTime;
    private long isDelete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPruductId() {
        return pruductId;
    }

    public void setPruductId(String pruductId) {
        this.pruductId = pruductId;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getParameterType() {
        return parameterType;
    }

    public void setParameterType(long parameterType) {
        this.parameterType = parameterType;
    }

    public String getParameterContent() {
        return parameterContent;
    }

    public void setParameterContent(String parameterContent) {
        this.parameterContent = parameterContent;
    }

    public String getSelectField() {
        return selectField;
    }

    public void setSelectField(String selectField) {
        this.selectField = selectField;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(long isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", pruductId='" + pruductId + '\'' +
                ", parameterName='" + parameterName + '\'' +
                ", remark='" + remark + '\'' +
                ", parameterType=" + parameterType +
                ", parameterContent='" + parameterContent + '\'' +
                ", selectField='" + selectField + '\'' +
                ", resultValue='" + resultValue + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createrTime=" + createrTime +
                ", modifyName='" + modifyName + '\'' +
                ", modifyTime=" + modifyTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
