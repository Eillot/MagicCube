package com.simon.magiccube.facade.dto;

import java.util.Date;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/30 3:53 PM
 * @File : AppManageDTO
 * @Software: IntelliJ IDEA 2018.1.8
 */
public class AppManageDTO {

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

    public String getAddressIp() {
        return addressIp;
    }

    public void setAddressIp(String addressIp) {
        this.addressIp = addressIp;
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
