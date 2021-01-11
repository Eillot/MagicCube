package com.simon.magiccube.facade.dto;

import java.util.*;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2020/1/3 2:58 PM
 * @File : SmallToolDTO
 * @Software: IntelliJ IDEA 2018.1.8
 */
public class SmallToolDTO {

    private long id;
    private String interfaceType;  //1-http接口，2-dubbo接口
    private String requestType; //1-get，2-post
    private String interfaceId;//资源管理平台接口id
    private String interName;//接口名称
    private String interfaceContent;//接口地址（接口内容）
    private String methodName;//方法名称（dubbo接口使用）
    private String methodDict;//方法描述（dubbo接口使用）
    private String serverId;//资源管理平台环境（服务器）id
    private String serverName;//环境名称
    private String runEnv; //运行环境地址（接口执行前缀）
    private String zkVersion;//zk版本号（dubbo接口使用）
    private String group;//zk分组（dubbo接口使用）
    private String registry;//注册中心（dubbo接口使用）
    private String applicationName;//（dubbo接口使用）
    private String paramFormat;//接口的参数格式（form,dto,json)
    private List<ParameterDTO> parameters; //接口参数列表
    private Object parameter;//http接口专用入参
    private String remark;
    private String createrName;
    private Date createrTime;
    private String modifyName;
    private Date modifyTime;
    private long isDelete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName;
    }

    public String getInterfaceContent() {
        return interfaceContent;
    }

    public void setInterfaceContent(String interfaceContent) {
        this.interfaceContent = interfaceContent;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDict() {
        return methodDict;
    }

    public void setMethodDict(String methodDict) {
        this.methodDict = methodDict;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getRunEnv() {
        return runEnv;
    }

    public void setRunEnv(String runEnv) {
        this.runEnv = runEnv;
    }

    public String getZkVersion() {
        return zkVersion;
    }

    public void setZkVersion(String zkVersion) {
        this.zkVersion = zkVersion;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public List<ParameterDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterDTO> parameters) {
        this.parameters = parameters;
    }

    public Object getParameter() {
        return parameter;
    }
    public String getParameterString() {
        if(parameter!=null){
            return parameter.toString();
        }
        return null;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public String getParamFormat() {
        return paramFormat;
    }

    public void setParamFormat(String paramFormat) {
        this.paramFormat = paramFormat;
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

    public long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(long isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "SmallToolDTO{" +
                "id=" + id +
                ", interfaceType='" + interfaceType + '\'' +
                ", requestType='" + requestType + '\'' +
                ", interfaceId='" + interfaceId + '\'' +
                ", interName='" + interName + '\'' +
                ", interfaceContent='" + interfaceContent + '\'' +
                ", methodName='" + methodName + '\'' +
                ", methodDict='" + methodDict + '\'' +
                ", serverId='" + serverId + '\'' +
                ", serverName='" + serverName + '\'' +
                ", runEnv='" + runEnv + '\'' +
                ", zkVersion='" + zkVersion + '\'' +
                ", group='" + group + '\'' +
                ", registry='" + registry + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", paramFormat='" + paramFormat + '\'' +
                ", parameters=" + parameters +
                ", parameter=" + parameter +
                ", remark='" + remark + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createrTime=" + createrTime +
                ", modifyName='" + modifyName + '\'' +
                ", modifyTime=" + modifyTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
