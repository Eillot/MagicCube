package com.simon.magiccube.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.dao.domain.AssertData;
import com.simon.magiccube.dao.domain.CaseManage;
import com.simon.magiccube.dao.domain.PreOrPosAction;
import com.simon.magiccube.dao.domain.TestData;
import com.simon.magiccube.engine.IAssertDataEngine;
import com.simon.magiccube.engine.ICaseManageEngine;
import com.simon.magiccube.engine.IPreOrPosActionEngine;
import com.simon.magiccube.engine.IProductManageEngine;
import com.simon.magiccube.engine.ITestDataEngine;
import com.simon.magiccube.engine.common.ResourceManagement;
import com.simon.magiccube.facade.support.CommonResult;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用例Controller
 */
@RestController
@CrossOrigin
@RequestMapping("/casemanage")
//如果其他bean调用这个方法,在其他bean中声明事务,那就用事务.如果其他bean没有声明事务,那就不用事务
@Transactional(propagation= Propagation.SUPPORTS)
public class CaseManageController {

  private static final Logger logger = LoggerFactory.getLogger(CaseManageController.class);

  @Autowired
  private ICaseManageEngine iCaseManageEngine;
  @Autowired
  private IProductManageEngine iProductManageEngine;
  @Autowired
  private IPreOrPosActionEngine iPreOrPosActionEngine;
  @Autowired
  private ITestDataEngine iTestDataEngine;
  @Autowired
  private IAssertDataEngine iAssertDataEngine;

  /**
   * 查询用例列表
   *
   * @param caseManage
   * @return
   * @throws ServletException
   */
  @RequestMapping(value = "/case/all", method = RequestMethod.POST)
  public CommonResult caseManage(@RequestBody() CaseManage caseManage) {
    CommonResult commonResult = new CommonResult();
    caseManage.setIsDelete(1);
    caseManage.setCaseName(caseManage.getCaseName());
    caseManage.setPriority(caseManage.getPriority());
    caseManage.setRunState(caseManage.getRunState());
    caseManage.setRunResult(caseManage.getRunResult());
    caseManage.setProductId(caseManage.getProductId());
    caseManage.setAppId(caseManage.getAppId());
    caseManage.setSceneId("0");
    List<CaseManage> caseAll = iCaseManageEngine.caseListQueryAll(caseManage);
    logger.info("用例列表查询结果：" + caseAll);

    commonResult.setCode("0");
    commonResult.setMsg("success");
    commonResult.setData(caseAll);
    commonResult.setStatus("200");
    return commonResult;

  }

  /**
   * 用例详情
   *
   * @param caseId
   * @return
   */
  @GetMapping(value = "case/detail")
  public CommonResult caseDetail(@RequestBody() @RequestParam @Valid Integer caseId) {
    CommonResult commonResult = new CommonResult();

    HashMap hashMap = new HashMap();

    //根据用例id查询用例表
    List<CaseManage> caseManageList = iCaseManageEngine.selectCaseById(caseId);
    logger.info("用例列表: " + caseManageList);

    //根据业务线id，查询业务线数据
    String productId = caseManageList.get(0).getProductId();
    JSONObject productList = JSONObject
      .parseObject(ResourceManagement.queryResBusinessInfoById(productId));
    logger.info("业务线数据：" + productList);

    //根据应用id，查询应用数据
    String appId = caseManageList.get(0).getAppId();
    JSONObject appList = JSONObject
      .parseObject(ResourceManagement.queryListResAppInfoByAppId(appId));
    logger.info("应用数据：" + appList);

    //根据接口id，查询接口数据）
    String interfaceId = caseManageList.get(0).getInterfaceId();
    JSONObject interfaceList = JSONObject
      .parseObject(ResourceManagement.queryResInterfaceInfoDetail(interfaceId));
    logger.info("接口数据：" + interfaceList);

    //根据用例表查询出的前置id，查询前后置表
    PreOrPosAction preOrPosAction = new PreOrPosAction();
    JSONObject preActionList = new JSONObject();
    String preActionId = caseManageList.get(0).getPreActionId();
    if (preActionId == null || preActionId.equals("")) {
      preActionList.put("isPre", "2"); //2是没有
      logger.info("没有前置");
    } else {
      preActionList.put("isPre", "1"); //1是有
      preOrPosAction.setId(Integer.valueOf(preActionId));
      List<PreOrPosAction> preList = iPreOrPosActionEngine.PreOrPosActionQueryAll(preOrPosAction);
      preActionList.put("preList", preList);
      logger.info("前置返回结果: " + preActionList);
    }

    //根据用例id去入参表里查询数据
    TestData testData = new TestData();
    testData.setCaseId(String.valueOf(caseManageList.get(0).getId()));
    testData.setIsDelete(1);
    testData.setSceneId("0");
    List<TestData> testDataList = iTestDataEngine.TestDataQueryAll(testData);
    logger.info("入参数据: " + testDataList);

    //根据入参id去断言表里查询数据
    AssertData assertData = new AssertData();
//        List AssertDatalist = new ArrayList();
    for (TestData testData1 : testDataList) {
      String testDataId = String.valueOf(testData1.getId());
      assertData.setTestDataId(testDataId);
      List<AssertData> assertDataList = iAssertDataEngine.AssertDataQueryAll(assertData);
      hashMap.put("assertDatalist", assertDataList);

//            AssertDatalist.add(assertDataList);
    }
    logger.info("断言数据: " + hashMap);

    //根据用例表查询出的后置id，查询前后置表
    JSONObject posActionList = new JSONObject();
    String posActionId = caseManageList.get(0).getPosActionId();
    if (posActionId == null || posActionId.equals("")) {
      posActionList.put("isPos", "2"); //2是没有
      logger.info("没有后置");

    } else {
      preOrPosAction.setId(Integer.valueOf(posActionId));
      posActionList.put("isPos", "1"); //1是有
      List<PreOrPosAction> posList = iPreOrPosActionEngine.PreOrPosActionQueryAll(preOrPosAction);
      posActionList.put("posList", posList);
      logger.info("后置数据为: " + posActionList);
    }

    hashMap.put("caseManageList", caseManageList);
    hashMap.put("productList", productList);
    hashMap.put("appList", appList);
    hashMap.put("interfaceList", interfaceList);
    hashMap.put("preActionList", preActionList);
    hashMap.put("testDataList", testDataList);
    hashMap.put("posActionList", posActionList);
    logger.info("result:" + hashMap);
    commonResult.setCode("0");
    commonResult.setData(hashMap);
    commonResult.setStatus("200");
    return commonResult;

  }


  /**
   * 用例新增页面
   *
   * @param productId
   * @param appId
   * @param preProductId
   * @param posProductId
   * @return
   */
  @GetMapping(value = "case/inster")
  public CommonResult caseInster(@RequestBody() @RequestParam(required = false) String productId,
    @RequestParam(required = false) String appId,
    @RequestParam(required = false) String preProductId,
    @RequestParam(required = false) String posProductId) {
    CommonResult commonResult = new CommonResult();
    HashMap hashMap = new HashMap();
    PreOrPosAction preActionSet = new PreOrPosAction();
    PreOrPosAction posActionSet = new PreOrPosAction();

    if (productId != null && !productId.equals("")) {
      //根据业务线id查询应用
      JSONObject appList = JSONObject
        .parseObject(ResourceManagement.queryListResAppInfo(productId));
      logger.info("根据业务线id查询应用: " + appList);
      hashMap.put("appList", appList);
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
   * 用例保存
   *
   * @param
   * @param
   * @param
   * @return
   */
  @RequestMapping(value = "case/save", method = RequestMethod.POST)
  public CommonResult caseCreate(@RequestBody() Map<String, Object> params) {
    CommonResult commonResult = new CommonResult();

    logger.info("入参 params: {}", params);
    JSONObject jsonParams = new JSONObject(params);

    //新增用例表数据
    CaseManage caseManage = new CaseManage();
    caseManage.setCaseName(jsonParams.getString("caseName"));
    caseManage.setCaseDes(jsonParams.getString("caseDes"));
    caseManage.setPriority(jsonParams.getString("priority"));
    caseManage.setProductId(jsonParams.getString("productId"));
    caseManage.setAppId(jsonParams.getString("appId"));
    caseManage.setInterfaceType(jsonParams.getInteger("type"));
    caseManage.setInterfaceId(jsonParams.getString("interfaceId"));
    caseManage.setRequestType(jsonParams.getInteger("requestType"));
    caseManage.setPreActionId(jsonParams.getString("preActionId"));
    caseManage.setPosActionId(jsonParams.getString("posActionId"));
    caseManage.setSceneId("0");
    caseManage.setCreaterTime(new Date());
    caseManage.setCreaterName("admin");
    caseManage.setModifyTime(new Date());
    caseManage.setModifyName("admin");
    caseManage.setIsDelete(1);
    int caseInserter = iCaseManageEngine.caseManageInsert(caseManage);
    logger.info("新增用例数据:" + caseInserter);

    //新增入参表数据
    TestData testData = new TestData();
    String caseId = String.valueOf(caseManage.getId());
    testData.setCaseId(caseId);
    Long inputFormat = jsonParams.getLong("inputFormat");
    if (inputFormat != null) {
      testData.setInputFormat(inputFormat);
      JSONArray caseDataContentJson = jsonParams.getJSONArray("caseDataContentList");
      if (null != caseDataContentJson) {
        JSONArray caseDataContentJsonAll = new JSONArray();
        for (int i = 0; i < caseDataContentJson.size(); i++) {
          System.out.println("@@@:" + caseDataContentJson.get(i));
          caseDataContentJsonAll.add(caseDataContentJson.get(i));
        }
        System.out.println("@@@:" + caseDataContentJsonAll);
        testData.setCaseDataContent(String.valueOf(caseDataContentJsonAll));
      } else {
        String caseDataString = String.valueOf(jsonParams.getJSONObject("caseDataContent"));
        testData.setCaseDataContent(caseDataString);
      }
    } else {
      if (jsonParams.getLong("type") == 2) {//dubbo接口

        JSONObject jsonObjectDTOParam = jsonParams.getJSONObject("caseDataContent");//dto入参
        testData.setCaseDataContent(String.valueOf(jsonObjectDTOParam));
        if(String.valueOf(jsonParams.getJSONArray("dubboDataParam")) !=null ){
          JSONArray jsonArray = jsonParams.getJSONArray("dubboDataParam");
          String dataType = jsonArray.getJSONObject(0).getString("dataType");
          if(!dataType.equals("dataTypeNull")){
            testData.setBaseDataParam(String.valueOf(jsonParams.getJSONArray("dubboDataParam")));
          }
        }

      } else {//get接口
        testData.setCaseDataContent(jsonParams.getString("getCaseDataContent"));
      }
    }

    testData.setCreaterName("admin");
    testData.setCreaterTime(new Date());
    testData.setSceneId("0");
    testData.setIsDelete(1);
    int testDataInserter = iTestDataEngine.TestDataInsert(testData);
    logger.info("新增入参数据：" + testDataInserter);

    //新增断言表数据
    AssertData assertData = new AssertData();
    JSONArray assertDataArray = jsonParams.getJSONArray("assertDatalist");
    String testDataId = String.valueOf(testData.getId());
    for (int i = 0; i < assertDataArray.size(); i++) {
      JSONObject jsonObject = assertDataArray.getJSONObject(i);
      assertData.setTestDataId(testDataId);
      if (null != jsonObject.get("assertDes")) {
        assertData.setAssertDes(jsonObject.getString("assertDes"));
      }
      if (null != jsonObject.get("assertTarget")) {
        assertData.setAssertTarget(jsonObject.getString("assertTarget"));
      }
      if (null != jsonObject.get("matchRules")) {
        assertData.setMatchRules(jsonObject.getInteger("matchRules"));
      }
      assertData.setExpectedValue(jsonObject.getString("expectedValue"));
      assertData.setSceneId("0");
      assertData.setCreaterName("admin");
      assertData.setCreaterTime(new Date());
      assertData.setIsDelete(1);
      int assertDataInserter = iAssertDataEngine.AssertDataInsert(assertData);
      logger.info("新增断言数据：" + assertDataInserter);
    }

    commonResult.setCode("0");
    commonResult.setMsg("success");
    commonResult.setStatus("200");
    return commonResult;
  }

  /**
   * 修改用例
   *
   * @param params
   * @return
   */
  @RequestMapping(value = "/case/modify", method = RequestMethod.POST)
  public CommonResult caseModify(@RequestBody() Map<String, Object> params) {
    CommonResult commonResult = new CommonResult();

    logger.info("入参 params: {}", params);
    JSONObject jsonParams = new JSONObject(params);

    //修改用例表数据
    CaseManage caseManage = new CaseManage();
    caseManage.setId(jsonParams.getInteger("id"));
    caseManage.setCaseName(jsonParams.getString("caseName"));
    caseManage.setCaseDes(jsonParams.getString("caseDes"));
    caseManage.setPriority(jsonParams.getString("priority"));
    caseManage.setProductId(jsonParams.getString("productId"));
    caseManage.setAppId(jsonParams.getString("appId"));
    caseManage.setInterfaceType(jsonParams.getInteger("type"));
    caseManage.setInterfaceId(jsonParams.getString("interfaceId"));
    caseManage.setRequestType(jsonParams.getInteger("requestType"));
    if (jsonParams.getString("preActionId") == null) {
      caseManage.setPreActionId("");
    } else {
      caseManage.setPreActionId(jsonParams.getString("preActionId"));
    }
    if (jsonParams.getString("posActionId") == null) {
      caseManage.setPosActionId("");
    } else {
      caseManage.setPosActionId(jsonParams.getString("posActionId"));
    }
    caseManage.setModifyTime(new Date());
    caseManage.setModifyName("admin");
    caseManage.setIsDelete(1);
    int caseInserter = iCaseManageEngine.CaseManageUpdate(caseManage);
    logger.info("修改用例数据：" + caseInserter);

    //修改入参表数据
    TestData testData = new TestData();
    String caseId = String.valueOf(caseManage.getId());
    testData.setCaseId(caseId);
    testData.setInputFormat(jsonParams.getLong("inputFormat"));

    if(jsonParams.getInteger("type") == 1 && jsonParams.getInteger("requestType") == 1){//http接口,get请求
      testData.setCaseDataContent(jsonParams.getString("caseDataContent"));
    }else if(jsonParams.getInteger("type") == 1 && jsonParams.getInteger("requestType") == 2){//http接口,post请求
      if(jsonParams.getInteger("inputFormat") == 1){//json入参
        testData.setCaseDataContent(String.valueOf(jsonParams.getJSONObject("caseDataContent")));
      }else if(jsonParams.getInteger("inputFormat") == 2){//form入参
        JSONArray caseDataContentJson = jsonParams.getJSONArray("caseDataContentList");
        JSONArray caseDataContentJsonAll = new JSONArray();
        for (int i = 0; i < caseDataContentJson.size(); i++) {
          System.out.println("@@@:" + caseDataContentJson.get(i));
          caseDataContentJsonAll.add(caseDataContentJson.get(i));
        }
        System.out.println("@@@:" + caseDataContentJsonAll);
        testData.setCaseDataContent(String.valueOf(caseDataContentJsonAll));
      }

    }else if(jsonParams.getInteger("type") == 2){//dubbo接口
      testData.setCaseDataContent(String.valueOf(jsonParams.getJSONObject("caseDataContent")));
      if(String.valueOf(jsonParams.getJSONArray("dubboDataParam")) !=null ){
        JSONArray jsonArray = jsonParams.getJSONArray("dubboDataParam");
        String dataType = jsonArray.getJSONObject(0).getString("dataType");
        if(!dataType.equals("dataTypeNull")){
          testData.setBaseDataParam(String.valueOf(jsonParams.getJSONArray("dubboDataParam")));
        }
      }
    }

    testData.setModifyName("admin");
    testData.setModifyTime(new Date());
    testData.setIsDelete(1);
    int testDataInserter = iTestDataEngine.TestDataUpdateByCaseId(testData);
    logger.info("修改入参数据：" + testDataInserter);
    TestData testData1 = iTestDataEngine.TestDataQueryByCaseId(caseId);


    //新增断言表数据
    AssertData assertData = new AssertData();
    JSONArray assertDataArray = jsonParams.getJSONArray("assertDatalist");

    for (int i = 0; i < assertDataArray.size(); i++) {
      JSONObject jsonObject = assertDataArray.getJSONObject(i);
      String testDataId = String.valueOf(testData1.getId());
      String assertDataId = String.valueOf(jsonObject.get("id"));

      //判断是否有assertDataId，如果有则执行根据主键更新，如果没有则执行根据testDataId插入
      if (assertDataId != null && !"null".equals(assertDataId)) {
        assertData.setId(Integer.valueOf(assertDataId));
        if (null != jsonObject.get("assertDes")) {
          assertData.setAssertDes(jsonObject.getString("assertDes"));
        }
        if (null != jsonObject.get("assertTarget")) {
          assertData.setAssertTarget(jsonObject.getString("assertTarget"));
        }
        if (null != jsonObject.get("matchRules")) {
          assertData.setMatchRules(jsonObject.getInteger("matchRules"));
        }
        assertData.setExpectedValue(jsonObject.getString("expectedValue"));

        assertData.setModifyTime(new Date());
        assertData.setModifyName("admin");
        assertData.setIsDelete(1);
        int assertDataUpdate = iAssertDataEngine.AssertDataUpdate(assertData);
        logger.info("修改断言数据：" + assertDataUpdate);
      } else {
        assertData.setTestDataId(testDataId);
        if (null != jsonObject.get("assertDes")) {
          assertData.setAssertDes(jsonObject.getString("assertDes"));
        }
        if (null != jsonObject.get("assertTarget")) {
          assertData.setAssertTarget(jsonObject.getString("assertTarget"));
        }
        if (null != jsonObject.get("matchRules")) {
          assertData.setMatchRules(jsonObject.getInteger("matchRules"));
        }
        if (null != jsonObject.get("expectedValue")) {
          assertData.setExpectedValue(jsonObject.getString("expectedValue"));
        }
        assertData.setCreaterName("admin");
        assertData.setCreaterTime(new Date());
        assertData.setIsDelete(1);
        int assertDataInserter = iAssertDataEngine.AssertDataInsert(assertData);
        logger.info("新增断言数据：" + assertDataInserter);
      }


    }

    commonResult.setCode("0");
    commonResult.setMsg("success");
    commonResult.setStatus("200");
    return commonResult;
  }


  /**
   * 删除用例
   *
   * @param params
   * @return
   */
  @RequestMapping(value = "/case/delete", method = RequestMethod.POST)
  public CommonResult caseDelete(@RequestBody() Map<String, Object> params) {
    CommonResult commonResult = new CommonResult();
    logger.info("入参 params: {}", params);
    JSONObject jsonParams = new JSONObject(params);
    int caseDelete = iCaseManageEngine.CaseManageDelete(jsonParams.getInteger("id"));
    logger.info("用例删除结果：" + caseDelete);

    commonResult.setCode("0");
    commonResult.setMsg("success");
    commonResult.setData(caseDelete);
    commonResult.setStatus("200");
    return commonResult;

  }


  @GetMapping(value = "case/productQueryAndInterfaceQuery")
  public CommonResult caseInster(@RequestBody() @RequestParam(required = false) String interfaceId) {
    CommonResult commonResult = new CommonResult();
    HashMap hashMap = new HashMap();

    JSONObject productList = JSONObject.parseObject(ResourceManagement.queryListResBusinessInfo());
    logger.info("查询业务线列表: " + productList);
    hashMap.put("productList",productList);

    if (!interfaceId.equals("")) {
      JSONObject interfaceList = JSONObject.parseObject(ResourceManagement.queryResInterfaceInfoDetail(interfaceId));
      logger.info("查询接口返回结果：" + interfaceList);
      hashMap.put("interfaceList",interfaceList);
    }else {
      hashMap.put("interfaceList",null);
    }

    commonResult.setCode("0");
    commonResult.setMsg("success");
    commonResult.setData(hashMap);
    commonResult.setStatus("200");
    return commonResult;
  }

}
