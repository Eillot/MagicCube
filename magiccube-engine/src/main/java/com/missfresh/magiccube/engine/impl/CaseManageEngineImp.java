package com.simon.magiccube.engine.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.magiccube.dao.domain.*;
import com.simon.magiccube.dao.mapper.CaseManageMapper;
import com.simon.magiccube.dao.mapper.CaseRunMapper;
import com.simon.magiccube.engine.IAssertDataEngine;
import com.simon.magiccube.engine.ICaseManageEngine;
import com.simon.magiccube.engine.ICaseRunEngine;
import com.simon.magiccube.engine.ITestDataEngine;
import com.simon.magiccube.engine.common.ResourceManagement;
import com.simon.magiccube.engine.util.DTOtoEntity;
import com.simon.magiccube.facade.dto.CaseManageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 4:20 PM
 * @File : CaseManageEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */

@Component
public class CaseManageEngineImp implements ICaseManageEngine {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private CaseManageMapper caseManageMapper;
    private CaseManage caseManage = new CaseManage();

    @Autowired
    private ICaseRunEngine iCaseRunEngine;

    @Autowired
    private ITestDataEngine iTestDataEngine;

    @Autowired
    private IAssertDataEngine iAssertDataEngine;


    @Override
    public List<CaseManage> selectAllCase(CaseManage caseManage) {
        List<CaseManage> caseManageList = caseManageMapper.CaseQueryAll(caseManage);
        return caseManageList;
    }

    @Override
    public List<CaseManage> selectAllCaseBySceneId(CaseManage caseManage) {
        List<CaseManage> caseManageList = caseManageMapper.CaseQueryAll(caseManage);
        List result = new ArrayList();

        for(CaseManage caseManageData : caseManageList){
            //判断是否执行过，根据用例id查询用例执行表是否有数据
            String caseId = String.valueOf(caseManageData.getId());
            CaseRun caseRunList = iCaseRunEngine.selectCaseRunByCaseIdNew(caseId);
            if(caseRunList == null){
                caseManageData.setRunResult(0);
            }else {
                caseManageData.setRunResult(Long.parseLong(String.valueOf(caseRunList.getRunResult())));
            }
//            caseManageData.setRunBeginTime(String.valueOf(caseRunList.getCreaterTime()));

            //查询出业务线名称
            String productId = caseManageData.getProductId();
            JSONObject productList = JSONObject.parseObject(ResourceManagement.queryResBusinessInfoById(productId));
            String productName = String.valueOf(productList.getJSONObject("result").get("businessName"));
            caseManageData.setProductName(productName);

            //查询应用名称
            String appId = caseManageData.getAppId();
            JSONObject appList = JSONObject.parseObject(ResourceManagement.queryListResAppInfoByAppId(appId));
            String appName = String.valueOf(appList.getJSONArray("result").getJSONObject(0).get("appname"));
            caseManageData.setAppName(appName);

            //查询接口地址
            String interfaceId = caseManageData.getInterfaceId();
            JSONObject interfaceList = JSONObject.parseObject(ResourceManagement.queryResInterfaceInfoDetail(interfaceId));
            String interUrl = String.valueOf(interfaceList.getJSONObject("result").get("interUrl"));
            String interName = String.valueOf(interfaceList.getJSONObject("result").get("interName"));
            String methodName = String.valueOf(interfaceList.getJSONObject("result").get("methodName"));
            caseManageData.setInterUrl(interUrl);
            caseManageData.setInterName(interName);
            caseManageData.setMethodName(methodName);

            result.add(caseManageData);

        }
        logger.info("result:"+result);


        return result;
    }

    @Override
    public List<CaseManage> caseManageQueryAll(CaseManage caseManage) {
        List<CaseManage> caseManageList = caseManageMapper.CaseManageQueryAll(caseManage);
        return caseManageList;
    }

    @Override
    public List<CaseManage> caseQueryListBySceneId(String sceneId) {
        List<CaseManage> caseManageList = caseManageMapper.caseQueryListBySceneId(sceneId);
        return caseManageList;
    }

    @Override
    public List<CaseManage> caseQueryByCaseName(String caseName) {
        List<CaseManage> caseQueryByCaseName = caseManageMapper.CaseQueryByCaseName(caseName);
        return caseQueryByCaseName;
    }

    @Override
    public List selectCaseById(Integer id) {
        List caseManageListByID = caseManageMapper.CaseManageQueryById(id);
        return caseManageListByID;
    }

    @Override
    public CaseManage queryCaseById(Integer id) {
        CaseManage cas= caseManageMapper.caseQueryListById(id);
        return cas;
    }

    @Override
    public List<CaseManage> caseListQueryAll(CaseManage caseManage) {
        PageHelper.startPage(caseManage.getPageNum(),caseManage.getPageSize());

        List<CaseManage> caseManageList = caseManageMapper.CaseQueryAll(caseManage);

        PageInfo pageInfo = new PageInfo(caseManageList);

        List result = new ArrayList();
        result.add(pageInfo);

        //遍历前后置数据，取出productId
        for(CaseManage caseManageData : caseManageList){
            //判断是否执行过，根据用例id查询用例执行表是否有数据
            String caseId = String.valueOf(caseManageData.getId());
            CaseRun caseRunList = iCaseRunEngine.selectCaseRunByCaseIdNew(caseId);
            if(caseRunList == null){
                caseManageData.setRunResult(0);
            }else {
                caseManageData.setRunResult(Long.parseLong(String.valueOf(caseRunList.getRunResult())));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(caseRunList.getCreaterTime());
                caseManageData.setRunBeginTime(dateString);
            }

            //查询出业务线名称
            String productId = caseManageData.getProductId();
            JSONObject productList = JSONObject.parseObject(ResourceManagement.queryResBusinessInfoById(productId));
            String productName = String.valueOf(productList.getJSONObject("result").get("businessName"));
            caseManageData.setProductName(productName);

            //查询应用名称
            String appId = caseManageData.getAppId();
            JSONObject appList = JSONObject.parseObject(ResourceManagement.queryListResAppInfoByAppId(appId));
            String appName = String.valueOf(appList.getJSONArray("result").getJSONObject(0).get("appname"));
            caseManageData.setAppName(appName);

            //查询接口地址
            String interfaceId = caseManageData.getInterfaceId();
            JSONObject interfaceList = JSONObject.parseObject(ResourceManagement.queryResInterfaceInfoDetail(interfaceId));
            String interUrl = String.valueOf(interfaceList.getJSONObject("result").get("interUrl"));
            String interName = String.valueOf(interfaceList.getJSONObject("result").get("interName"));
            String methodName = String.valueOf(interfaceList.getJSONObject("result").get("methodName"));
            String dubboInterfaceGroup = String.valueOf(interfaceList.getJSONObject("result").get("dubboInterfaceGroup"));
            String dubboInterfaceVersion = String.valueOf(interfaceList.getJSONObject("result").get("dubboInterfaceVersion"));
            String reqDubbo = String.valueOf(interfaceList.getJSONObject("result").getJSONObject("params").get("reqDubbo"));

            caseManageData.setInterUrl(interUrl);
            caseManageData.setInterName(interName);
            caseManageData.setMethodName(methodName);
            caseManageData.setDubboInterfaceGroup(dubboInterfaceGroup);
            caseManageData.setDubboInterfaceVersion(dubboInterfaceVersion);
            caseManageData.setReqDubbo(reqDubbo);


            result.add(caseManageData);

        }

        logger.info("result:"+result);
        return result;
    }

    @Override
    public List<CaseManage> caseListQueryAllForScene(CaseManage caseManage) {
        List<CaseManage> caseManageList = caseManageMapper.CaseQueryAll(caseManage);
        List result = new ArrayList();

        //遍历前后置数据，取出productId
        for(CaseManage caseManageData : caseManageList){
            //判断是否执行过，根据用例id查询用例执行表是否有数据
            String caseId = String.valueOf(caseManageData.getId());

            //查询出业务线名称
            String productId = caseManageData.getProductId();
            JSONObject productList = JSONObject.parseObject(ResourceManagement.queryResBusinessInfoById(productId));
            String productName = String.valueOf(productList.getJSONObject("result").get("businessName"));
            caseManageData.setProductName(productName);

            //查询应用名称
            String appId = caseManageData.getAppId();
            JSONObject appList = JSONObject.parseObject(ResourceManagement.queryListResAppInfoByAppId(appId));
            String appName = String.valueOf(appList.getJSONArray("result").getJSONObject(0).get("appname"));
            caseManageData.setAppName(appName);

            //查询接口地址
            String interfaceId = caseManageData.getInterfaceId();
            JSONObject interfaceList = JSONObject.parseObject(ResourceManagement.queryResInterfaceInfoDetail(interfaceId));
            String interUrl = String.valueOf(interfaceList.getJSONObject("result").get("interUrl"));
            String interName = String.valueOf(interfaceList.getJSONObject("result").get("interName"));
            String methodName = String.valueOf(interfaceList.getJSONObject("result").get("methodName"));
            String dubboInterfaceGroup = String.valueOf(interfaceList.getJSONObject("result").get("dubboInterfaceGroup"));
            String dubboInterfaceVersion = String.valueOf(interfaceList.getJSONObject("result").get("dubboInterfaceVersion"));
            String reqDubbo = String.valueOf(interfaceList.getJSONObject("result").getJSONObject("params").get("reqDubbo"));

            caseManageData.setInterUrl(interUrl);
            caseManageData.setInterName(interName);
            caseManageData.setMethodName(methodName);
            caseManageData.setDubboInterfaceGroup(dubboInterfaceGroup);
            caseManageData.setDubboInterfaceVersion(dubboInterfaceVersion);
            caseManageData.setReqDubbo(reqDubbo);

            //根据用例id去入参表里查询数据
            TestData testData = new TestData();
            testData.setCaseId(caseId);
            testData.setIsDelete(1);
            testData.setSceneId(caseManage.getSceneId());
            List<TestData> testDataList = iTestDataEngine.TestDataQueryAll(testData);
            String baseDataParam = testDataList.get(0).getBaseDataParam();
            JSONArray jsonArray = new JSONArray();
            if(baseDataParam == null || "".equals(baseDataParam)){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("dataType","dataTypeNull");
                jsonObject.put("dataName","无基础数据类型入参，无需填写");
                jsonArray.add(jsonObject);
                baseDataParam = String.valueOf(jsonArray);
            }
            testDataList.get(0).setBaseDataParam(baseDataParam);
            logger.info("入参数据: " + testDataList);
            caseManageData.setCaseManageForTestDataList(testDataList);

            //根据入参id去断言表里查询数据
            AssertData assertData = new AssertData();
            for (TestData testData1 : testDataList) {
                String testDataId = String.valueOf(testData1.getId());
                assertData.setTestDataId(testDataId);
                assertData.setIsDelete(1);
                List<AssertData> assertDataList = iAssertDataEngine.AssertDataQueryAll(assertData);
                JSONArray jsonArrayAssertData = JSONArray.parseArray(JSON.toJSONString(assertDataList));

                caseManageData.setCaseManageForAssertDataList(String.valueOf(jsonArrayAssertData));
            }

            result.add(caseManageData);

        }

        logger.info("result:"+result);
        return result;
    }

    @Override
    public List<CaseManage> selectCaseId() {
        List<CaseManage> caseIdList = caseManageMapper.SelectCaseId();
        return caseIdList;
    }

    @Override
    public int caseManageInsert(CaseManage caseManage) {
        int caseManageInsert = caseManageMapper.CaseManageInsert(caseManage);
        return caseManageInsert;
    }

    @Override
    public int CaseManageUpdate(CaseManage caseManage) {
        int caseManageUpdate = caseManageMapper.CaseManageUpdate(caseManage);
        return caseManageUpdate;
    }

    @Override
    public int CaseManageDelete(Integer id) {
        int caseManageDelete = caseManageMapper.CaseManageDelete(id);
        return caseManageDelete;
    }

    @Override
    public int selectCaseListPageTotal(CaseManage caseManage) {
        return caseManageMapper.selectCaseListPageTotal(caseManage);
    }


}
