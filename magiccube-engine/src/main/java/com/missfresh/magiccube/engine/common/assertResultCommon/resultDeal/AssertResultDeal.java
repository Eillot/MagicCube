package com.simon.magiccube.engine.common.assertResultCommon.resultDeal;

import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.engine.common.assertResultCommon.MatchRulesCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.testng.Assert;

/**
 * <p>断言结果处理</p>
 * 该方法没有用到
 *
 * @author ：simon
 * @date ：Created in 2020-05-12 11:31
 */
@Component
public class AssertResultDeal {

    private static final Logger logger = LoggerFactory.getLogger(AssertResultDeal.class);

    private static final String ASSERT_RESULT = "1";

    @MatchRulesCode(typeCode = 1)
    public String assertNotNull(String jsonres, String expectedValue) {
        logger.debug("断言内容非空");
        Assert.assertNotEquals(jsonres, expectedValue);
        return ASSERT_RESULT;
    }

    @MatchRulesCode(typeCode = 2)
    public String assertContentEquals(String jsonres, String expectedValue) {
        logger.debug("断言内容相等");
        Assert.assertEquals(jsonres,expectedValue);
        return ASSERT_RESULT;
    }

    @MatchRulesCode(typeCode = 3)
    public String assertLengthEquals(String jsonres, String expectedValue) {
        logger.debug("断言长度相等");
        Assert.assertEquals(jsonres.length(),expectedValue.length());
        return ASSERT_RESULT;
    }

    @MatchRulesCode(typeCode = 4)
    public String assertJsonEquals(String jsonres, String expectedValue) {
        logger.debug("断言json字符串相等");
        Assert.assertEquals(jsonres,expectedValue);
        return ASSERT_RESULT;
    }

    @MatchRulesCode(typeCode = -1)
    public String assertDefault(String jsonres, String expectedValue) {
        logger.debug("断言失败");
        String assertResult="3";
        return assertResult;
    }


}
