package com.simon.magiccube.dao.domain;


public class CaseParameterizationRelation {

    private long id;
    private int caseId;
    private int parameterizationId;
    private long isDelete;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getParameterizationId() {
        return parameterizationId;
    }

    public void setParameterizationId(int parameterizationId) {
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
