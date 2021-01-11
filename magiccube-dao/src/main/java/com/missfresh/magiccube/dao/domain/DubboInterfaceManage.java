package com.simon.magiccube.dao.domain;

import java.util.Date;

public class DubboInterfaceManage {

    private long id;
    private String dubboInterfaceName;
    private String dubboInterfaceContent;
    private String methodName;
    private String methodDes;
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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    public String getMethodDes() {
        return methodDes;
    }

    public void setMethodDes(String methodDes) {
        this.methodDes = methodDes;
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

    public String getDubboInterfaceName() {
        return dubboInterfaceName;
    }

    public void setDubboInterfaceName(String dubboInterfaceName) {
        this.dubboInterfaceName = dubboInterfaceName;
    }

    public String getDubboInterfaceContent() {
        return dubboInterfaceContent;
    }

    public void setDubboInterfaceContent(String dubboInterfaceContent) {
        this.dubboInterfaceContent = dubboInterfaceContent;
    }

    @Override
    public String toString() {
        return "DubboInterfaceManage{" +
                "id=" + id +
                ", dubboInterfaceName='" + dubboInterfaceName + '\'' +
                ", dubboInterfaceContent='" + dubboInterfaceContent + '\'' +
                ", methodName='" + methodName + '\'' +
                ", methodDes='" + methodDes + '\'' +
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
