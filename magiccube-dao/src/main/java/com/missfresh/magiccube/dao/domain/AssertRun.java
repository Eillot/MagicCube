package com.simon.magiccube.dao.domain;

import java.util.Date;

public class AssertRun {

    /****/
    private Integer id;
    /**断言id**/
    private String assertId;
    /**断言执行结果（1-成功，2-失败，3-异常）**/
    private Integer assertRunResult;
    /**断言执行日志**/
    private String assertResponseResult;
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

    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return this.id;
    }
    public void setAssertId(String assertId){
        this.assertId = assertId;
    }
    public String getAssertId(){
        return this.assertId;
    }
    public void setAssertRunResult(Integer assertRunResult){
        this.assertRunResult = assertRunResult;
    }
    public Integer getAssertRunResult(){
        return this.assertRunResult;
    }
    public void setAssertResponseResult(String assertResponseResult){
        this.assertResponseResult = assertResponseResult;
    }
    public String getAssertResponseResult(){
        return this.assertResponseResult;
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


    @Override
    public String toString() {
        return "AssertRun [ " +
                "id= "+ id +
                ",assertId= "+assertId+
                ",assertRunResult= "+assertRunResult+
                ",assertResponseResult= "+assertResponseResult+
                ",remark= "+remark+
                ",createrName= "+createrName+
                ",createrTime= "+createrTime+
                ",modifyName= "+modifyName+
                ",modifyTime= "+modifyTime+
                ",isDelete= "+isDelete+
                "]";
    }


}
