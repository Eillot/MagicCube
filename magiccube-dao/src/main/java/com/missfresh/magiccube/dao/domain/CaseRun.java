package com.simon.magiccube.dao.domain;

import java.util.Date;
import java.util.List;

public class CaseRun {

    /****/
    private Integer id;
    /**执行结果（1-成功，2-失败，3-异常）**/
    private Integer runResult;
    /**用例id**/
    private String caseId;
    private List<String> caseIdList;
    /**执行环境id**/
    private String environmentId;
    /**耗时(ms)**/
    private Integer responseTime;
    /**返回结果**/
    private String responseResult;
    /**断言执行id**/
    private String assertRunId;
    /**批量执行id（批次）**/
    private String sceneId;
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
    private Integer limit;

    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return this.id;
    }
    public void setRunResult(Integer runResult){
        this.runResult = runResult;
    }
    public Integer getRunResult(){
        return this.runResult;
    }
    public void setCaseId(String caseId){
        this.caseId = caseId;
    }
    public String getCaseId(){
        return this.caseId;
    }

    public List<String> getCaseIdList() {
        return caseIdList;
    }

    public void setCaseIdList(List<String> caseIdList) {
        this.caseIdList = caseIdList;
    }

    public void setEnvironmentId(String environmentId){
        this.environmentId = environmentId;
    }
    public String getEnvironmentId(){
        return this.environmentId;
    }
    public void setResponseTime(Integer responseTime){
        this.responseTime = responseTime;
    }
    public Integer getResponseTime(){
        return this.responseTime;
    }
    public void setResponseResult(String responseResult){
        this.responseResult = responseResult;
    }
    public String getResponseResult(){
        return this.responseResult;
    }
    public void setAssertRunId(String assertRunId){
        this.assertRunId = assertRunId;
    }
    public String getAssertRunId(){
        return this.assertRunId;
    }
    public void setSceneId(String sceneId){
        this.sceneId = sceneId;
    }
    public String getSceneId(){
        return this.sceneId;
    }
    public void setRemark(String remark){
        this.remark = remark;
    }
    public String getRemark(){
        return this.remark;
    }
    public void setCreaterName(String createrName){
        this.createrName = createrName;
    }
    public String getCreaterName(){
        return this.createrName;
    }
    public void setCreaterTime(Date createrTime){
        this.createrTime = createrTime;
    }
    public Date getCreaterTime(){
        return this.createrTime;
    }
    public void setModifyName(String modifyName){
        this.modifyName = modifyName;
    }
    public String getModifyName(){
        return this.modifyName;
    }
    public void setModifyTime(Date modifyTime){
        this.modifyTime = modifyTime;
    }
    public Date getModifyTime(){
        return this.modifyTime;
    }
    public void setIsDelete(Integer isDelete){
        this.isDelete = isDelete;
    }
    public Integer getIsDelete(){
        return this.isDelete;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "CaseRun [ " +
                "id= "+ id +
                ",runResult= "+runResult+
                ",caseId= "+caseId+
                ",environmentId= "+environmentId+
                ",responseTime= "+responseTime+
                ",responseResult= "+responseResult+
                ",assertRunId= "+assertRunId+
                ",sceneId= "+sceneId+
                ",remark= "+remark+
                ",createrName= "+createrName+
                ",createrTime= "+createrTime+
                ",modifyName= "+modifyName+
                ",modifyTime= "+modifyTime+
                ",isDelete= "+isDelete+
                "]";
    }


}
