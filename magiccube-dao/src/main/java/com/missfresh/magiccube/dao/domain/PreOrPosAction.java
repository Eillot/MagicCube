package com.simon.magiccube.dao.domain;

import java.util.Date;

public class PreOrPosAction {

    /****/
    private Integer id;
    /**业务线id**/
    private String productId;
    /**所属应用id**/
    private String appId;
    /**动作名**/
    private String actionName;
    /**动作类型（1-执行sql，2-post请求，3-get请求，4-执行测试用例，5-执行等待时间，6-执行groovy脚本）**/
    private Integer actionType;
    /**数据库id**/
    private String databaseId;
    /**请求类型（1-post，2-get）**/
    private String requestType;
    /**请求url**/
    private String requestUrl;
    /**用例id**/
    private String caseId;
    /**动作内容**/
    private String actionContent;
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
    /****/
    private Integer isDelete;

    private String productName;


    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return this.id;
    }
    public void setProductId(String productId){
        this.productId = productId;
    }
    public String getProductId(){
        return this.productId;
    }
    public void setAppId(String appId){
        this.appId = appId;
    }
    public String getAppId(){
        return this.appId;
    }
    public void setActionName(String actionName){
        this.actionName = actionName;
    }
    public String getActionName(){
        return this.actionName;
    }
    public void setActionType(Integer actionType){
        this.actionType = actionType;
    }
    public Integer getActionType(){
        return this.actionType;
    }
    public void setDatabaseId(String databaseId){
        this.databaseId = databaseId;
    }
    public String getDatabaseId(){
        return this.databaseId;
    }
    public void setRequestType(String requestType){
        this.requestType = requestType;
    }
    public String getRequestType(){
        return this.requestType;
    }
    public void setRequestUrl(String requestUrl){
        this.requestUrl = requestUrl;
    }
    public String getRequestUrl(){
        return this.requestUrl;
    }
    public void setCaseId(String caseId){
        this.caseId = caseId;
    }
    public String getCaseId(){
        return this.caseId;
    }
    public void setActionContent(String actionContent){
        this.actionContent = actionContent;
    }
    public String getActionContent(){
        return this.actionContent;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "PreOrPosAction [ " +
                "id= "+ id +
                ",productId= "+productId+
                ",appId= "+appId+
                ",actionName= "+actionName+
                ",actionType= "+actionType+
                ",databaseId= "+databaseId+
                ",requestType= "+requestType+
                ",requestUrl= "+requestUrl+
                ",caseId= "+caseId+
                ",actionContent= "+actionContent+
                ",remark= "+remark+
                ",createrName= "+createrName+
                ",createrTime= "+createrTime+
                ",modifyName= "+modifyName+
                ",modifyTime= "+modifyTime+
                ",isDelete= "+isDelete+
                ", productName='" + productName + '\'' +
                "]";
    }


}
