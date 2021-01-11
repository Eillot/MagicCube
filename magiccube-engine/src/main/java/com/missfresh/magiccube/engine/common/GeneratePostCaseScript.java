package com.simon.magiccube.engine.common;

import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.engine.common.assertResultCommon.AssertBase;
import com.simon.magiccube.engine.util.SpringBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2020/3/25 2:46 PM
 * @File : GeneratePostCaseScript
 * @Software: IntelliJ IDEA 2018.1.8
 */
@Listeners({com.simon.magiccube.engine.reporter.GenerateReporter.class})
public class GeneratePostCaseScript<T> {

    private static final Logger logger = LoggerFactory.getLogger(GeneratePostCaseScript.class);

//    @BeforeClass
//    public void before() {
//
//    }


    @Test
    @Parameters({"path","paramsJson","matchRules","assertTarget","expectedValue","caseRunId","assertRunId"})
    public void runCase(String path,String paramsJson,String matchRules , String assertTarget , String expectedValue,String caseRunId,String assertRunId) {

        JSONObject response = JSONObject.parseObject(RequestBase.doPost(path,paramsJson));
        System.out.println("接口返回："+ response.toString());

        //实际结果
        String responseValue = String.valueOf(response.get(assertTarget));

        //断言
        AssertBase assertBase = SpringBeanUtil.getBean(AssertBase.class);
        assertBase.assertMoBase(matchRules,responseValue,expectedValue,caseRunId,response.toString(),assertRunId);

    }
}
