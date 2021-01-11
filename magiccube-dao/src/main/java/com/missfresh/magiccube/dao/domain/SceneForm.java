package com.simon.magiccube.dao.domain;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/6/16 11:19 上午
 */
public class SceneForm {
    /** 场景id */
    private Integer id;

    /** 所属业务线 */
    private String productId;

    /** 场景名称 */
    private String sceneName;

    /** 场景描述 */
    private String sceneDes;

    private List<CaseRelation> runCaseFormList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneDes() {
        return sceneDes;
    }

    public void setSceneDes(String sceneDes) {
        this.sceneDes = sceneDes;
    }

    public List<CaseRelation> getRunCaseFormList() {
        return runCaseFormList;
    }

    public void setRunCaseFormList(List<CaseRelation> runCaseFormList) {
        this.runCaseFormList = runCaseFormList;
    }

    public static class CaseRelation {

        private Integer id;

        private Boolean delete;

        /**
         * 入参id
         */
        private Integer testDataId;

        /**
         * 用例id
         */
        private String caseId;

        /**
         * 入参格式（1-json，2-form）
         */
        private long inputFormat;

        /**
         * 测试数据内容
         */
        private String caseDataContent;

        /**
         * 出参id
         */
        private Integer assertDataId;

        /**
         * 断言描述*
         */
        private String assertDes;

        /**
         * 断言目标*
         */
        private String assertTarget;

        /**
         * 匹配规则（1-等于，2-不等于，3-包含，4-不包含，5-为空，6-不为空，7-size）*
         */
        private Integer matchRules;

        /**
         * 预期值*
         */
        private String expectedValue;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Boolean getDelete() {
            return delete;
        }

        public void setDelete(Boolean delete) {
            this.delete = delete;
        }

        public Integer getTestDataId() {
            return testDataId;
        }

        public void setTestDataId(Integer testDataId) {
            this.testDataId = testDataId;
        }

        public String getCaseId() {
            return caseId;
        }

        public void setCaseId(String caseId) {
            this.caseId = caseId;
        }

        public long getInputFormat() {
            return inputFormat;
        }

        public void setInputFormat(long inputFormat) {
            this.inputFormat = inputFormat;
        }

        public String getCaseDataContent() {
            return caseDataContent;
        }

        public void setCaseDataContent(String caseDataContent) {
            this.caseDataContent = caseDataContent;
        }

        public Integer getAssertDataId() {
            return assertDataId;
        }

        public void setAssertDataId(Integer assertDataId) {
            this.assertDataId = assertDataId;
        }

        public String getAssertDes() {
            return assertDes;
        }

        public void setAssertDes(String assertDes) {
            this.assertDes = assertDes;
        }

        public String getAssertTarget() {
            return assertTarget;
        }

        public void setAssertTarget(String assertTarget) {
            this.assertTarget = assertTarget;
        }

        public Integer getMatchRules() {
            return matchRules;
        }

        public void setMatchRules(Integer matchRules) {
            this.matchRules = matchRules;
        }

        public String getExpectedValue() {
            return expectedValue;
        }

        public void setExpectedValue(String expectedValue) {
            this.expectedValue = expectedValue;
        }
    }
}
