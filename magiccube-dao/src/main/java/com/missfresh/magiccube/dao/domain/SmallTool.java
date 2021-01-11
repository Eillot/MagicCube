package com.simon.magiccube.dao.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;

public class SmallTool {

    private long id;
    private String interfaceType;//0-http接口，1-dubbo接口
    private String interfaceId;//接口id,源自资源管理平台
    private String parameter;
    private String remark;
    private String createrName;
    private Date createrTime;
    private String modifyName;
    private Date modifyTime;
    private long isDelete = 1;//1-有效，0-无效


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


    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }


    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
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
        return "SmallTool{" +
                "id=" + id +
                ", interfaceType='" + interfaceType + '\'' +
                ", interfaceId='" + interfaceId + '\'' +
                ", parameter='" + parameter + '\'' +
                ", remark='" + remark + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createrTime=" + createrTime +
                ", modifyName='" + modifyName + '\'' +
                ", modifyTime=" + modifyTime +
                ", isDelete=" + isDelete +
                '}';
    }

//    public static void main(String[] args) {
//        SmallTool smallTool = new SmallTool();
//        String test = JSON.toJSONString(smallTool, SerializerFeature.WriteMapNullValue);
//        System.out.println(test);
//    }
}
