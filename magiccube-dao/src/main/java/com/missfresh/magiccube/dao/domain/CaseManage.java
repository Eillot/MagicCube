package com.simon.magiccube.dao.domain;

import java.util.Date;
import java.util.List;

public class CaseManage {

    /**用例id**/
    private Integer id;
    /**用例名称**/
    private Object caseName;
    /**用例描述**/
    private String caseDes;
    /**优先级（1-P0,2-P1,3-P2,4-P3）**/
    private String priority;
    /**业务线id**/
    private String productId;
    /**所属应用id**/
    private String appId;
    /**接口类型（1-http,2-dubbo）**/
    private Integer interfaceType;
    /**接口id**/
    private String interfaceId;
    /**请求类型（1-get,2-post）**/
    private Integer requestType;
    /**前置动作id**/
    private String preActionId;
    /**后置动作id**/
    private String posActionId;
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
    /****/
    private Integer isDelete;

    private String productName;
    private String appName;
    private long runState;
    private long runResult;
    private String interUrl;
    private String interName;
    private String methodName;
    private String runBeginTime;
    private String dubboInterfaceGroup;
    private String dubboInterfaceVersion;
    private String reqDubbo;
    private List<TestData> caseManageForTestDataList;
    private String caseManageForAssertDataList;

    private int pageNum;
    private int pageSize;

    public CaseManage() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getRunState() {
        return runState;
    }

    public void setRunState(long runState) {
        this.runState = runState;
    }

    public long getRunResult() {
        return runResult;
    }

    public void setRunResult(long runResult) {
        this.runResult = runResult;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return this.id;
    }

    public Object getCaseName() {
        return this.caseName;
    }

    public void setCaseName(Object caseName) {
        this.caseName = caseName;
    }
    public void setCaseDes(String caseDes){
        this.caseDes = caseDes;
    }
    public String getCaseDes(){
        return this.caseDes;
    }
    public void setPriority(String priority){
        this.priority = priority;
    }
    public String getPriority(){
        return this.priority;
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
    public void setInterfaceType(Integer interfaceType){
        this.interfaceType = interfaceType;
    }
    public Integer getInterfaceType(){
        return this.interfaceType;
    }
    public void setInterfaceId(String interfaceId){
        this.interfaceId = interfaceId;
    }
    public String getInterfaceId(){
        return this.interfaceId;
    }
    public void setRequestType(Integer requestType){
        this.requestType = requestType;
    }
    public Integer getRequestType(){
        return this.requestType;
    }
    public void setPreActionId(String preActionId){
        this.preActionId = preActionId;
    }
    public String getPreActionId(){
        return this.preActionId;
    }
    public void setPosActionId(String posActionId){
        this.posActionId = posActionId;
    }
    public String getPosActionId(){
        return this.posActionId;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
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

    public String getInterUrl() {
        return interUrl;
    }

    public void setInterUrl(String interUrl) {
        this.interUrl = interUrl;
    }

    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<TestData> getCaseManageForTestDataList() {
        return caseManageForTestDataList;
    }

    public void setCaseManageForTestDataList(List<TestData> caseManageForTestDataList) {
        this.caseManageForTestDataList = caseManageForTestDataList;
    }

    public String getCaseManageForAssertDataList() {
        return caseManageForAssertDataList;
    }

    public void setCaseManageForAssertDataList(String caseManageForAssertDataList) {
        this.caseManageForAssertDataList = caseManageForAssertDataList;
    }

    public String getRunBeginTime() {
        return runBeginTime;
    }

    public void setRunBeginTime(String runBeginTime) {
        this.runBeginTime = runBeginTime;
    }

    public String getDubboInterfaceGroup() {
        return dubboInterfaceGroup;
    }

    public void setDubboInterfaceGroup(String dubboInterfaceGroup) {
        this.dubboInterfaceGroup = dubboInterfaceGroup;
    }

    public String getDubboInterfaceVersion() {
        return dubboInterfaceVersion;
    }

    public void setDubboInterfaceVersion(String dubboInterfaceVersion) {
        this.dubboInterfaceVersion = dubboInterfaceVersion;
    }

    public String getReqDubbo() {
        return reqDubbo;
    }

    public void setReqDubbo(String reqDubbo) {
        this.reqDubbo = reqDubbo;
    }

    @Override
    public String toString() {
        return "CaseManage [ " +
                "id= "+ id +
                ",caseName= "+caseName+
                ",caseDes= "+caseDes+
                ",priority= "+priority+
                ",productId= "+productId+
                ",appId= "+appId+
                ",interfaceType= "+interfaceType+
                ",interfaceId= "+interfaceId+
                ",requestType= "+requestType+
                ",preActionId= "+preActionId+
                ",posActionId= "+posActionId+
                ",sceneId= " + sceneId +
                ",remark= " + remark +
                ",createrName= "+createrName+
                ",createrTime= "+createrTime+
                ",modifyName= "+modifyName+
                ",modifyTime= "+modifyTime+
                ",isDelete= "+isDelete+
                ", productName=" + productName +'\'' +
                ", appName=" + appName +'\'' +
                ", runState=" + runState +'\'' +
                ", runResult=" + runResult +'\'' +
                ", interUrl=" + interUrl +'\'' +
                ", interName=" + interName +'\'' +
                ", methodName=" + methodName +'\'' +
                ", pageNum=" + pageNum +'\'' +
                ", pageSize=" + pageSize +'\'' +
                "]";
    }


}
