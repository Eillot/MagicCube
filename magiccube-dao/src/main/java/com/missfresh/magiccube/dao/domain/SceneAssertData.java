package com.simon.magiccube.dao.domain;

import java.util.Date;

public class SceneAssertData {

    private long id;
    private String sceneCaseId;
    private String assertDataContent;
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


    public String getSceneCaseId() {
        return sceneCaseId;
    }

    public void setSceneCaseId(String sceneCaseId) {
        this.sceneCaseId = sceneCaseId;
    }


    public String getAssertDataContent() {
        return assertDataContent;
    }

    public void setAssertDataContent(String assertDataContent) {
        this.assertDataContent = assertDataContent;
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
        return "SceneAssertData{" +
                "id=" + id +
                ", sceneCaseId='" + sceneCaseId + '\'' +
                ", assertDataContent='" + assertDataContent + '\'' +
                ", remark='" + remark + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createrTime=" + createrTime +
                ", modifyName='" + modifyName + '\'' +
                ", modifyTime=" + modifyTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
