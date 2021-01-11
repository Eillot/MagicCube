package com.simon.magiccube.dao.domain;

import java.util.Date;

public class ProductManage {

    private int id;
    private String productName;
    private String productDes;
    private String createrName;
    private Date createrTime;
    private String modifyName;
    private Date modifyTime;
    private String remark;
    private int isDelete;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
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


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "ProductManage{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDes='" + productDes + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createrTime=" + createrTime +
                ", modifyName='" + modifyName + '\'' +
                ", modifyTime=" + modifyTime +
                ", remark='" + remark + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
