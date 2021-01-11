package com.simon.magiccube.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.dao.domain.*;
import com.simon.magiccube.engine.*;
import com.simon.magiccube.engine.common.ResourceManagement;
import com.simon.magiccube.facade.support.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @Author : huorh  @Time : 2020/05/21 17:12 PM @Content : 场景管理接口列表 */
@RestController
@CrossOrigin//解决跨域
@RequestMapping("/scenes")
@Transactional(propagation = Propagation.SUPPORTS)
public class SceneManageController {
    private static final Logger logger = LoggerFactory.getLogger(SceneManageController.class);


    @Autowired
    private SceneManageService sceneManageService;
    @Autowired
    private ICaseManageEngine iCaseManageEngine;
    @Autowired
    private IPreOrPosActionEngine iPreOrPosActionEngine;
    @Autowired
    private ITestDataEngine iTestDataEngine;
    @Autowired
    private IAssertDataEngine iAssertDataEngine;

    /**
     * 查询场景
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult list(@RequestBody SceneManage sceneManag) {
        CommonResult commonResult = new CommonResult();
        sceneManag.setCreaterName(sceneManag.getCreaterName());

        CommonResult sceneList = sceneManageService.sceneList(sceneManag);
        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(sceneList);
        commonResult.setStatus("200");
        return commonResult;
    }


    /**
     * 新增场景页面
     *
     * @param productId
     * @param appId
     * @param preProductId
     * @param posProductId
     * @return
     */
    @GetMapping(value = "/addPage")
    public CommonResult sceneAddPage(@RequestBody() @RequestParam(required = false) String productId,
                                     @RequestParam(required = false) String appId,
                                     @RequestParam(required = false) String caseId,
                                     @RequestParam(required = false) String preProductId,
                                     @RequestParam(required = false) String posProductId) {
        CommonResult commonResult = new CommonResult();
        HashMap hashMap = new HashMap();
        PreOrPosAction preActionSet = new PreOrPosAction();
        PreOrPosAction posActionSet = new PreOrPosAction();

        if (productId != null && !productId.equals("")) {
            //根据业务线id查询应用
            JSONObject appList = JSONObject.parseObject(ResourceManagement.queryListResAppInfo(productId));
            logger.info("根据业务线id查询应用: " + appList);
            hashMap.put("appList", appList);

        }
        if (appId != null && !appId.equals("")) {
            //根据应用id查询用例
            CaseManage caseManage = new CaseManage();
            caseManage.setAppId(appId);
            caseManage.setIsDelete(1);
            caseManage.setSceneId("0");
            List<CaseManage> caseManageList = iCaseManageEngine.caseListQueryAllForScene(caseManage);
            logger.info("根据应用id查询用例: " + caseManageList);
            hashMap.put("caseManageList", caseManageList);
        }
        if (appId != null && !appId.equals("")) {
            //根据应用id查询接口
            JSONObject interfaceList = JSONObject
                    .parseObject(ResourceManagement.queryListResInterfaceInfo(appId));
            logger.info("根据应用id查询接口: " + interfaceList);
            hashMap.put("interfaceList", interfaceList);
        }


        if (preProductId != null && !preProductId.equals("")) {
            //根据前置业务线id查询前置动作
            preActionSet.setProductId(preProductId);
            List<PreOrPosAction> preActionList = iPreOrPosActionEngine
                    .PreOrPosActionQueryAll(preActionSet);
            logger.info("根据前置业务线id查询前置动作: " + preActionList);
            hashMap.put("preActionList", preActionList);
        }
        if (posProductId != null && !posProductId.equals("")) {
            //根据后置业务线id查询后置动作
            posActionSet.setProductId(posProductId);
            List<PreOrPosAction> posActionList = iPreOrPosActionEngine
                    .PreOrPosActionQueryAll(posActionSet);
            logger.info("根据后置业务线id查询后置动作: " + posActionList);
            hashMap.put("posActionList", posActionList);
        }


        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(hashMap);
        commonResult.setStatus("200");
        return commonResult;
    }


    /**
     * 新增场景
     */
    @PostMapping("/add")
    public CommonResult add(@RequestBody() Map<String, Object> params) {
        CommonResult commonResult = new CommonResult();

        logger.info("入参 params: {}", params);
        JSONObject jsonParams = new JSONObject(params);
        JSONArray jsonArrayParams = jsonParams.getJSONArray("requests");

        JSONObject jsonObjectCaseListFor0 = new JSONObject((Map<String, Object>) jsonArrayParams.getJSONObject(0).get("caseList"));

        //新增场景数据
        SceneManage sceneManage = new SceneManage();
        sceneManage.setProductId(jsonObjectCaseListFor0.getString("productId"));
        sceneManage.setAppId(jsonObjectCaseListFor0.getString("appId"));
        sceneManage.setSceneName(jsonArrayParams.getJSONObject(0).getString("sceneName"));
        sceneManage.setSceneDes(jsonArrayParams.getJSONObject(0).getString("sceneDes"));
        sceneManage.setCreaterName("admin");
        sceneManage.setCreaterTime(new Date());
        sceneManage.setModifyName("admin");
        sceneManage.setModifyTime(new Date());
        int sceneInsert = sceneManageService.SceneManageInsert(sceneManage);
        logger.info("新增场景数据:" + sceneInsert);
        String sceneId = String.valueOf(sceneManage.getId());

        if (jsonArrayParams != null) {
            for (int i = 0; i < jsonArrayParams.size(); i++) {
                JSONObject jsonObjectCaseList = new JSONObject((Map<String, Object>) jsonArrayParams.getJSONObject(i).get("caseList"));
                System.out.println(jsonObjectCaseList);

                //从caseList中获取用例并保存
                //新增用例表数据
                CaseManage caseManage = new CaseManage();
                caseManage.setCaseName(jsonObjectCaseList.getString("caseName"));
                caseManage.setCaseDes(jsonObjectCaseList.getString("caseDes"));
                caseManage.setPriority(jsonObjectCaseList.getString("priority"));
                caseManage.setProductId(jsonObjectCaseList.getString("productId"));
                caseManage.setAppId(jsonObjectCaseList.getString("appId"));
                caseManage.setInterfaceType(jsonObjectCaseList.getInteger("interfaceType"));
                caseManage.setInterfaceId(jsonObjectCaseList.getString("interfaceId"));
                caseManage.setRequestType(jsonObjectCaseList.getInteger("requestType"));
                caseManage.setPreActionId(jsonObjectCaseList.getString("preActionId"));
                caseManage.setPosActionId(jsonObjectCaseList.getString("posActionId"));
                caseManage.setSceneId(String.valueOf(sceneManage.getId()));
                caseManage.setCreaterTime(new Date());
                caseManage.setCreaterName("admin");
                caseManage.setModifyTime(new Date());
                caseManage.setModifyName("admin");
                caseManage.setIsDelete(1);
                int caseInserter = iCaseManageEngine.caseManageInsert(caseManage);
                logger.info("新增用例数据:" + caseInserter);

                //获取测试数据
                JSONArray jsonObjectTestDataList = jsonObjectCaseList.getJSONArray("caseManageForTestDataList");
                //caseDataContent数据
                JSONObject jsonObjectCaseDataContent = jsonArrayParams.getJSONObject(i).getJSONObject("caseDataContent");
//                JSONObject jsonObjectCaseDataContent = new JSONObject();
//                if(jsonArrayParams.getJSONObject(i).getInteger("type") == 0){//get
//
//                }else if(jsonArrayParams.getJSONObject(i).getInteger("type") == 1){//json
//                    jsonObjectCaseDataContent = jsonArrayParams.getJSONObject(i).getJSONObject("caseDataContent");
//                }else if(jsonArrayParams.getJSONObject(i).getInteger("type") == 2){//form
//                    JSONArray jsonArray = jsonArrayParams.getJSONObject(i).getJSONArray("caseDataContent");
//                    jsonObjectCaseDataContent = JSON.parseObject(String.valueOf(jsonArray));
//                }

                //dubboDataParam数据
                JSONArray jsonObjectDubboDataParam = jsonArrayParams.getJSONObject(i).getJSONArray("dubboDataParam");

                //新增入参表数据
                TestData testData = new TestData();
                String caseId = String.valueOf(caseManage.getId());
                testData.setCaseId(caseId);
                Long inputFormat = jsonObjectTestDataList.getJSONObject(0).getLong("inputFormat");
                if (inputFormat != 0) {//http接口
                    testData.setInputFormat(inputFormat);
                    JSONArray caseDataContentJson = jsonObjectTestDataList.getJSONObject(0).getJSONArray("caseDataContentList");
                    if (null != caseDataContentJson) {
                        JSONArray caseDataContentJsonAll = new JSONArray();
                        for (int j = 0; j < caseDataContentJson.size(); j++) {
                            System.out.println("@@@:" + caseDataContentJson.get(j));
                            caseDataContentJsonAll.add(caseDataContentJson.get(j));
                        }
                        System.out.println("@@@:" + caseDataContentJsonAll);
                        testData.setCaseDataContent(String.valueOf(caseDataContentJsonAll));
                    } else {
                        String caseDataString = jsonObjectTestDataList.getJSONObject(0).getString("caseDataContent");
                        testData.setCaseDataContent(caseDataString);
                    }
                } else {//dubbo接口
                    testData.setCaseDataContent(String.valueOf(jsonObjectCaseDataContent));
                    if (jsonObjectDubboDataParam != null) {
                        String dataType = String.valueOf(jsonObjectDubboDataParam.getJSONObject(0).get("dataType"));
                        if (!dataType.equals("dataTypeNull")) {
                            testData.setBaseDataParam(String.valueOf(jsonObjectDubboDataParam));
                        }
                    }
                }

                testData.setCreaterName("admin");
                testData.setCreaterTime(new Date());
                testData.setIsDelete(1);
                testData.setSceneId(sceneId);
                int testDataInserter = iTestDataEngine.TestDataInsert(testData);
                logger.info("新增入参数据：" + testDataInserter);
//

                //新增断言表数据
                AssertData assertData = new AssertData();
                JSONArray jsonObjectAssertDataList = jsonArrayParams.getJSONObject(i).getJSONArray("assertDatalist");
                String testDataId = String.valueOf(testData.getId());
                assertData.setTestDataId(testDataId);
                for (int m = 0; m < jsonObjectAssertDataList.size(); m++) {
                    if (null != jsonObjectAssertDataList.getJSONObject(m).getString("assertDes")) {
                        assertData.setAssertDes(jsonObjectAssertDataList.getJSONObject(m).getString("assertDes"));
                    }
                    if (null != jsonObjectAssertDataList.getJSONObject(m).getString("assertTarget")) {
                        assertData.setAssertTarget(jsonObjectAssertDataList.getJSONObject(m).getString("assertTarget"));
                    }
                    if (null != jsonObjectAssertDataList.getJSONObject(m).getInteger("matchRules")) {
                        assertData.setMatchRules(jsonObjectAssertDataList.getJSONObject(m).getInteger("matchRules"));
                    }
                    if (null != jsonObjectAssertDataList.getJSONObject(m).getString("expectedValue")) {
                        assertData.setExpectedValue(jsonObjectAssertDataList.getJSONObject(m).getString("expectedValue"));
                    }
                    assertData.setCreaterName("admin");
                    assertData.setCreaterTime(new Date());
                    assertData.setIsDelete(1);
                    assertData.setSceneId(sceneId);
                    int assertDataInserter = iAssertDataEngine.AssertDataInsert(assertData);
                    logger.info("新增断言数据：" + assertDataInserter);
                }
            }
        }

        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setStatus("200");
        return commonResult;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public CommonResult sceneModify(@RequestBody() Map<String, Object> params) {
        CommonResult commonResult = new CommonResult();
        CaseManage caseManage = new CaseManage();
        TestData testData = new TestData();
        AssertData assertData = new AssertData();

        logger.info("入参 params: {}", params);
        JSONObject jsonParams = new JSONObject(params);
        Integer sceneId = jsonParams.getInteger("sceneId");
        String appId = jsonParams.getString("appId");
        String productId = jsonParams.getString("productId");
        String sceneName = jsonParams.getString("sceneName");
        String sceneDes = jsonParams.getString("sceneDes");
        JSONArray requestsJsonArray = jsonParams.getJSONArray("requests");

        //根据场景id更新场景表
        SceneManage sceneManage = new SceneManage();
        sceneManage.setId(sceneId);
        sceneManage.setProductId(productId);
        sceneManage.setAppId(appId);
        sceneManage.setSceneName(sceneName);
        sceneManage.setSceneDes(sceneDes);
        sceneManage.setModifyName("admin");
        sceneManage.setModifyTime(new Date());
        sceneManage.setIsDelete(1);
        int sceneUpdate = sceneManageService.SceneManageUpdate(sceneManage);
        logger.info("新增场景数据:" + sceneUpdate);


        for (int i = 0; i < requestsJsonArray.size(); i++) {
            String caseId = String.valueOf(requestsJsonArray.getJSONObject(i).get("id"));
            String caseName = String.valueOf(requestsJsonArray.getJSONObject(i).get("caseName"));
            String caseDes = String.valueOf(requestsJsonArray.getJSONObject(i).get("caseDes"));
            String priority = String.valueOf(requestsJsonArray.getJSONObject(i).get("priority"));
            String interfaceType = String.valueOf(requestsJsonArray.getJSONObject(i).get("interfaceType"));
            String interfaceId = String.valueOf(requestsJsonArray.getJSONObject(i).get("interfaceId"));
            String requestType = String.valueOf(requestsJsonArray.getJSONObject(i).get("requestType"));
            String preActionId = String.valueOf(requestsJsonArray.getJSONObject(i).get("preActionId"));
            String posActionId = String.valueOf(requestsJsonArray.getJSONObject(i).get("posActionId"));
            JSONArray caseManageForTestDataListArray = requestsJsonArray.getJSONObject(i).getJSONArray("caseManageForTestDataList");
            Integer dataId = caseManageForTestDataListArray.getJSONObject(0).getInteger("id");
            Integer inputFormat = caseManageForTestDataListArray.getJSONObject(0).getInteger("inputFormat");
            JSONObject jsonObjectCaseDataContent = requestsJsonArray.getJSONObject(i).getJSONObject("caseDataContent");//caseDataContent数据
            JSONObject jsonObjectCaseDataContentDubbo = requestsJsonArray.getJSONObject(i).getJSONObject("dubboCaseDataContent");//caseDataContent数据
            JSONArray jsonObjectDubboDataParam = requestsJsonArray.getJSONObject(i).getJSONArray("dubboDataParam");//dubboDataParam数
            JSONArray caseManageForAssertDataListArray = requestsJsonArray.getJSONObject(i).getJSONArray("caseManageForAssertDataList");//old
            JSONArray jsonObjectAssertDataList = requestsJsonArray.getJSONObject(i).getJSONArray("assertDatalist");//new
            String testDataId = "";
            List caseList = iCaseManageEngine.selectCaseById(Integer.valueOf(caseId));//根据用例id查询数据
            //根据dataId和sceneId查询测试数据
            testData.setId(Long.valueOf(dataId));
            testData.setSceneId(String.valueOf(sceneId));
            List testDataList = iTestDataEngine.TestDataQueryListById(testData);
//            List assertList = iAssertDataEngine.AssertDataQueryById(assertId);


            //遍历用例id，如果数据库中存在，则更新，如果不存在，则新增
            if (caseList.size() == 0) {//如果等于0，则数据库中不存在，执行新增用例表
                //新增用例表数据
                caseManage.setCaseName(caseName);
                if (!caseDes.equals("null") && !caseDes.equals("")) {
                    caseManage.setCaseDes(caseDes);
                }
                if (!priority.equals("null") && !priority.equals("")) {
                    caseManage.setPriority(priority);
                }
                caseManage.setProductId(productId);
                caseManage.setAppId(appId);
                caseManage.setInterfaceType(Integer.valueOf(interfaceType));
                caseManage.setInterfaceId(interfaceId);
                if (!requestType.equals("null") && !requestType.equals("")) {
                    caseManage.setRequestType(Integer.valueOf(requestType));
                }
                if (!preActionId.equals("null") && !preActionId.equals("")) {
                    caseManage.setPreActionId(preActionId);
                }
                if (!posActionId.equals("null") && !posActionId.equals("")) {
                    caseManage.setPosActionId(posActionId);
                }
                caseManage.setSceneId(String.valueOf(sceneId));
                caseManage.setCreaterTime(new Date());
                caseManage.setCreaterName("admin");
                caseManage.setModifyTime(new Date());
                caseManage.setModifyName("admin");
                caseManage.setIsDelete(1);
                int caseInserter = iCaseManageEngine.caseManageInsert(caseManage);
                logger.info("新增用例数据:" + caseInserter);
            } else {
                //更新用例表数据
                caseManage.setId(Integer.valueOf(caseId));
                caseManage.setCaseName(caseName);
                if (!caseDes.equals("null") && !caseDes.equals("")) {
                    caseManage.setCaseDes(caseDes);
                }
                if (!priority.equals("null") && !priority.equals("")) {
                    caseManage.setPriority(priority);
                }
                caseManage.setProductId(productId);
                caseManage.setAppId(appId);
                caseManage.setInterfaceType(Integer.valueOf(interfaceType));
                caseManage.setInterfaceId(interfaceId);
                if (!requestType.equals("null") && !requestType.equals("")) {
                    caseManage.setRequestType(Integer.valueOf(requestType));
                }
                if (!preActionId.equals("null") && !preActionId.equals("")) {
                    caseManage.setPreActionId(preActionId);
                }
                if (!posActionId.equals("null") && !posActionId.equals("")) {
                    caseManage.setPosActionId(posActionId);
                }
                caseManage.setSceneId(String.valueOf(sceneId));
                caseManage.setCreaterTime(new Date());
                caseManage.setCreaterName("admin");
                caseManage.setModifyTime(new Date());
                caseManage.setModifyName("admin");
                caseManage.setIsDelete(1);
                int caseUpdate = iCaseManageEngine.CaseManageUpdate(caseManage);
                logger.info("更新用例数据:" + caseUpdate);

            }

            //遍历测试数据id，如果数据库中存在，则更新，如果不存在，则新增
            if (testDataList.size() == 0) {//如果等于0，则数据库中不存在，执行新增数据表
                //新增入参表数据
                testData.setCaseId(caseId);
                if (inputFormat != 0) {//http接口
                    testData.setInputFormat(inputFormat);
                    testData.setCaseDataContent(String.valueOf(jsonObjectCaseDataContent));
                } else {//dubbo接口
                    testData.setCaseDataContent(String.valueOf(jsonObjectCaseDataContentDubbo));
                    if (jsonObjectDubboDataParam != null) {
                        String dataType = String.valueOf(jsonObjectDubboDataParam.getJSONObject(0).get("dataType"));
                        if (!dataType.equals("dataTypeNull")) {
                            testData.setBaseDataParam(String.valueOf(jsonObjectDubboDataParam));
                        }
                    }

                }
                testData.setCreaterName("admin");
                testData.setCreaterTime(new Date());
                testData.setIsDelete(1);
                testData.setSceneId(String.valueOf(sceneId));
                int testDataInserter = iTestDataEngine.TestDataInsert(testData);
                logger.info("新增入参数据：" + testDataInserter);
                testDataId = String.valueOf(testData.getId());
            } else {
                //更新入参表数据
                testData.setId(Long.valueOf(dataId));
                testData.setCaseId(caseId);
                if (inputFormat != 0) {
                    testData.setInputFormat(inputFormat);
                    testData.setCaseDataContent(String.valueOf(jsonObjectCaseDataContent));
                } else {
                    testData.setCaseDataContent(String.valueOf(jsonObjectCaseDataContentDubbo));
                    if (jsonObjectDubboDataParam != null) {
                        String dataType = String.valueOf(jsonObjectDubboDataParam.getJSONObject(0).get("dataType"));
                        if (!dataType.equals("dataTypeNull")) {
                            testData.setBaseDataParam(String.valueOf(jsonObjectDubboDataParam));
                        }
                    }
                }
                testData.setModifyName("admin");
                testData.setModifyTime(new Date());
                testData.setIsDelete(1);
                testData.setSceneId(String.valueOf(sceneId));
                int testDataUpdate = iTestDataEngine.TestDataUpdate(testData);
                logger.info("更新入参数据：" + testDataUpdate);
                testDataId = String.valueOf(testData.getId());
            }

            //根据断言id和场景id，查询数据库是否存在此数据
            for (int p = 0; p < jsonObjectAssertDataList.size(); p++) {
                Integer assertId = jsonObjectAssertDataList.getJSONObject(p).getInteger("assertId");
                assertData.setId(assertId);
                assertData.setSceneId(String.valueOf(sceneId));
                List assertList = iAssertDataEngine.AssertDataQueryById(assertData);
                if (assertList.size() == 0) {
                    logger.info("数据库没有此值，新增数据");
                    assertData.setTestDataId(testDataId);
                    if (null != jsonObjectAssertDataList.getJSONObject(p).getString("assertDes")) {
                        assertData.setAssertDes(jsonObjectAssertDataList.getJSONObject(p).getString("assertDes"));
                    }
                    if (null != jsonObjectAssertDataList.getJSONObject(p).getString("assertTarget")) {
                        assertData.setAssertTarget(jsonObjectAssertDataList.getJSONObject(p).getString("assertTarget"));
                    }
                    if (null != jsonObjectAssertDataList.getJSONObject(p).getInteger("matchRules")) {
                        assertData.setMatchRules(jsonObjectAssertDataList.getJSONObject(p).getInteger("matchRules"));
                    }
                    if (null != jsonObjectAssertDataList.getJSONObject(p).getString("expectedValue")) {
                        assertData.setExpectedValue(jsonObjectAssertDataList.getJSONObject(p).getString("expectedValue"));
                    }
                    assertData.setCreaterName("admin");
                    assertData.setCreaterTime(new Date());
                    assertData.setIsDelete(1);
                    assertData.setSceneId(String.valueOf(sceneId));
                    int assertDataInserter = iAssertDataEngine.AssertDataInsert(assertData);
                    logger.info("新增断言数据成功：" + assertDataInserter);
                }
            }

//            遍历old数据中的id，是否在new中有，有更新，没有删除
            for (int m = 0; m < caseManageForAssertDataListArray.size(); m++) {//遍历old数据
                Integer oldAssertId = caseManageForAssertDataListArray.getJSONObject(m).getInteger("id");
                int k = 0;
                for (int o = 0; o < jsonObjectAssertDataList.size(); o++) {//遍历new数据
                    if (jsonObjectAssertDataList.getJSONObject(o).containsValue(oldAssertId)) {//有此值，更新
                        k = 1;
                        Integer newAssertId = jsonObjectAssertDataList.getJSONObject(o).getInteger("assertId");
                        assertData.setId(newAssertId);
                        assertData.setTestDataId(testDataId);
                        if (null != jsonObjectAssertDataList.getJSONObject(o).getString("assertDes")) {
                            assertData.setAssertDes(jsonObjectAssertDataList.getJSONObject(o).getString("assertDes"));
                        }
                        if (null != jsonObjectAssertDataList.getJSONObject(o).getString("assertTarget")) {
                            assertData.setAssertTarget(jsonObjectAssertDataList.getJSONObject(o).getString("assertTarget"));
                        }
                        if (null != jsonObjectAssertDataList.getJSONObject(o).getInteger("matchRules")) {
                            assertData.setMatchRules(jsonObjectAssertDataList.getJSONObject(o).getInteger("matchRules"));
                        }
                        if (null != jsonObjectAssertDataList.getJSONObject(o).getString("expectedValue")) {
                            assertData.setExpectedValue(jsonObjectAssertDataList.getJSONObject(o).getString("expectedValue"));
                        }
                        assertData.setModifyName("admin");
                        assertData.setModifyTime(new Date());
                        assertData.setIsDelete(1);
                        assertData.setSceneId(String.valueOf(sceneId));
                        int assertDataUpdate = iAssertDataEngine.AssertDataUpdate(assertData);
                        logger.info("更新断言数据：" + assertDataUpdate);
                        break;
                    }
                }
                if (k == 0) {
                    assertData.setId(oldAssertId);
                    assertData.setIsDelete(0);
                    int assertDataDelect = iAssertDataEngine.AssertDataUpdate(assertData);
                    logger.info("删除断言数据：" + assertDataDelect);
                }
            }
        }
        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setStatus("200");
        return commonResult;
    }






    /**
     * 场景执行传参
     * @param params
     * @return
     */
    @PostMapping("/runParam")
    public CommonResult runParam(@RequestBody() Map<String, Object> params) {
        CommonResult commonResult = new CommonResult();
        HashMap hashMap = new HashMap();

        logger.info("入参 params: {}", params);
        JSONObject jsonParams = new JSONObject(params);
        String sceneId = String.valueOf(jsonParams.get("id"));

        //根据sceneId查询用例
        CaseManage caseManage = new CaseManage();
        caseManage.setSceneId(sceneId);
        caseManage.setIsDelete(1);
        List<CaseManage> caseManageList = iCaseManageEngine.caseListQueryAllForScene(caseManage);
        hashMap.put("caseManageList",caseManageList);

        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(hashMap);
        commonResult.setStatus("200");
        return commonResult;
    }


    /** 更新场景 */
    @PostMapping("/update")
    public CommonResult update(@RequestBody SceneForm sceneForm) {

        return sceneManageService.sceneUpdate(sceneForm);
    }

    /** 复制场景 */
    @PostMapping("/copy")
    public CommonResult copy1(@RequestBody SceneId sceneId) {

        return sceneManageService.sceneCopy(sceneId.getId().intValue());
    }

    /** 删除场景 */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult sceneDelete(@RequestBody() Map<String, Object> params) {
        CommonResult commonResult = new CommonResult();
        logger.info("入参 params: {}", params);
        JSONObject jsonParams = new JSONObject(params);
        int sceneDelete = sceneManageService.SceneManageDelete(jsonParams.getInteger("id"));
        logger.info("场景删除结果：" + sceneDelete);

        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(sceneDelete);
        commonResult.setStatus("200");
        return commonResult;

    }

    /** 执行场景 */
    @PostMapping("/execute")
    public CommonResult execute(@RequestBody SceneId sceneId) {

        return sceneManageService.sceneExecute(sceneId.getId().intValue());
    }


    /**
     * 查看场景详情
     * @param sceneId
     * @return
     */
    @GetMapping("/detail")
    public CommonResult sceneDetail(@RequestBody() @RequestParam @Valid Integer sceneId) {
        CommonResult commonResult = new CommonResult();
        HashMap hashMap = new HashMap();

        //根据sceneId查询场景数据
        List<SceneManage> sceneManageList = sceneManageService.SceneManageListQueryById(sceneId);
        logger.info("场景列表: " + sceneManageList);

        //根据sceneId查询用例数据（组装前后置，入参，出参）
        CaseManage caseManage = new CaseManage();
        caseManage.setSceneId(String.valueOf(sceneId));
        caseManage.setIsDelete(1);
        List<CaseManage> caseManageList = iCaseManageEngine.caseListQueryAllForScene(caseManage);
        logger.info("用例列表: " + caseManageList);

        hashMap.put("sceneManageList",sceneManageList);
        hashMap.put("requests",caseManageList);
        commonResult.setCode("0");
        commonResult.setData(hashMap);
        commonResult.setStatus("200");
        return commonResult;
    }

    }
