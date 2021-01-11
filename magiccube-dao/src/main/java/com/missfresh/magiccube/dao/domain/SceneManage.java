package com.simon.magiccube.dao.domain;

import java.util.Date;

public class SceneManage {

    private long id;
    private String productId;
    private String appId;
    private String sceneName;
    private String sceneDes;
    private String remark;
    private String createrName;
    private Date createrTime;
    private String modifyName;
    private Date modifyTime;
    private long isDelete;

    private String productName;
    private String appName;
    private long runResult;
    private int pageNum;
    private int pageSize;


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }


    public String getSceneDes() {
        return sceneDes;
    }

    public void setSceneDes(String sceneDes) {
        this.sceneDes = sceneDes;
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

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getRunResult() {
        return runResult;
    }

    public void setRunResult(long runResult) {
        this.runResult = runResult;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "SceneManage{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", appId='" + appId + '\'' +
                ", sceneName='" + sceneName + '\'' +
                ", sceneDes='" + sceneDes + '\'' +
                ", remark='" + remark + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createrTime=" + createrTime +
                ", modifyName='" + modifyName + '\'' +
                ", modifyTime=" + modifyTime +
                ", isDelete=" + isDelete +
                ", productName=" + productName +'\'' +
                ", runResult=" + runResult +'\'' +
                ", appName=" + appName +'\'' +
                ", pageNum=" + pageNum +'\'' +
                ", pageSize=" + pageSize +'\'' +
                '}';
    }
}
