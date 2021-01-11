package com.simon.magiccube.dao.domain;


public class CaseSceneRunRelation {

    private long id;
    private long runObjectType;
    private String runObjectId;
    private String runId;
    private long isDelete;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getRunObjectType() {
        return runObjectType;
    }

    public void setRunObjectType(long runObjectType) {
        this.runObjectType = runObjectType;
    }


    public String getRunObjectId() {
        return runObjectId;
    }

    public void setRunObjectId(String runObjectId) {
        this.runObjectId = runObjectId;
    }


    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }


    public long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(long isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "CaseSceneRunRelation{" +
                "id=" + id +
                ", runObjectType=" + runObjectType +
                ", runObjectId='" + runObjectId + '\'' +
                ", runId='" + runId + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
