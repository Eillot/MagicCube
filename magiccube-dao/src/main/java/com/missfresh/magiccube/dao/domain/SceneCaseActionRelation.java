package com.simon.magiccube.dao.domain;


public class SceneCaseActionRelation {

    private long id;
    private long actionObjectType;
    private long actionObjectId;
    private long preOrPosId;
    private long isDelete;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getActionObjectType() {
        return actionObjectType;
    }

    public void setActionObjectType(long actionObjectType) {
        this.actionObjectType = actionObjectType;
    }


    public long getActionObjectId() {
        return actionObjectId;
    }

    public void setActionObjectId(long actionObjectId) {
        this.actionObjectId = actionObjectId;
    }


    public long getPreOrPosId() {
        return preOrPosId;
    }

    public void setPreOrPosId(long preOrPosId) {
        this.preOrPosId = preOrPosId;
    }


    public long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(long isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "SceneCaseActionRelation{" +
                "id=" + id +
                ", actionObjectType=" + actionObjectType +
                ", actionObjectId=" + actionObjectId +
                ", preOrPosId=" + preOrPosId +
                ", isDelete=" + isDelete +
                '}';
    }
}
