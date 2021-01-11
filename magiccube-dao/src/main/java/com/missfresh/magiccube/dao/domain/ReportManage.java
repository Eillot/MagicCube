package com.simon.magiccube.dao.domain;

import com.alibaba.fastjson.JSONArray;

import java.util.Date;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/3/25 9:04 下午
 */
public class ReportManage {

    private long id;
    private String caseId;
    private String caseName;
    private String interfaceUrl;
    private String environment;
    private long runResult;
    private long responseTime;
    private long statusCode;
    private String assertResultAndDes;
    private String assertId;
    private String caseResponseResult;
    private String testDataId;
    private String caseDataContent;
    private Date createrTime;
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

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public long getRunResult() {
        return runResult;
    }

    public void setRunResult(long runResult) {
        this.runResult = runResult;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(long statusCode) {
        this.statusCode = statusCode;
    }

    public String getAssertResultAndDes() {
        return assertResultAndDes;
    }

    public void setAssertResultAndDes(String assertResultAndDes) {
        this.assertResultAndDes = assertResultAndDes;
    }

    public String getAssertId() {
        return assertId;
    }

    public void setAssertId(String assertId) {
        this.assertId = assertId;
    }

    public String getCaseResponseResult() {
        return caseResponseResult;
    }

    public void setCaseResponseResult(String caseResponseResult) {
        this.caseResponseResult = caseResponseResult;
    }

    public String getTestDataId() {
        return testDataId;
    }

    public void setTestDataId(String testDataId) {
        this.testDataId = testDataId;
    }

    public String getCaseDataContent() {
        return caseDataContent;
    }

    public void setCaseDataContent(String caseDataContent) {
        this.caseDataContent = caseDataContent;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(long isDelete) {
        this.isDelete = isDelete;
    }


    @Override
    public String toString() {
        return "CaseManage{" +
                "id=" + id +
                ", caseId='" + caseId + '\'' +
                ", caseName='" + caseName + '\'' +
                ", interfaceUrl='" + interfaceUrl + '\'' +
                ", environment=" + environment + '\'' +
                ", runResult='" + runResult +
                ", responseTime='" + responseTime +
                ", statusCode='" + statusCode +
                ", assertResultAndDes='" + assertResultAndDes + '\'' +
                ", assertId='" + assertId + '\'' +
                ", caseResponseResult='" + caseResponseResult + '\'' +
                ", testDataId='" + testDataId + '\'' +
                ", caseDataContent='" + caseDataContent + '\'' +
                ", createrTime=" + createrTime +
                ", isDelete=" + isDelete +'\'' +



                '}';
    }

}
