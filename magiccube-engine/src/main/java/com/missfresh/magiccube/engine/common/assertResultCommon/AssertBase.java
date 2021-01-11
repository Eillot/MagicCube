package com.simon.magiccube.engine.common.assertResultCommon;

import com.simon.magiccube.dao.domain.AssertRun;
import com.simon.magiccube.dao.domain.CaseRun;
import com.simon.magiccube.engine.IAssertRunEngine;
import com.simon.magiccube.engine.ICaseRunEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.Date;


/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/6/7 5:59 下午
 */
@Component
public class AssertBase {
    private static final Logger logger = LoggerFactory.getLogger(AssertBase.class);

    @Autowired
    private ICaseRunEngine iCaseRunEngine;

    @Autowired
    private IAssertRunEngine iAssertRunEngine;

    public void assertMoBase(String matchRules, String responseValue, String expectedValue, String caseRunId, Object caseRunResponseResult, String assertRunId) {
        //匹配规则（1-等于，2-不等于，3-包含，4-不包含，5-为空，6-不为空，7-size）
        AssertRun assertRun = new AssertRun();
        //根据id更新断言执行记录
        assertRun.setId(Integer.valueOf(assertRunId));
        assertRun.setModifyTime(new Date());
        CaseRun caseRun = new CaseRun();
        //根据id更新用例执行记录
        caseRun.setId(Integer.valueOf(caseRunId));
        caseRun.setAssertRunId(assertRunId);
        caseRun.setModifyTime(new Date());
        if(matchRules.equals("1")){
            logger.info("断言匹配规则：等于");
            try {
                Assert.assertEquals(responseValue, expectedValue);
                logger.info("断言结果：成功");
                //根据id更新断言执行记录
                assertRun.setAssertRunResult(1);
                assertRun.setAssertResponseResult("responseValue：" + responseValue + "；expectedValue：" + expectedValue);
                assertRun.setIsDelete(1);
                //根据id更新用例执行记录
                caseRun.setRunResult(1);
                caseRun.setIsDelete(1);
                caseRun.setResponseResult(caseRunResponseResult.toString());

            } catch (Throwable throwable) {
                logger.info("Assert执行失败：" + throwable.getMessage(), throwable);

//                String assertResponseResult = throwable.getMessage(), throwable;
                logger.info("断言结果：" + throwable);
                //根据id更新断言执行记录
                assertRun.setAssertRunResult(2);
                assertRun.setAssertResponseResult(String.valueOf(throwable));
                assertRun.setIsDelete(1);
                //根据id更新用例执行记录
                caseRun.setRunResult(2);
                caseRun.setResponseResult(caseRunResponseResult.toString());
                caseRun.setIsDelete(1);
            }

        }else if(matchRules.equals("2")){
            logger.info("断言匹配规则：不等于");
            try {
                Assert.assertNotEquals(responseValue, expectedValue);
                logger.info("断言结果：成功");
                //根据id更新断言执行记录
                assertRun.setAssertRunResult(1);
                assertRun.setAssertResponseResult("responseValue：" + responseValue + "；expectedValue：" + expectedValue);
                assertRun.setIsDelete(1);
                //根据id更新用例执行记录
                caseRun.setRunResult(1);
                caseRun.setIsDelete(1);
                caseRun.setResponseResult(caseRunResponseResult.toString());

            } catch (Throwable throwable) {
                logger.info("Assert执行失败：" + throwable.getMessage(), throwable);

//                String assertResponseResult = throwable.getMessage(), throwable;
                logger.info("断言结果：" + throwable);
                //根据id更新断言执行记录
                assertRun.setAssertRunResult(2);
                assertRun.setAssertResponseResult(String.valueOf(throwable));
                assertRun.setIsDelete(1);
                //根据id更新用例执行记录
                caseRun.setRunResult(2);
                caseRun.setResponseResult(caseRunResponseResult.toString());
                caseRun.setIsDelete(1);
            }

        }else if(matchRules.equals("3")){
            logger.info("断言匹配规则：包含");

        }else if(matchRules.equals("4")){
            logger.info("断言匹配规则：不包含");

        }else if(matchRules.equals("5")){
            logger.info("断言匹配规则：为空");
            try {
                Assert.assertNull(caseRunResponseResult);
                logger.info("断言结果：成功");
                //根据id更新断言执行记录
                assertRun.setAssertRunResult(1);
                assertRun.setAssertResponseResult("responseValue：" + responseValue + "；expectedValue：" + expectedValue);
                assertRun.setIsDelete(1);
                //根据id更新用例执行记录
                caseRun.setRunResult(1);
                caseRun.setIsDelete(1);
                caseRun.setResponseResult(caseRunResponseResult.toString());

            } catch (Throwable throwable) {
                logger.info("Assert执行失败：" + throwable.getMessage(), throwable);

//                String assertResponseResult = throwable.getMessage(), throwable;
                logger.info("断言结果：" + throwable);
                //根据id更新断言执行记录
                assertRun.setAssertRunResult(2);
                assertRun.setAssertResponseResult(String.valueOf(throwable));
                assertRun.setIsDelete(1);
                //根据id更新用例执行记录
                caseRun.setRunResult(2);
                caseRun.setResponseResult(caseRunResponseResult.toString());
                caseRun.setIsDelete(1);
            }

        }else if(matchRules.equals("6")){
            logger.info("断言匹配规则：不为空");
            try {
                Assert.assertNotNull(caseRunResponseResult);
                logger.info("断言结果：成功");
                //根据id更新断言执行记录
                assertRun.setAssertRunResult(1);
                assertRun.setAssertResponseResult("responseValue：" + responseValue + "；expectedValue：" + expectedValue);
                assertRun.setIsDelete(1);
                //根据id更新用例执行记录
                caseRun.setRunResult(1);
                caseRun.setIsDelete(1);
                caseRun.setResponseResult(caseRunResponseResult.toString());

            } catch (Throwable throwable) {
                logger.info("Assert执行失败：" + throwable.getMessage(), throwable);

//                String assertResponseResult = throwable.getMessage(), throwable;
                logger.info("断言结果：" + throwable);
                //根据id更新断言执行记录
                assertRun.setAssertRunResult(2);
                assertRun.setAssertResponseResult(String.valueOf(throwable));
                assertRun.setIsDelete(1);
                //根据id更新用例执行记录
                caseRun.setRunResult(2);
                caseRun.setResponseResult(caseRunResponseResult.toString());
                caseRun.setIsDelete(1);
            }

        }else if(matchRules.equals("7")){
            logger.info("断言匹配规则：size");

        }

        try {
            iAssertRunEngine.updateAssertRunByIdSelective(assertRun);
            logger.info("更新assert_run表成功");
            iCaseRunEngine.updateCaseRunByIdSelective(caseRun);
            logger.info("更新case_run表成功");
        } catch (Exception e) {
            logger.info("更新数据失败：" + e.getMessage(), e);
        }

    }
}
