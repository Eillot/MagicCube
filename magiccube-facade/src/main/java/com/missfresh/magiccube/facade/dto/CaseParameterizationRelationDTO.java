package com.simon.magiccube.facade.dto;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class CaseParameterizationRelationDTO {

    private long id;
    private String caseId;
    private String parameterizationId;
    private long isDelete;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }


    public String getParameterizationId() {
        return parameterizationId;
    }

    public void setParameterizationId(String parameterizationId) {
        this.parameterizationId = parameterizationId;
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
                ", caseId='" + caseId + '\'' +
                ", parameterizationId='" + parameterizationId + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
