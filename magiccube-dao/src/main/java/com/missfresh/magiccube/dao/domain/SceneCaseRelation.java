package com.simon.magiccube.dao.domain;


public class SceneCaseRelation {

    private long id;
    private String caseId;
    private String sceneId;
    private long caseOrder;
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


    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }


    public long getCaseOrder() {
        return caseOrder;
    }

    public void setCaseOrder(long caseOrder) {
        this.caseOrder = caseOrder;
    }


    public long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(long isDelete) {
        this.isDelete = isDelete;
    }


    @Override
    public String toString() {
        return "SceneCaseRelation{" +
                "id=" + id +
                ", caseId='" + caseId + '\'' +
                ", sceneId='" + sceneId + '\'' +
                ", caseOrder=" + caseOrder +
                ", isDelete=" + isDelete +
                '}';
    }
}
