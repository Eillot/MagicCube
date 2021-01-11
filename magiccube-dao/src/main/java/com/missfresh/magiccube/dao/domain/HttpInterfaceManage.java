package com.simon.magiccube.dao.domain;

import java.util.Date;

public class HttpInterfaceManage {

    private long id;
    private String httpInterfaceName;
    private String httpInterfaceContent;
    private String httpInterfaceRequestType;
    private String productId;
    private String remark;
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


    public String getHttpInterfaceName() {
        return httpInterfaceName;
    }

    public void setHttpInterfaceName(String httpInterfaceName) {
        this.httpInterfaceName = httpInterfaceName;
    }


    public String getHttpInterfaceContent() {
        return httpInterfaceContent;
    }

    public void setHttpInterfaceContent(String httpInterfaceContent) {
        this.httpInterfaceContent = httpInterfaceContent;
    }


    public String getHttpInterfaceRequestType() {
        return httpInterfaceRequestType;
    }

    public void setHttpInterfaceRequestType(String httpInterfaceRequestType) {
        this.httpInterfaceRequestType = httpInterfaceRequestType;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    @Override
    public String toString() {
        return "HttpInterfaceManage{" +
                "id=" + id +
                ", httpInterfaceName='" + httpInterfaceName + '\'' +
                ", httpInterfaceContent='" + httpInterfaceContent + '\'' +
                ", httpInterfaceRequestType='" + httpInterfaceRequestType + '\'' +
                ", productId='" + productId + '\'' +
                ", remark='" + remark + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createrTime=" + createrTime +
                ", modifyName='" + modifyName + '\'' +
                ", modifyTime=" + modifyTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
