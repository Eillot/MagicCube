package com.simon.magiccube.dao.domain;

import java.util.Date;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-26  15:23
 * @File : Image
 * @Software: IntelliJ IDEA 2018.3
 */

public class AppManage {

    private long id;
    private String appName;
    private String appCode;
    private String environmentName;
    private String addressIp;
    private String profile;
    private String version;
    private String productId;
    private String remark;
    private String createrName;
    private Date createrTime;
    private String modifyName;
    private Date modifyTime;
    private Boolean isDelete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }


    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }


    public String getAddressIp() {
        return addressIp;
    }

    public void setAddressIp(String addressIp) {
        this.addressIp = addressIp;
    }


    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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


    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    @Override
    public String toString() {
        return "AppManage{" +
                "id=" + id +
                ", appName='" + appName + '\'' +
                ", appCode='" + appCode + '\'' +
                ", environmentName='" + environmentName + '\'' +
                ", addressIp='" + addressIp + '\'' +
                ", profile='" + profile + '\'' +
                ", version='" + version + '\'' +
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
