package com.simon.magiccube.dao.domain;

import java.util.Date;

public class TestData {

    private long id;
    private String caseId;
    private long inputFormat;
    private String caseDataContent;
    private String baseDataParam;
    private String sceneId;
    private String remark;
    private String createrName;
    private Date createrTime;
    private String modifyName;
    private Date modifyTime;
    private long isDelete;


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }


    public long getInputFormat() {
        return inputFormat;
    }

    public void setInputFormat(long inputFormat) {
        this.inputFormat = inputFormat;
    }



    public String getCaseDataContent() {
        return caseDataContent;
    }

    public void setCaseDataContent(String caseDataContent) {
        this.caseDataContent = caseDataContent;
    }

    public String getBaseDataParam() {
        return baseDataParam;
    }

    public void setBaseDataParam(String baseDataParam) {
        this.baseDataParam = baseDataParam;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
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
        return "TestData{" +
                "id=" + id +
                ", caseId='" + caseId + '\'' +
                ", inputFormat=" + inputFormat +
                ", caseDataContent='" + caseDataContent + '\'' +
                ", baseDataParam='" + baseDataParam + '\'' +
                ", sceneId='" + sceneId + '\'' +
                ", remark='" + remark + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createrTime=" + createrTime +
                ", modifyName='" + modifyName + '\'' +
                ", modifyTime=" + modifyTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
