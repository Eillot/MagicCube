package com.simon.magiccube.dao.domain;

import java.util.Date;

public class SceneRun {

    /****/
    private Integer id;
    /**场景执行结果（1-成功，2-失败，3-异常）**/
    private Integer sceneRunResult;
    /**场景id**/
    private String sceneId;
    /**用例总数**/
    private Integer caseTotal;
    /**用例成功数**/
    private Integer caseSuccessTotal;
    /**用例失败数**/
    private Integer caseFailTotal;
    /**执行环境id**/
    private String environmentId;
    /**耗时(ms)**/
    private Integer responseTime;
    /**返回结果**/
    private String responseResult;
    /**备注**/
    private String remark;
    /**创建人**/
    private String createrName;
    /**创建时间**/
    private Date createrTime;
    /**修改人**/
    private String modifyName;
    /**修改时间**/
    private Date modifyTime;
    /**是否删除（0-删除，1-未删除）**/
    private Integer isDelete;
    private String environmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSceneRunResult() {
        return sceneRunResult;
    }

    public void setSceneRunResult(Integer sceneRunResult) {
        this.sceneRunResult = sceneRunResult;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public Integer getCaseTotal() {
        return caseTotal;
    }

    public void setCaseTotal(Integer caseTotal) {
        this.caseTotal = caseTotal;
    }

    public Integer getCaseSuccessTotal() {
        return caseSuccessTotal;
    }

    public void setCaseSuccessTotal(Integer caseSuccessTotal) {
        this.caseSuccessTotal = caseSuccessTotal;
    }

    public Integer getCaseFailTotal() {
        return caseFailTotal;
    }

    public void setCaseFailTotal(Integer caseFailTotal) {
        this.caseFailTotal = caseFailTotal;
    }

    public String getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(String responseResult) {
        this.responseResult = responseResult;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    @Override
    public String toString() {
        return "SceneRun [ " +
                "id= "+ id +
                ",sceneRunResult= "+sceneRunResult+
                ",sceneId= "+sceneId+
                ",caseTotal= "+caseTotal+
                ",caseSuccessTotal= "+caseSuccessTotal+
                ",caseFailTotal= "+caseFailTotal+
                ",environmentId= "+environmentId+
                ",responseTime= "+responseTime+
                ",responseResult= "+responseResult+
                ",remark= "+remark+
                ",createrName= "+createrName+
                ",createrTime= "+createrTime+
                ",modifyName= "+modifyName+
                ",modifyTime= "+modifyTime+
                ",isDelete= "+isDelete+
                "]";
    }


}
