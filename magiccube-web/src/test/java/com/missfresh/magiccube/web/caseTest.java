package com.simon.magiccube.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.simon.magiccube.dao.domain.*;
import com.simon.magiccube.dao.mapper.CaseManageMapper;
import com.simon.magiccube.dao.mapper.ProductManageMapper;
import com.simon.magiccube.engine.*;
import com.simon.magiccube.engine.common.RequestBase;
import com.simon.magiccube.engine.common.ResourceManagement;
import com.simon.magiccube.engine.dto.request.DubboCaseRequest;
import com.simon.magiccube.facade.dto.ParamInfoDTO;
import com.simon.magiccube.facade.support.CommonResult;
import com.simon.magiccube.service.COSService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.JsonPathAssertions;
import org.testng.Assert;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class caseTest {

    @Autowired
    private ICaseManageEngine iCaseManageEngine;

    @Autowired
    private ICaseRunEngine iCaseRunEngine;

    @Autowired
    private IProductManageEngine iProductManageEngine;

    @Autowired
    private ProductManageMapper productManageMapper;

    @Autowired
    private IPreOrPosActionEngine iPreOrPosActionEngine;

    @Autowired
    private ICaseSceneRunRelationEngine iCaseSceneRunRelationEngine;

    @Autowired
    private ITestDataEngine iTestDataEngine;

    @Autowired
    private IAssertDataEngine iAssertDataEngine;

    @Autowired
    private COSService cosService;

    @Autowired
    private IAssertRunEngine iAssertRunEngine;

    @Autowired
    private IReportManageEngine iReportManageEngine;

    @Autowired
    private SQLService sqlService;

    @Autowired
    private CaseManageMapper manageMapper;

    @Autowired
    private SceneManageService sceneManageService;

    @Resource
    private ParamInfoService paramInfoService;

    @Test
    public void caseList() {

    }

    /**
     * 用例详情测试
     */
    @Test
    public void caseTest01(){
        HashMap hashMap = new HashMap();

        CaseManage caseManage = new CaseManage();
        caseManage.setId(1);
        caseManage.setIsDelete(1);
        //根据用例id查询用例表
        List<CaseManage> caseManageList = iCaseManageEngine.caseManageQueryAll(caseManage);
        System.out.println("用例列表: " + caseManageList);
        hashMap.put("caseManageList",caseManageList);
//        result.add(caseManageList);

        //根据用例表查询出的前置id，查询前后置表
        PreOrPosAction preOrPosAction = new PreOrPosAction();
        String preActionId = caseManageList.get(0).getPreActionId();
        if(preActionId == null || preActionId.equals("")){
//            result.add("isPre=1");
            System.out.println("@@@: 没有前置");

        }else {
            System.out.println("前置id: " + preActionId);
            preOrPosAction.setId(Integer.valueOf(preActionId));
            List<PreOrPosAction> preActionList = iPreOrPosActionEngine.PreOrPosActionQueryAll(preOrPosAction);
            System.out.println("前置数据为: " + preActionList);
            hashMap.put("preActionList",preActionList);
//            result.add(preActionList);
        }

        //根据用例表查询出的后置id，查询前后置表
        String posActionId = caseManageList.get(0).getPosActionId();
        if(posActionId ==null || posActionId.equals("")){
//            result.add("isPos=1");
            System.out.println("@@@: 没有后置");

        }else {

            System.out.println("@@@: " + posActionId);
            preOrPosAction.setId(Integer.valueOf(posActionId));
            List<PreOrPosAction> posActionList = iPreOrPosActionEngine.PreOrPosActionQueryAll(preOrPosAction);
            System.out.println("后置数据为: " + posActionList);
            hashMap.put("posActionList",posActionList);
//            result.add(posActionList);
        }

        //根据用例id去入参表里查询数据
        TestData testData = new TestData();
        testData.setCaseId(String.valueOf(caseManageList.get(0).getId()));
        testData.setIsDelete(1);
        List<TestData> testDataList = iTestDataEngine.TestDataQueryAll(testData);
        System.out.println("入参数据: " + testDataList);
        hashMap.put("testDataList",testDataList);

        //根据入参id去断言表里查询数据
        AssertData assertData = new AssertData();
        List list = new ArrayList();
        for(TestData testData1 : testDataList){
            String testDataId = String.valueOf(testData1.getId());
            assertData.setTestDataId(testDataId);
            List<AssertData> assertDataList = iAssertDataEngine.AssertDataQueryAll(assertData);
            list.add(assertDataList);
        }
        System.out.println("断言数据：" +list);

        System.out.println("@@@result: " + hashMap);

    }

    /**
     * 用例新增
     */
    @Test
    public void caseInsert_old(){
        //新增用例表数据
        CaseManage caseManage = new CaseManage();
        caseManage.setCaseName("新增用例");
        caseManage.setPriority("P0");
        caseManage.setProductId("1");
        caseManage.setInterfaceType(0);
        caseManage.setInterfaceId("2");
        caseManage.setPreActionId("2");
        caseManage.setPosActionId("1");
        caseManage.setCreaterTime(new Date());
        caseManage.setCreaterName("zhangsan");
        caseManage.setIsDelete(1);
        int caseInserter = iCaseManageEngine.caseManageInsert(caseManage);
        System.out.println("用例新增：" + caseInserter);

        //新增入参表数据
        TestData testData = new TestData();
        String caseId = String.valueOf(caseManage.getId());
        testData.setCaseId(caseId);
        testData.setInputFormat(1);
        testData.setCaseDataContent("{\"dispatcher_mobile\":\"186124950556\"}");
        testData.setCreaterName("lisi");
        testData.setCreaterTime(new Date());
        testData.setIsDelete(1);
        int testDataInserter = iTestDataEngine.TestDataInsert(testData);
        System.out.println("入参新增数据：" + testDataInserter);


        //新增断言表数据
        AssertData assertData = new AssertData();
        String testDataId = String.valueOf(testData.getId());
        assertData.setTestDataId(testDataId);
        assertData.setAssertDes("验证123");
        assertData.setMatchRules(1);
        assertData.setExpectedValue("123");
        assertData.setCreaterName("wangwu");
        assertData.setCreaterTime(new Date());
        assertData.setIsDelete(1);
        int assertDataInserter = iAssertDataEngine.AssertDataInsert(assertData);
        System.out.println("断言新增数据：" + assertDataInserter);

    }

    /**
     * 用例修改
     */
    @Test
    public void caseUpdata() {
        //修改用例表数据
        CaseManage caseManage = new CaseManage();
        caseManage.setId(10);
        caseManage.setCaseName("新增用例4");
        caseManage.setPriority("P0");
        caseManage.setProductId("1");
        caseManage.setInterfaceType(0);
        caseManage.setInterfaceId("2");
        caseManage.setPreActionId("2");
        caseManage.setPosActionId("1");
        caseManage.setModifyName("wangwu");
        caseManage.setModifyTime(new Date());
        int caseInserter = iCaseManageEngine.CaseManageUpdate(caseManage);
        System.out.println("修改用例数据：" + caseInserter);

        //修改入参表数据
        TestData testData = new TestData();
        String caseId = String.valueOf(caseManage.getId());
        testData.setCaseId(caseId);
        testData.setInputFormat(1);
        testData.setCaseDataContent("修改修改4");
        testData.setModifyName("wangwu");
        testData.setModifyTime(new Date());
        int testDataInserter = iTestDataEngine.TestDataUpdateByCaseId(testData);
        System.out.println("修改入参数据：" + testDataInserter);


        //新增断言表数据
        AssertData assertData = new AssertData();
        //先查询入参id
        TestData testData1 = new TestData();
        testData1.setCaseId(String.valueOf(caseManage.getId()));
        testData.setIsDelete(1);
        List<TestData> testDataList = iTestDataEngine.TestDataQueryAll(testData1);

        String testDataId = String.valueOf(testDataList.get(0).getId());
        assertData.setTestDataId(testDataId);
        assertData.setAssertDes("验证修改修改4");
        assertData.setMatchRules(1);
        assertData.setExpectedValue("123");
        assertData.setModifyName("wangwu");
        assertData.setModifyTime(new Date());
        int assertDataInserter = iAssertDataEngine.AssertDataUpdateByTestDataId(assertData);
        System.out.println("修改断言数据：" + assertDataInserter);
    }

    /**
     * 用例模块测试
     */
    @Test
    public void caseTest(){

        //查询所有数据
        CaseManage caseManage = new CaseManage();
        caseManage.setIsDelete(1);
        List<CaseManage> caseAll = iCaseManageEngine.caseManageQueryAll(caseManage);

        //拿到caseId，去关系表中查
        List result = new ArrayList();
        for(CaseManage caseManage1:caseAll){
            String caseId = String.valueOf(caseManage1.getId());
            System.out.println("@@ caseId:"+caseId);
            CaseSceneRunRelation sceneRunRelation = new CaseSceneRunRelation();
            sceneRunRelation.setRunObjectId(caseId); //执行关系表中按用例id查
            sceneRunRelation.setRunObjectType(0); //执行关系表中按类型为用例的查
            sceneRunRelation.setIsDelete(1);
            List<CaseSceneRunRelation> caseSceneRunRelationList = iCaseSceneRunRelationEngine.caseSceneRunRelationQueryAll(sceneRunRelation);
            System.out.println("@@ caseSceneRunRelationList:"+caseSceneRunRelationList);
            //从关系表中拿出执行id，用执行id在任务表中查询
            for(CaseSceneRunRelation caseSceneRunRelation : caseSceneRunRelationList){
                String runId = caseSceneRunRelation.getRunId();
                System.out.println("@@ runId:"+runId);

                if(runId != null || runId !=""){

                }
            }


        }


//        List<CaseManage> caseAll = iCaseManageEngine.caseListQueryAll();
//        System.out.println("@@@:"+caseAll);
//        List result = new ArrayList();
//        //将业务线id转化成业务线名称
//        for(CaseManage caseManage1 : caseAll){
//            String productId = caseManage1.getProductId();
//            String productName = iProductManageEngine.selectProductNameByID(Integer.parseInt(productId)).getProductName();
//            caseManage1.setProductName(productName);
//            System.out.println("@@@:"+ caseManage1);
//            result.add(caseManage1);
//        }
//        System.out.println("@@@:"+ result);


        //新增
//        CaseManage caseManage = new CaseManage();
//        caseManage.setCaseName("测试用例001");
//        caseManage.setPriority("P2");
//        caseManage.setProductId("3");
//        caseManage.setInterfaceType(1);
//        caseManage.setInterfaceId("3");
//        caseManage.setRemark("测试测试");
//        caseManage.setCreaterName("lisi");
//        caseManage.setCreaterTime(new Date());
//        iCaseManageEngine.caseManageInsert(caseManage);
    }

    /**
     * 前后置模块测试
     * @throws ParseException
     */
    @Test
    public void preOrPosTest() throws ParseException {
        //新增
//        PreOrPosAction preOrPosAction = new PreOrPosAction();
//        preOrPosAction.setActionName("前后置测试002");
//        preOrPosAction.setActionType(1);
//        preOrPosAction.setActionContent("http://www.baidu.com");
//        preOrPosAction.setCreaterName("zhangsan");
//        preOrPosAction.setCreaterTime(new Date());
//        iPreOrPosActionEngine.PreOrPosActionInsert(preOrPosAction);

//        String str = "yyy-MM-dd HH:mm:ss";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        //查询全部数据
//        List<PreOrPosAction> preOrPosActionList = iPreOrPosActionEngine.PreOrPosActionListQueryAll();
//        System.out.println("@@@:"+preOrPosActionList);
//
//        for(PreOrPosAction preOrPosAction : preOrPosActionList){
//            Date cTime = preOrPosAction.getCreaterTime();
//            String createrTime = simpleDateFormat.format(cTime);
//            System.out.println("@@@:"+createrTime);
//            preOrPosAction.setCreaterDate(createrTime);
//            System.out.println("@@@:"+preOrPosAction);
        //按查询条件查询
        PreOrPosAction preOrPosAction = new PreOrPosAction();
//        preOrPosAction.setIsDelete(1);
//        preOrPosAction.setId(1);
        preOrPosAction.setIsDelete(1);

        List<PreOrPosAction> preOrPosActionList = iPreOrPosActionEngine.PreOrPosActionQueryAll(preOrPosAction);
        System.out.println("@@@:" + preOrPosActionList);

        }

    /**
     * 用例列表查询
     */
    @Test
    public void test(){
        CaseManage caseManage = new CaseManage();
        caseManage.setIsDelete(1);
        caseManage.setCaseName(caseManage.getCaseName());
        caseManage.setPriority(caseManage.getPriority());
        caseManage.setRunState(caseManage.getRunState());
        caseManage.setRunResult(caseManage.getRunResult());
        List<CaseManage> caseAll = iCaseManageEngine.caseListQueryAll(caseManage);
        System.out.println("用例列表查询结果：" + caseAll);


        List result = new ArrayList();
        for (CaseManage caseManageDate : caseAll) {
            String productId = caseManageDate.getProductId();
            String productName = iProductManageEngine.selectProductNameByID(Integer.parseInt(productId)).getProductName();
            caseManageDate.setProductName(productName);
            result.add(caseManageDate);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("list:", result);
        System.out.println("@@@:"+hashMap);

        }

    @Test
    public void perorposQueryById() {

        PreOrPosAction preOrPosAction = new PreOrPosAction();
//        System.out.println("@@@: " + iPreOrPosActionEngine.PreOrPosActionQueryById(40));
        preOrPosAction.setId(36);
        preOrPosAction.setActionName("更新测试001");
        preOrPosAction.setRemark("");
        preOrPosAction.setActionType(3);
        preOrPosAction.setRequestType("");
        preOrPosAction.setActionContent("1");
        preOrPosAction.setProductId("1");
        preOrPosAction.setModifyName("liutang");
        preOrPosAction.setModifyTime(new Date());
        preOrPosAction.setIsDelete(1);
        int preOrPosActionUpdate = iPreOrPosActionEngine.PreOrPosActionUpdate(preOrPosAction);

    }


    @Test
    public void commonTest(){
        //查询业务线
//        String a = ResourceManagement.queryListResBusinessInfo("");
//        System.out.println("@@:"+a);

        //查询数据库
//        String a = ResourceManagement.queryDatabaseResources("2");
//        System.out.println("@@:"+a);

        //查询应用信息
        System.out.println("@@#:"+ResourceManagement.queryResBusinessInfoById("2"));
        JSONObject productList = JSONObject.parseObject(ResourceManagement.queryResBusinessInfoById("2"));
        System.out.println("productList:"+productList);
        String productName = String.valueOf(productList.getJSONObject("result").get("businessName"));
        System.out.println("productName@@:"+productName);


        System.out.println("@@##:"+ResourceManagement.queryListResAppInfoByAppId("1"));
        JSONObject appList = JSONObject.parseObject(ResourceManagement.queryListResAppInfoByAppId("1"));
        System.out.println("applist@@:"+appList);

//        JSONArray jsonArray = JSONArray.parseArray(ResourceManagement.queryListResAppInfoByAppId("1"));
//        System.out.println("##jsonArray:"+jsonArray);
        System.out.println("@@@:"+appList.getJSONArray("result").getJSONObject(0).get("appname"));

//        String appName = String.valueOf(appList.getJSONObject("result").get("appname"));

//        System.out.println("@@:"+appName);
//        System.out.println("@@:"+b);

//        String url = "http://localhost:8079" + "/platform/business/queryListResBusinessInfo";
//        Map<String, String> paramsJson = new HashMap<>();
//        paramsJson.put("id", "");
//        JSONObject paramsJson = new JSONObject();
//        paramsJson.put("id","1");
//        String resultString = null;
//        try {
//            resultString = HttpClientUtil.sendPostRequest(url, String.valueOf(paramsJson));
//        } catch (HttpClientUtil.HttpUtilException e) {
//            e.printStackTrace();
//        }
//        System.out.println("@@:"+resultString);

//
//        String a = ResourceManagement.queryResInterfaceInfoDetail("124");
//        System.out.println("@@:"+a);

    }


    /**
     * 前后置查看详情（根据前后置id查看）
     */
    @Test
    public void preOrPosPageDetails(){
        //根据前后置id查询前后置数据
        String preOrPosId = "3";
        List preOrPoslist = new ArrayList();
        PreOrPosAction preOrPosAction = iPreOrPosActionEngine.PreOrPosActionQueryById(Integer.valueOf(preOrPosId));
        preOrPoslist.add(preOrPosAction);
        System.out.println("@根据id查询前后置数据：" +preOrPoslist);

        //根据id查询查询业务线
        String productId = preOrPosAction.getProductId();
        System.out.println("@productId:" +productId);
        String productList = ResourceManagement.queryResBusinessInfoById(productId);
        System.out.println("@根据业务线id查询业务线数据：" + productList);    //详情中【业务线】下拉框通过远程调用此接口，获取字段

        //如果actionType=1，根据databaseId查询数据库信息
        if(preOrPosAction.getActionType()==1){
            String dataList = ResourceManagement.queryDatabaseDetail(preOrPosAction.getDatabaseId());
            System.out.println("@根据数据库id查询数据库数据：" + dataList);

        }

        //如果actionType=2，取action_content内容和request_type内容---前端直接取

        //如果actionType=3，根据action_content中的用例id查询用例表数据
        if(preOrPosAction.getActionType() == 3){
            String caseId = preOrPosAction.getActionContent();
            List<CaseManage> caseManageList = iCaseManageEngine.selectCaseById(Integer.valueOf(caseId));
            System.out.println("@根据用例id查询用例数据：" + caseManageList);

        }

        //如果actionType=4，取action_content内容---前端直接取

        //如果actionType=5，取action_content内容---前端直接取


    }


    /**
     * 前后置新增
     */
    @Test
    public void preOrPosPageInsert(){
        //查询业务线
        JSONObject productList = JSONObject.parseObject(ResourceManagement.queryListResBusinessInfo());
        System.out.println("@查询业务线列表：" + productList);

        //返回动作类型
        JSONObject actionType = new JSONObject();
        actionType.put("1","执行sql");
        actionType.put("2","执行http请求");
        actionType.put("3","执行测试用例");
        actionType.put("4","等等时间");
        System.out.println("@返回动作类型列表：" + actionType);

        //查询数据库
        JSONObject dataList = JSONObject.parseObject(ResourceManagement.queryDatabaseList());
        System.out.println("@查询数据库列表：" + dataList);

        //返回请求类型
        JSONObject requestType = new JSONObject();
        requestType.put("1","post");
        requestType.put("2","get");
        System.out.println("@返回请求类型列表：" + requestType);

        //返回用例列表
//        List<CaseManage> caseManageList = iCaseManageEngine.selectAllCase(caseManage) ;
//        System.out.println("@返回用例列表：" + caseManageList);



    }

    /**
     * 用例详情-调资源管理
     */
    @Test
    public void caseDetail(){
        HashMap hashMap = new HashMap();

        //根据用例id查询用例表
        Integer caseId = 1;
        List<CaseManage> caseManageList = iCaseManageEngine.selectCaseById(caseId);
        System.out.println("@用例列表: " + caseManageList);

        //根据业务线id，查询业务线数据
        String productId = caseManageList.get(0).getProductId();
        System.out.println("@业务线id："+productId);
        JSONObject productList = JSONObject.parseObject(ResourceManagement.queryResBusinessInfoById(productId));
        System.out.println("@业务线数据："+productList);

        //根据接口id，查询接口数据（接口名称，接口内容，接口类型，请求类型（暂时没有））
        String interfaceId = caseManageList.get(0).getInterfaceId();
        System.out.println("@接口id："+interfaceId);
        JSONObject interfaceList = JSONObject.parseObject(ResourceManagement.queryResInterfaceInfoDetail(interfaceId));
        System.out.println("@接口数据："+interfaceList);

        //根据用例表查询出的前置id，查询前后置表
        PreOrPosAction preOrPosAction = new PreOrPosAction();
        JSONObject preActionList = new JSONObject();
        String preActionId = caseManageList.get(0).getPreActionId();
        if(preActionId == null || preActionId.equals("")){
            preActionList.put("isPre","2"); //2是没有
            System.out.println("@没有前置");
        }else {
            System.out.println("@前置id: " + preActionId);
            preActionList.put("isPre","1"); //1是有
            preOrPosAction.setId(Integer.valueOf(preActionId));
            List<PreOrPosAction> preList = iPreOrPosActionEngine.PreOrPosActionQueryAll(preOrPosAction);
            System.out.println("@前置数据为: " + preList);
            preActionList.put("preList",preList);
            System.out.println("@前置返回结果: " + preActionList);
        }

        //根据用例id去入参表里查询数据
        TestData testData = new TestData();
        testData.setCaseId(String.valueOf(caseManageList.get(0).getId()));
        testData.setIsDelete(1);
        List<TestData> testDataList = iTestDataEngine.TestDataQueryAll(testData);
        System.out.println("@入参数据: " + testDataList);

        //根据入参id去断言表里查询数据
        AssertData assertData = new AssertData();
        List AssertDatalist = new ArrayList();
        for(TestData testData1 : testDataList){
            String testDataId = String.valueOf(testData1.getId());
            assertData.setTestDataId(testDataId);
            List<AssertData> assertDataList = iAssertDataEngine.AssertDataQueryAll(assertData);
            AssertDatalist.add(assertDataList);
        }
        System.out.println("@断言数据: " + AssertDatalist);


        //根据用例表查询出的后置id，查询前后置表
        JSONObject posActionList = new JSONObject();
        String posActionId = caseManageList.get(0).getPosActionId();
        if(posActionId ==null || posActionId.equals("")){
            posActionList.put("isPos","2"); //2是没有
            System.out.println("@没有后置");

        }else {
            preOrPosAction.setId(Integer.valueOf(posActionId));
            posActionList.put("isPoe","1"); //1是有
            List<PreOrPosAction> posList = iPreOrPosActionEngine.PreOrPosActionQueryAll(preOrPosAction);
            posActionList.put("posList",posList);
            System.out.println("后置数据为: " + posActionList);
        }
    }

    /**
     * 用例列表查询
     */
    @Test
    public void caseAll(){
        CaseManage caseManage = new CaseManage();
        caseManage.setIsDelete(1);
        List<CaseManage> caseAll = iCaseManageEngine.caseListQueryAll(caseManage);
        System.out.println("用例列表查询结果："+caseAll);
    }


    /**
     * 用例新增页面
     */
    @Test
    public void caseInsert(){
        //查询业务线
        JSONObject productList = JSONObject.parseObject(ResourceManagement.queryListResBusinessInfo());
        System.out.println("@查询业务线列表：" + productList);

        //根据业务线id查询应用
        String productId = "1";
        JSONObject appList = JSONObject.parseObject(ResourceManagement.queryListResAppInfo(productId));
        System.out.println("@根据业务线id查询应用：" + appList);

        //根据应用id查询接口
        String appId = "1";
        JSONObject interfaceList = JSONObject.parseObject(ResourceManagement.queryListResInterfaceInfo(appId));
        System.out.println("@根据应用id查询接口：" + interfaceList);

        //根据业务线id查询前后置动作
        PreOrPosAction preOrPosAction = new PreOrPosAction();
        preOrPosAction.setProductId("1");
        List<PreOrPosAction> preOrPosActionList = iPreOrPosActionEngine.PreOrPosActionQueryAll(preOrPosAction);
        System.out.println("@根据业务线id查询前后置：" + preOrPosActionList);


    }

    /**
     * 用例保存
     */


    @Test
    public void test001(){
//        File localFile = new File("/Users/mac/Downloads/jmeter.log");
//        System.out.println("@@@:"+localFile);
//        cosService.asyncUpload(localFile,"log1");
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        cosService.download("log1");

        System.out.println("@@@:"+cosService.download("log1"));

    }

    @Test
    public void test002(){

//        sqlService.sqlFunction("36","select * from address_info where address_id=8");

//        sqlService.sqlFunction("119","insert into run_task (task_name,is_delete) VALUES ('asa',1)");

        String aa = sqlService.sqlFunction("119","update run_task set task_name='fff' where id=5");
        System.out.println("@@@:"+aa);

    }

    /**
     * 前后置sercice测试
     */
    @Test
    public void preOrPos(){
        //根据id查询前后置数据
        PreOrPosAction preOrPosActionList =  iPreOrPosActionEngine.PreOrPosActionQueryById(1);
        System.out.println("@@@:"+ preOrPosActionList);


        PreOrPosAction preOrPosAction = new PreOrPosAction();
        List<PreOrPosAction> preOrPosActionList2 = iPreOrPosActionEngine.PreOrPosActionQueryAll(preOrPosAction);
        System.out.println("@@@:"+ preOrPosActionList2);


    }

    /**
     * 用例service测试
     */
    @Test
    public void caseManage(){
        CaseManage caseManage = new CaseManage();
        List<CaseManage> caseManageList = iCaseManageEngine.caseListQueryAll(caseManage);
        System.out.println("@@@:"+ caseManageList);

    }

    /**
     * 用例执行service测试
     */
    @Test
    public void caseRun() throws IOException {
//        CaseRun caseRunList = iCaseRunEngine.selectCaseRunById(1);
//        System.out.println("@@@:"+ caseRunList);
//
//        CaseRun caseRunList2 = iCaseRunEngine.selectCaseRunByCaseId("134");
//        System.out.println("@@@:"+ caseRunList2);

//        TestData testData =iTestDataEngine.TestDataQueryByCaseId("133");
//        System.out.println("@@@:"+ testData.getCaseDataContent());


//        AssertData assertData = iAssertDataEngine.AssertDataQueryBytestDataId("112");
//        System.out.println("@@@:"+ assertData);


        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://10.2.4.100:8081/platform/openapi/queryListServerenvInfoByAppId?appId=3");
        CloseableHttpResponse response1 = httpclient.execute(httpGet);

        String responseString = EntityUtils.toString(response1.getEntity(),"UTF-8");
        System.out.println("字符串格式响应内容：" + responseString);


        //创建Json对象，把上面字符串序列化成Json对象格式
        JSONObject responseJson = JSON.parseObject(responseString);
        System.out.println("json格式响应内容：" + responseJson);


        //把响应内容存储在字符串对象
        Assert.assertEquals(responseString,1,"用例执行失败");



//        String responseValue = String.valueOf(response.get("code"));//实际结果
    }

    @Test
    public void runTest001() throws FileNotFoundException {
        String path = "http://10.2.4.100:8081/platform/openapi/queryListServerenvInfoByAppId?appId=3";

        JSONObject response = JSONObject.parseObject(RequestBase.doGet(path));
        System.out.println("接口返回："+ response.toString());
//
//        //实际结果
//        String responseValue = String.valueOf(response.get("code"));//实际结果
//
//        Assert.assertEquals(responseValue,1,"用例执行失败");


//        StringBuilder result = new StringBuilder();
//        try {
//            BufferedReader br = new BufferedReader(new FileReader("../test-output/xml-test-suite/caseTest001.html"));//构造一个BufferedReader类来读取文件
//            String s = null;
//            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
//                result.append(System.lineSeparator() + s);
//            }
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("@@@:" + result.toString());


//        try {
//            FileReader fr = new FileReader("../test-output/xml-test-suite/caseTest001.html");
//            BufferedReader br = new BufferedReader(fr);
//            String temp = "";// 用于临时保存每次读取的内容
//            while (temp != null) {
//                temp = br.readLine();
//                if (temp != null && temp.contains("java.lang.AssertionError")) {
//                    System.out.println(temp);
//                }
//            }
//        } catch (FileNotFoundException e) {
//// TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//// TODO Auto-generated catch block
//            e.printStackTrace();
//        }




//        File file = new File("../test-output/xml-test-suite/caseTest001.html");
//        InputStream input = null;
//        try {
//            input = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        StringBuffer buffer = new StringBuffer();
//        byte[] bytes = new byte[1024];
//        try {
//            for(int n ; (n = input.read(bytes))!=-1 ; ){
//                buffer.append(new String(bytes,0,n,"UTF-8"));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(buffer);
//
//
//
//        String start = "<pre>";
//        String end = "</pre>";
//        int s = buffer.indexOf(start) + start.length();
//        int e = buffer.indexOf(end);
//        buffer.substring(s, e);
//
//        System.out.println("@@@@:"+buffer.substring(s, e));
//


    }


    @Test
    public void caseRunTest() throws ParseException {
        CaseRun caseRun1 = new CaseRun();

        CaseRun caseRun = iCaseRunEngine.selectCaseRunByNew(caseRun1);
        System.out.println("@@@:"+iCaseRunEngine.selectCaseRunByNew(caseRun1));

        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = dfs.format(new Date());
        Date nowDate11 = dfs.parse(nowDate);
//        Date nowDate = dfs.parse(String.valueOf(new Date()));
//        Date nowDate = new Date();
        System.out.println("当前时间：" + nowDate);
        System.out.println("@@@@：" + caseRun.getModifyTime());
        String hisDate = dfs.format(caseRun.getModifyTime());
        System.out.println("历史时间：" + hisDate);

        Date hisDate11 = dfs.parse(hisDate);
        long between = nowDate11.getTime()-hisDate11.getTime();
        System.out.println("执行时间：" + between);



        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        System.out.println(day + "天" + hour + "小时" + min + "分" + s + "秒" + ms
                + "毫秒");

//        long between = nowDate-hisDate;

//        iCaseRunEngine.insertCaseRun();
    }


    @Test
    public void test0001(){
//        AssertRun assertRun = new AssertRun();
//        assertRun.setId(Integer.valueOf(171));
//        assertRun.setModifyTime(new Date());
//
//        assertRun.setAssertRunResult(1);
//        assertRun.setAssertResponseResult("123");
//        iAssertRunEngine.updateAssertRunByIdSelective(assertRun);
//
//        AssertRun ar = iAssertRunEngine.selectAssertRunById(229);
//        System.out.println("@@@:"+ar);
        String path = "http://10.2.4.100:9090/casemanage/case/all";
//        JSONObject paramsJson = new JSONObject();
        String paramsJson = "{}";

        JSONObject response = JSONObject.parseObject(RequestBase.doPost(path,paramsJson));
        System.out.println("接口返回："+ response.toString());

        Assert.assertNull(response);

    }

    @Test
    public void testError (){
        CaseManage caseManage = new CaseManage();
        caseManage.setIsDelete(0);
        List<CaseManage> caseAll =iCaseManageEngine.caseListQueryAll(caseManage);
        System.out.println("@@@:"+caseAll);

    }

    @Test
    public void sceneCaseRun(){
        CaseRun caseRunR = new CaseRun();
        caseRunR.setSceneId("16");
//        List list = new ArrayList();
//        list.add(183);
//        list.add(184);
//        caseRunR.setCaseIdList(list);
//        caseRunR.setRunResult(2);
//        caseRunR.setLimit(2);
//        int caseFailTotal = iCaseRunEngine.selectCaseRunListPageTotal(caseRunR);
        List caseRunList = iCaseRunEngine.selectCaseRunListByNew(caseRunR);
        if(caseRunList.size() == 0){//没有数据
            System.out.println("@@@没数据:"+caseRunList);
        }else {
            System.out.println("@@@有数据:"+caseRunList.size());
        }


    }

    @Test
    public void caseRun002Test(){
        //根据sceneId查询场景数据
        List<SceneManage> sceneManageList = sceneManageService.SceneManageListQueryById(27);
        System.out.println("场景列表: " + sceneManageList);


        //根据sceneId查询用例数据（组装前后置，入参，出参）
        JSONObject jsonObject = new JSONObject();
        CaseManage caseManage = new CaseManage();
        caseManage.setSceneId(String.valueOf(27));
        caseManage.setIsDelete(1);
        List<CaseManage> caseManageList = iCaseManageEngine.caseListQueryAllForScene(caseManage);
        System.out.println("用例列表: " + caseManageList);



        jsonObject.put("requests",caseManageList);
        System.out.println("@@@: " + jsonObject);


    }

    @Test
    public void ttt (){

        DubboCaseRequest testReq = new DubboCaseRequest();

        testReq.setAddress("address");
        testReq.setGroup("group");
        testReq.setVersion("version");
        testReq.setInterfaceName("interfaceName");
        testReq.setMethodName("methodName");
        testReq.setParamTypes(new String[]{"paramType"});
        Map params = JSONObject.parseObject("dubboParams");

        System.out.println("@@@:"+params);
        testReq.setParamObjs(new Object[]{params});

        JSONObject response = JSONObject.parseObject(RequestBase.dubboCaseInvoke(testReq));
        System.out.println("@@@:"+response.toString());


    }



    @Test
    public void saveParam_test(){
        ParamInfoDTO paramInfoEntity = new ParamInfoDTO();
        paramInfoEntity.setId(1);
        paramInfoEntity.setParamName("333");
        paramInfoEntity.setParamValue("444");
        paramInfoEntity.setBusinessId(3);
        paramInfoEntity.setBusinessName("微仓");
        paramInfoEntity.setAddressId(4);
        paramInfoEntity.setType(4);
        paramInfoEntity.setParamContent("select name from xxx  where id=xxcd");
        paramInfoEntity.setRemark("test2");
        paramInfoEntity.setAccess("${data.[2].id}");


        System.out.println(paramInfoService.updateParamInfo(paramInfoEntity));
    }
    @Test
    public void queryParam_test(){
        ParamInfoDTO paramInfoEntity = new ParamInfoDTO();
        paramInfoEntity.setBusinessId(3);
        paramInfoEntity.setPageNo(1);
        paramInfoEntity.setPageSize(10);
        System.out.println(JSONObject.toJSON(paramInfoService.searchParamByList(paramInfoEntity)));
    }

    @Test
    public void sqlByParam(){
        String addressId ="36";
        String dbSentance = "select job_name from job_info where job_id=203";
        String access = "$.data.[0].job_name";

        System.out.println("获取字段为："+sqlService.sqlByParam(addressId,dbSentance,access));
    }

    @Test
    public void doParam(){
        //根据应用id查询接口
        String appId = "20";
        JSONObject interfaceList = JSONObject.parseObject(ResourceManagement.queryListResInterfaceInfo(appId));
        System.out.println("###：" + interfaceList);
        JSONArray resultJsonArray = interfaceList.getJSONArray("result");
        for(int i =0;i<resultJsonArray.size();i++){
            JSONObject paramsJsonObject = resultJsonArray.getJSONObject(i).getJSONObject("params");
            String reqOther = paramsJsonObject.getString("reqOther");
            JSONArray jsonArray = new JSONArray();
            if(reqOther.equals("") ){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("dataType","");
                jsonObject.put("dataName","");
                jsonArray.add(jsonObject);
                paramsJsonObject.put("reqOther",jsonArray);
            }else {
                jsonArray = JSON.parseArray(reqOther);
                paramsJsonObject.put("reqOther",jsonArray);
            }

            System.out.println("@@@：" + paramsJsonObject);

        }

        System.out.println("&&&&:" + interfaceList);

    }

    @Test
    public void assertTest() {

        JSONObject newJson = new JSONObject();
        newJson.put("a",1);
        newJson.put("b",2);
        newJson.put("c",3);
        JSONObject newJson1 = new JSONObject();
        newJson1.put("a",11);
        newJson1.put("b",22);
        newJson1.put("c",33);
        JSONObject newJson2 = new JSONObject();
        newJson2.put("a",111);
        newJson2.put("b",222);
        newJson2.put("c",333);
        JSONArray jsonArray1 = new JSONArray();
        jsonArray1.add(newJson);
        jsonArray1.add(newJson1);
        jsonArray1.add(newJson2);
        System.out.println("@@@newJson:"+jsonArray1);

        JSONObject oldJSON = new JSONObject();
        oldJSON.put("a",1);
        oldJSON.put("b",2);
        oldJSON.put("c",3);
        JSONObject oldJSON1 = new JSONObject();
        oldJSON1.put("a",110);
        oldJSON1.put("b",22);
        oldJSON1.put("c",33);
        JSONObject oldJSON2 = new JSONObject();
        oldJSON2.put("a",1110);
        oldJSON2.put("b",222);
        oldJSON2.put("c",333);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(oldJSON);
        jsonArray.add(oldJSON1);
        jsonArray.add(oldJSON2);
        System.out.println("@@@oldJSON:"+jsonArray);

        List<String> oldValue = JsonPath.read(jsonArray,"$..a");
        System.out.println("@@@oldValue:"+oldValue);


        for(int n=0;n<oldValue.size();n++){
            String aaoldValue = String.valueOf(oldValue.get(n));
            System.out.println("@@@aaoldValue:"+aaoldValue);
            int i =0;

            for(int m=0;m<jsonArray1.size();m++){//遍历new，是否包含old中的值
                String bbnewValue = String.valueOf(jsonArray1.getJSONObject(m).getInteger("a"));
                System.out.println("@@@bbnewValue:"+bbnewValue);

                if(bbnewValue.equals(aaoldValue)){
                    i = 1;
                    System.out.println("@@@有值，更新数据:"+bbnewValue);//有值更新
                    break;
                }

            }

            System.out.println("@@@i的值:"+i);//有值更新

            if(i==0){
                System.out.println("@@@没有值，删除数据：" +aaoldValue);//有值更新

            }

        }











    }



}
