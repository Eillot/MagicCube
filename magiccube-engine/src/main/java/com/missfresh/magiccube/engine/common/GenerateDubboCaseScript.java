package com.simon.magiccube.engine.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.engine.common.assertResultCommon.AssertBase;
import com.simon.magiccube.engine.dto.request.DubboCaseRequest;
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
 * @Time : 2020/3/25 8:21 PM
 * @File : GenerateDubboCaseScript
 * @Software: IntelliJ IDEA 2018.1.8
 */
@Listeners({com.simon.magiccube.engine.reporter.GenerateReporter.class})
public class GenerateDubboCaseScript {

    private static final Logger logger = LoggerFactory.getLogger(GenerateDubboCaseScript.class);

//    @BeforeClass
//    public void before() {
//
//    }

    @Test
    @Parameters({"address", "group", "version", "interfaceName", "methodName", "dubboParams", "matchRules", "assertTarget", "expectedValue", "caseRunId", "assertRunId"})
    public void runCase(String address, String group, String version, String interfaceName, String methodName, String dubboParams, String matchRules, String assertTarget, String expectedValue, String caseRunId, String assertRunId) {
        try {
            DubboCaseRequest testReq = new DubboCaseRequest();

            testReq.setAddress(address);
            testReq.setGroup(group);
            testReq.setVersion(version);
            testReq.setInterfaceName(interfaceName);
            testReq.setMethodName(methodName);

            //从testData中解析出paramTypes和dubboParams
            JSONArray arrayTestData = JSONArray.parseArray(dubboParams);
//            JSONObject a = JSON.parseObject(dubboParams);

            String [] parameterTypes;
            Object [] parameterObjs;
            int parameterCount = 0;
            if (arrayTestData !=null) {
                parameterCount = arrayTestData.size();
            }
            //组装接口参数及参数类型
            parameterTypes = new String[parameterCount];
            parameterObjs= new Object[parameterCount];

            for (int i=0; i<arrayTestData.size(); i++) {
                parameterTypes[i] = arrayTestData.getJSONObject(i).getString("dataType");
                parameterObjs[i] = arrayTestData.getJSONObject(i).get("dataName");

            }

            //入参类型
            testReq.setParamTypes(parameterTypes);
            //入参内容
            testReq.setParamObjs(parameterObjs);

            JSONObject response = JSONObject.parseObject(RequestBase.dubboCaseInvoke(testReq));
            logger.info("接口返回结果：" + response.toString());


            //实际结果
            String responseValue = String.valueOf(response.get(assertTarget));

            //断言
            AssertBase assertBase = SpringBeanUtil.getBean(AssertBase.class);
            assertBase.assertMoBase(matchRules, responseValue, expectedValue, caseRunId, response.toString(), assertRunId);

        } catch (Exception e) {
            logger.error("dubbo用例执行失败",e);
        }
    }

}
