package com.simon.magiccube.facade.dto;

import java.util.Date;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2020/1/4 2:49 PM
 * @File : CaseManageDTO
 * @Software: IntelliJ IDEA 2018.1.8
 */
public class CaseManageDTO {

    private long id;
    private String priority;
    private String productId;
    private long interfaceType;
    private String interfaceId;
    private String preActionId;
    private String posActionId;
    private String remark;
    private String createrName;
    private Date createrTime;
    private String modifyName;
    private Date modifyTime;
    private long isDelete;
    private String caseName;
    private String productName;
    private long runState;
    private long runResult;
    private long pageSize;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(long interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getPreActionId() {
        return preActionId;
    }

    public void setPreActionId(String preActionId) {
        this.preActionId = preActionId;
    }

    public String getPosActionId() {
        return posActionId;
    }

    public void setPosActionId(String posActionId) {
        this.posActionId = posActionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getRunState() {
        return runState;
    }

    public void setRunState(long runState) {
        this.runState = runState;
    }

    public long getRunResult() {
        return runResult;
    }

    public void setRunResult(long runResult) {
        this.runResult = runResult;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "CaseManageDTO{" +
                "id=" + id +
                ", priority='" + priority + '\'' +
                ", productId='" + productId + '\'' +
                ", interfaceType=" + interfaceType +
                ", interfaceId='" + interfaceId + '\'' +
                ", preActionId='" + preActionId + '\'' +
                ", posActionId='" + posActionId + '\'' +
                ", remark='" + remark + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createrTime=" + createrTime +
                ", modifyName='" + modifyName + '\'' +
                ", modifyTime=" + modifyTime +
                ", isDelete=" + isDelete +'\'' +
                ", caseName=" + caseName +'\'' +
                ", productName=" + productName +'\'' +
                ", runState=" + runState +'\'' +
                ", runResult=" + runResult +'\'' +
                ", pageSize=" + pageSize +'\'' +
                '}';
    }
}
