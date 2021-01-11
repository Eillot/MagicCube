package com.simon.magiccube.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.dao.domain.*;
import com.simon.magiccube.engine.*;
import com.simon.magiccube.engine.common.GenerateDubboCaseScript;
import com.simon.magiccube.engine.common.GenerateGetCaseScript;
import com.simon.magiccube.engine.common.GeneratePostCaseScript;
import com.simon.magiccube.engine.reporter.ReportWeb;
import com.simon.magiccube.engine.util.SpringBeanUtil;
import com.simon.magiccube.facade.support.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.*;

//import com.simon.magiccube.engine.common.GenerateGetCaseScript;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2020/3/22 6:31 PM
 * @File : RunCaseController
 * @Software: IntelliJ IDEA 2018.1.8
 */

@RestController
@CrossOrigin//解决跨域
@RequestMapping("/runSceneCase")
@Transactional
public class RunSceneCaseController {


    private static final Logger logger = LoggerFactory.getLogger(GeneratePostCaseScript.class);

    @Autowired
    private ITestDataEngine iTestDataEngine;

    @Autowired
    private IAssertDataEngine iAssertDataEngine;

    @Autowired
    private ICaseRunEngine iCaseRunEngine;

    @Autowired
    private IAssertRunEngine iAssertRunEngine;

    @Autowired
    private ICaseManageEngine iCaseManageEngine;

    /**
     * 用例执行统一入口
     *
     * @return
     */
    @RequestMapping(value = "/runSceneCaseStart", method = RequestMethod.POST)
    public CommonResult runSceneCaseStart(@RequestBody() Map<String, Object> params) {
        CommonResult commonResult = new CommonResult();
        CaseManage caseManage = new CaseManage();
        List caseIdList = new ArrayList();
        ReportWeb reportWeb = SpringBeanUtil.getBean(ReportWeb.class);

        try {
            XmlSuite xmlSuite = new XmlSuite();//新建一个XmlSuite对象
            xmlSuite.setName("xml-test-suite"); //指定xmlsuite 名称
            Map<String, String> parameters = new HashMap<>();//指定name 参数值
            List<XmlClass> classes = new ArrayList<>();//新建xmlclass 对象
            XmlTest xmlTest = new XmlTest(xmlSuite);//新建一个XmlTest对象
            TestNG testNG = new TestNG();

            logger.info("入参 params: {}", params);
            JSONObject jsonParams = new JSONObject(params);
            String serverName = jsonParams.getString("environmentId");
            String sip = jsonParams.getString("sip");
            JSONArray jsonArrayParams = jsonParams.getJSONArray("caseManageList");
            JSONObject serverJsonObject = jsonParams.getJSONObject("serverName");

            String assertRunId = "";
            String assertDes = "";
            String caseId = "";
            String caseName = "";
            String interfaceUrl = "";
            String testData = "";
            String dubboBaseDataParam = "";
            String assertId = "";
            String testDataId = "";
            String sceneId = "";

            if(jsonArrayParams != null){
                for(int i = 0;i<jsonArrayParams.size();i++){
                    JSONObject caseJsonObject = new JSONObject((Map<String, Object>) jsonArrayParams.get(i));
                    sceneId = String.valueOf(caseJsonObject.get("sceneId"));
                    caseId = String.valueOf(caseJsonObject.get("id"));
                    caseIdList.add(caseId);
                    caseName = String.valueOf(caseJsonObject.get("caseName"));
                    xmlTest.setName(caseName);
                    interfaceUrl = String.valueOf(caseJsonObject.get("interUrl"));
                    CommonResult testDataListRe = iTestDataEngine.TestDataQueryByCaseIdRe(caseId);
                    if (testDataListRe.getCode() == "-1") {
                        logger.info("用例执行失败，查询测试数据表异常:", commonResult.getData());
                        commonResult.setCode("-1");
                        commonResult.setMsg("用例执行失败，查询测试数据表异常");
                        commonResult.setStatus("400");
                        return commonResult;
                    }
                    TestData testDataList = (TestData) testDataListRe.getData();
                    testData = testDataList.getCaseDataContent();
                    dubboBaseDataParam = testDataList.getBaseDataParam();
                    testDataId = String.valueOf(testDataList.getId());

                    CommonResult assertDataListRe = iAssertDataEngine.AssertDataQueryBytestDataIdRe(testDataId);
                    if (assertDataListRe.getCode() == "-1") {
                        logger.info("用例执行失败，查询断言数据表异常:", commonResult.getData());
                        commonResult.setCode("-1");
                        commonResult.setMsg("用例执行失败，查询断言数据表异常");
                        commonResult.setStatus("400");
                        return commonResult;
                    }
                    JSONArray jsonArray = new JSONArray();
                    List<AssertData> assertDataList = (List<AssertData>) assertDataListRe.getData();
                    for(int j=0;j<assertDataList.size();j++){
                        assertDes = assertDataList.get(j).getAssertDes();
                        assertId = String.valueOf(assertDataList.get(j).getId());

                        //匹配规则
                        Integer matchRules = assertDataList.get(j).getMatchRules();
                        parameters.put("matchRules", String.valueOf(matchRules));

                        //断言目标
                        String assertTarget = assertDataList.get(j).getAssertTarget();
                        parameters.put("assertTarget", assertTarget);

                        //预期结果
                        String expectedValue = assertDataList.get(j).getExpectedValue();
                        if (expectedValue == null) {
                            parameters.put("expectedValue", "");
                        } else {
                            parameters.put("expectedValue", expectedValue);
                        }

                        //interfaceType=1为http接口
                        if (Integer.valueOf(String.valueOf(caseJsonObject.get("interfaceType"))) == 1) {
                            logger.info("http接口");
                            if (Integer.valueOf(String.valueOf(caseJsonObject.get("requestType"))) == 1) {
                                logger.info("get请求");
                                String packageName = GenerateGetCaseScript.class.getPackage().getName();
                                classes.add(new XmlClass(packageName + ".GenerateGetCaseScript"));

                                String path = sip + interfaceUrl + testData;
                                parameters.put("path", path);

                            } else {
                                logger.info("post请求");
                                String packageName = GeneratePostCaseScript.class.getPackage().getName();
                                classes.add(new XmlClass(packageName + ".GeneratePostCaseScript"));

                                String path = sip + interfaceUrl;
                                parameters.put("path", path);
                                parameters.put("paramsJson", testData);

                            }
                        } else if (Integer.valueOf(String.valueOf(caseJsonObject.get("interfaceType"))) == 2) {
                            logger.info("dubbo接口");
                            String packageName = GenerateDubboCaseScript.class.getPackage().getName();
                            classes.add(new XmlClass(packageName + ".GenerateDubboCaseScript"));

                            String addressAll = serverJsonObject.getString("zkAddress") ;
                            String[] address = addressAll.split(",");
                            parameters.put("address", address[0]);
                            String group = jsonArrayParams.getJSONObject(i).getString("dubboInterfaceGroup");
                            parameters.put("group", group);
                            String version = jsonArrayParams.getJSONObject(i).getString("dubboInterfaceVersion");
                            parameters.put("version", version);
                            String interfaceName = jsonArrayParams.getJSONObject(i).getString("interUrl");
                            parameters.put("interfaceName", interfaceName);
                            String methodName = jsonArrayParams.getJSONObject(i).getString("methodName");
                            parameters.put("methodName", methodName);

                            String paramTypes = jsonArrayParams.getJSONObject(i).getString("reqDubbo");
                            JSONObject jsonObjectDTO = new JSONObject();
                            jsonObjectDTO.put("dataType", jsonArrayParams.getJSONObject(i).getString("reqDubbo"));
                            jsonObjectDTO.put("dataName", JSON.parseObject(testData, HashMap.class));
                            JSONArray jsonArrayDubboBaseDataParam = new JSONArray();
                            jsonArrayDubboBaseDataParam.add(jsonObjectDTO);
                            if (dubboBaseDataParam!=null &&!dubboBaseDataParam.equals("null") && !dubboBaseDataParam.equals("")) {//如果基础入参有值，则组装
                                jsonArrayDubboBaseDataParam.addAll(JSON.parseArray(dubboBaseDataParam));
                            }
                            parameters.put("dubboParams",String.valueOf(jsonArrayDubboBaseDataParam));
                        }

                        //新增断言执行记录
                        AssertRun assertRun = new AssertRun();
                        assertRun.setAssertId(String.valueOf(assertDataList.get(j).getId()));
                        assertRun.setCreaterName("admin");
                        assertRun.setCreaterTime(new Date());
                        assertRun.setModifyName("admin");
                        assertRun.setModifyTime(new Date());
                        assertRun.setIsDelete(1);
                        CommonResult insertAssertRunResult = iAssertRunEngine.insertAssertRunRe(assertRun);
                        if (insertAssertRunResult.getCode() == "-1") {
                            logger.info("用例执行失败，插入断言执行表出现异常", commonResult.getData());
                            commonResult.setCode("-1");
                            commonResult.setMsg("用例执行失败，插入断言执行表出现异常");
                            commonResult.setStatus("400");
                            return commonResult;
                        }

                        assertRunId = String.valueOf(assertRun.getId());
                        parameters.put("assertRunId", assertRunId);

                        //新增用例执行记录
                        CaseRun caseRun = new CaseRun();
                        caseRun.setCaseId(caseId);
                        caseRun.setEnvironmentId(serverName);
                        caseRun.setCreaterName("admin");
                        caseRun.setCreaterTime(new Date());
                        caseRun.setModifyName("admin");
                        caseRun.setModifyTime(new Date());
                        caseRun.setIsDelete(1);
                        caseRun.setSceneId(sceneId);
                        CommonResult insertCaseRunResult = iCaseRunEngine.insertCaseRunRe(caseRun);
                        if (insertCaseRunResult.getCode() == "-1") {
                            logger.info("用例执行失败，插入用例执行表出现异常", commonResult.getData());
                            commonResult.setCode("-1");
                            commonResult.setMsg("用例执行失败，插入用例执行表出现异常");
                            commonResult.setStatus("400");
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            return commonResult;
                        }


                        String caseRunId = String.valueOf(caseRun.getId());
                        parameters.put("caseRunId", caseRunId);

                        //xmlsuite添加parameters
                        xmlSuite.setParameters(parameters);

                        xmlTest.setXmlClasses(classes);
                        List<XmlSuite> suites = new ArrayList<>();
                        suites.add(xmlSuite);
//                    TestNG testNG = new TestNG();
//                    testNG.setOutputDirectory("/data/app/report.html");//设置输出目录
                        testNG.setXmlSuites(suites);
                        logger.info("用例开始执行");
                        testNG.run();
                        logger.info("用例执行完成");

                        if(testNG.getStatus() == 1){//1为失败，0为成功
                            logger.info("用例执行失败，请检查接口url和入参");
                            commonResult.setCode("-1");
                            commonResult.setMsg("用例执行失败，请检查接口url和入参");
                            commonResult.setStatus("400");
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            return commonResult;

                        }else if(testNG.getStatus() == 0){
                            Integer statusCode = testNG.getStatus();
                            AssertRun assertRunList = iAssertRunEngine.selectAssertRunById(Integer.valueOf(assertRunId));
                            String assertResponseResult = assertRunList.getAssertResponseResult();
                            String assertResult = String.valueOf(assertRunList.getAssertRunResult());
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("assertResponseResult", assertResponseResult);
                            jsonObject.put("assertResult", assertResult);
                            jsonObject.put("assertDes", assertDes);
                            jsonArray.add(jsonObject);
                        }
                    }
                    String assertResultAndDes = String.valueOf(jsonArray);
                    CaseRun caseRunList = iCaseRunEngine.selectCaseRunByCaseIdNew(caseId);
                    String caseResponseResul = caseRunList.getResponseResult();
                    //组装用例执行结果
                    reportWeb.reportAssembleByCaseRun(caseId, caseName, interfaceUrl, serverName, testNG.getStatus(), testData, assertId, assertResultAndDes, caseResponseResul, testDataId);

                }

                //根据sceneId查询用例总数
                caseManage.setSceneId(sceneId);
                int caseTotal = iCaseManageEngine.selectCaseListPageTotal(caseManage);
                //根据sceneId和runResult查询成功用例数（最新记录）
                CaseRun caseRunS = new CaseRun();
                caseRunS.setSceneId(sceneId);
                caseRunS.setCaseIdList(caseIdList);
                caseRunS.setRunResult(1);
                caseRunS.setLimit(caseIdList.size());
                List caseRunList = iCaseRunEngine.selectCaseRunListByNew(caseRunS);
                int caseSuccessTotal;
                if (caseRunList.size() == 0) {//没有数据
                    System.out.println("@@@没数据:" + caseRunList);
                    caseSuccessTotal = 0;
                } else {
                    System.out.println("@@@有数据:" + caseRunList.size());
                    caseSuccessTotal = caseRunList.size();
                }
                //根据sceneId和runResult查询失败用例数（最新记录）
//                CaseRun caseRunF = new CaseRun();
//                caseRunF.setSceneId(sceneId);
//                caseRunF.setCaseIdList(caseIdList);
//                caseRunF.setRunResult(1);
//                caseRunF.setLimit(caseIdList.size());
//                List caseFailTotalList = iCaseRunEngine.selectCaseRunListByNew(caseRunF);
                int caseFailTotal = caseTotal-caseSuccessTotal;
                //如果存在失败的用例数，场景执行结果为失败
                int sceneRunResult;
                if (caseFailTotal == 0) {
                    sceneRunResult = 1;
                } else {
                    sceneRunResult = 2;
                }
                //组装场景执行结果
                reportWeb.reportAssembleBySceneRun(sceneId, serverName, caseTotal, caseSuccessTotal, caseFailTotal, sceneRunResult);
            }

            commonResult.setCode("0");
            commonResult.setMsg("用例执行完成");
            commonResult.setStatus("200");

        } catch (Exception e) {
            e.printStackTrace();

            logger.info("用例执行过程中出现异常，数据已回滚");
            commonResult.setMsg("用例执行过程中出现异常，数据已回滚");
            commonResult.setData(e);
            commonResult.setStatus("200");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return commonResult;

        } finally {

        }
        return commonResult;
    }


}