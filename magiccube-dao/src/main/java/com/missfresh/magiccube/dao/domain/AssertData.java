package com.simon.magiccube.dao.domain;

import java.util.Date;

public class AssertData {

    /****/
    private Integer id;
    /**测试数据id**/
    private String testDataId;
    /**断言描述**/
    private String assertDes;
    /**断言目标**/
    private String assertTarget;
    /**匹配规则（1-等于，2-不等于，3-包含，4-不包含，5-为空，6-不为空，7-size）**/
    private Integer matchRules;
    /**预期值**/
    private String expectedValue;

    private String sceneId;
    /**备注（预留字段）**/
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


    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return this.id;
    }
    public void setTestDataId(String testDataId){
        this.testDataId = testDataId;
    }
    public String getTestDataId(){
        return this.testDataId;
    }
    public void setAssertDes(String assertDes){
        this.assertDes = assertDes;
    }
    public String getAssertDes(){
        return this.assertDes;
    }
    public void setAssertTarget(String assertTarget){
        this.assertTarget = assertTarget;
    }
    public String getAssertTarget(){
        return this.assertTarget;
    }
    public void setMatchRules(Integer matchRules){
        this.matchRules = matchRules;
    }
    public Integer getMatchRules(){
        return this.matchRules;
    }
    public void setExpectedValue(String expectedValue){
        this.expectedValue = expectedValue;
    }
    public String getExpectedValue(){
        return this.expectedValue;
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


    @Override
    public String toString() {
        return "AssertData [ " +
                "id= "+ id +
                ",testDataId= "+testDataId+
                ",assertDes= "+assertDes+
                ",assertTarget= "+assertTarget+
                ",matchRules= "+matchRules+
                ",expectedValue= "+expectedValue+
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
