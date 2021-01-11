package com.simon.magiccube.dao.domain;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/6/16 11:17 上午
 */
public class CaseSceneRelation {
    private Integer id;

    private Integer runObjectType;

    private String sceneId;

    private String caseId;

    private Byte isDelete;

    private Integer caseOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRunObjectType() {
        return runObjectType;
    }

    public void setRunObjectType(Integer runObjectType) {
        this.runObjectType = runObjectType;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCaseOrder() {
        return caseOrder;
    }

    public void setCaseOrder(Integer caseOrder) {
        this.caseOrder = caseOrder;
    }
}
