package com.simon.magiccube.dao.domain;

import java.util.Date;

public class RunTask {

    private long id;
    private String taskName;
    private long runState;
    private long runResult;
    private long runType;
    private String reportUrl;
    private String runPeople;
    private Date runTime;
    private String remark;
    private long isDelete;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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


    public long getRunType() {
        return runType;
    }

    public void setRunType(long runType) {
        this.runType = runType;
    }


    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }


    public String getRunPeople() {
        return runPeople;
    }

    public void setRunPeople(String runPeople) {
        this.runPeople = runPeople;
    }


    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(long isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "RunTask{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", runState=" + runState +
                ", runResult=" + runResult +
                ", runType=" + runType +
                ", reportUrl='" + reportUrl + '\'' +
                ", runPeople='" + runPeople + '\'' +
                ", runTime=" + runTime +
                ", remark='" + remark + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
