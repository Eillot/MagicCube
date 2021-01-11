package com.simon.magiccube.engine.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.simon.magiccube.dao.domain.*;
import com.simon.magiccube.dao.mapper.*;
import com.simon.magiccube.engine.SceneManageService;
import com.simon.magiccube.engine.common.ResourceManagement;
import com.simon.magiccube.facade.support.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/6/16 11:32 上午
 */
@Component

public class SceneManageServiceImpl implements SceneManageService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SceneManageMapper sceneManageMapper;
    @Autowired private CaseSceneRelationMapper caseSceneRelationMapper;
    @Autowired private TestDataMapper testDataMapper;
    @Autowired private AssertDataMapper assertDataMapper;
    @Autowired private CaseRunMapper caseRunMapper;

    @Override
    public CommonResult sceneList(SceneManage sceneManage) {
        CommonResult commonResult = new CommonResult();
        logger.info("查询场景入参是{}", sceneManage);
        sceneManage.setIsDelete(1);
        List<SceneManage> sceneManageList = sceneManageMapper.SceneManageQueryAll(sceneManage);

        PageInfo pageInfo = new PageInfo(sceneManageList);
        List result = new ArrayList();
        result.add(pageInfo);

        //遍历前后置数据，取出productId
        for(SceneManage sceneManageData : sceneManageList){
            //判断是否执行过，根据场景id查询用例执行表是否有数据
            String sceneId = String.valueOf(sceneManageData.getId());
            CaseRun caseRun = new CaseRun();
            caseRun.setSceneId(sceneId);
            CaseRun caseRunList = caseRunMapper.selectCaseRunByNew(caseRun);
            if(caseRunList == null){
                sceneManageData.setRunResult(0);
            }else {
                sceneManageData.setRunResult(caseRunList.getRunResult());
            }

            //查询出业务线名称
            String productId = sceneManageData.getProductId();
            JSONObject productList = JSONObject.parseObject(ResourceManagement.queryResBusinessInfoById(productId));
            String productName = String.valueOf(productList.getJSONObject("result").get("businessName"));
            sceneManageData.setProductName(productName);

            //查询应用名称
            String appId = sceneManageData.getAppId();
            JSONObject appList = JSONObject.parseObject(ResourceManagement.queryListResAppInfoByAppId(appId));
            String appName = String.valueOf(appList.getJSONArray("result").getJSONObject(0).get("appname"));
            sceneManageData.setAppName(appName);
            result.add(sceneManageData);
        }


        commonResult.setData(result);

        return commonResult;
    }

    @Override
    public CommonResult sceneAdd(SceneForm sceneForm) {
        CommonResult commonResult = new CommonResult();
        Date date = new Date();
        logger.info("新增场景入参是{}", sceneForm);

        if (CollectionUtils.isEmpty(sceneForm.getRunCaseFormList())) {
            commonResult.setMsg("非法参数.runCaseList");
            return commonResult;
        }

        // 往sceneManage中增加数据
        SceneManage sceneManage = new SceneManage();
        BeanUtils.copyProperties(sceneForm, sceneManage);
        sceneManage.setIsDelete(0L);
        sceneManage.setCreaterTime(date);
        sceneManage.setCreaterName("admin");

        sceneManageMapper.SceneManageInsert(sceneManage);

        int order = 0;
        // 循环CaseList
        for (SceneForm.CaseRelation caseRelation : sceneForm.getRunCaseFormList()) {
            this.insert(sceneManage, caseRelation, order++);
        }

        commonResult.setMsg("新增场景成功");
        return commonResult;
    }

    @Override
    public CommonResult sceneUpdate(SceneForm sceneForm) {
        CommonResult commonResult = new CommonResult();

        logger.info("编辑场景入参是{}", sceneForm);

        if (CollectionUtils.isEmpty(sceneForm.getRunCaseFormList())) {
            commonResult.setMsg("非法参数.runCaseList");
            return commonResult;
        }

        if (null == sceneForm.getId()) {
            commonResult.setMsg("非法参数.id");
            return commonResult;
        }

        SceneManage sceneManage = sceneManageMapper.SceneManageQueryById(sceneForm.getId());

        // 往sceneManage中增加数据
        BeanUtils.copyProperties(sceneForm, sceneManage);
        sceneManageMapper.SceneManageUpdate(sceneManage);

        int order = 0;
        // 循环CaseList
        for (com.simon.magiccube.dao.domain.SceneForm.CaseRelation caseRelation : sceneForm.getRunCaseFormList()) {

            if (null == caseRelation.getId()) {

                // 没有id就是新增
                this.insert(sceneManage, caseRelation, order);
            } else {

                // 设置了删除就删除数据
                if (caseRelation.getDelete()) {
                    caseSceneRelationMapper.deleteByPrimaryKey(caseRelation.getId());
                    testDataMapper.TestDataDeleteById(caseRelation.getTestDataId());
                    assertDataMapper.AssertDataDeleteById(caseRelation.getAssertDataId());
                } else {

                    // 否则就是更新
                    CaseSceneRelation caseSceneRelation =
                            caseSceneRelationMapper.selectByPrimaryKey(caseRelation.getId());
                    caseSceneRelation.setCaseOrder(order);
                    caseSceneRelationMapper.updateByPrimaryKey(caseSceneRelation);

                    TestData testData = testDataMapper.TestDataQueryById(caseRelation.getTestDataId());
                    testData.setCaseDataContent(caseRelation.getCaseDataContent());
                    testData.setInputFormat(caseRelation.getInputFormat());
                    testDataMapper.TestDataUpdate(testData);

                    AssertData assertData = assertDataMapper.findById(caseRelation.getAssertDataId());
                    assertData.setMatchRules(caseRelation.getMatchRules());
                    assertData.setExpectedValue(caseRelation.getExpectedValue());
                    assertData.setAssertTarget(caseRelation.getAssertTarget());
                    assertData.setAssertDes(caseRelation.getAssertDes());
                    assertDataMapper.AssertDataUpdate(assertData);
                }
            }

            order++;
        }

        commonResult.setMsg("新增编辑成功");
        return commonResult;
    }

    @Override
    public CommonResult sceneDelete(SceneManage sceneManage) {
        CommonResult commonResult = new CommonResult();

        sceneManage.setIsDelete(1L);

        logger.info("删除场景入参是{}", sceneManage.getId());

        List<Object> list = Collections.singletonList(sceneManageMapper.SceneManageUpdate(sceneManage));

        commonResult.setData(list);
        return commonResult;
    }

    @Override
    public CommonResult sceneExecute(Integer sceneId) {
        CommonResult commonResult = new CommonResult();

        logger.info("执行场景入参是{}", sceneId);

        // 根据sceneId查询到需要执行的用例
        List<String> runIdList = caseSceneRelationMapper.listCaseIdBySceneId(String.valueOf(sceneId));

        // TODO: 2020/5/28 执行用例
        //    for (int i = 0; i < runIdList.size(); i++) {}
        commonResult.setData(runIdList);
        return commonResult;
    }

    @Override
    public CommonResult sceneCopy(Integer sceneId) {
        CommonResult commonResult = new CommonResult();

        logger.info("复制场景入参是{}", sceneId);

        SceneManage sceneManage = sceneManageMapper.SceneManageQueryById(sceneId);

        sceneManage.setId(null);
        sceneManageMapper.SceneManageInsert(sceneManage);

        List<CaseSceneRelation> list = caseSceneRelationMapper.findAllBySceneId(sceneId.longValue());
        for (CaseSceneRelation caseSceneRelation : list) {
            Integer caseId = caseSceneRelation.getId();

            caseSceneRelation.setId(null);
            caseSceneRelationMapper.insert(caseSceneRelation);

            TestData testData = testDataMapper.TestDataQueryByCaseId(caseId.toString());
            Long testDataId = testData.getId();
            testData.setId(null);
            testDataMapper.TestDataInsert(testData);

            AssertData assertData = assertDataMapper.AssertDataQueryBytestDataId(testDataId.toString());
            assertData.setId(null);
            assertDataMapper.AssertDataInsert(assertData);
        }
        commonResult.setMsg("复制成功");
        return commonResult;
    }

    @Override
    public CommonResult sceneDetail(Integer sceneId) {
        CommonResult commonResult = new CommonResult();

        logger.info("查看场景详情入参是{}", sceneId);

        SceneManage sceneManage = sceneManageMapper.SceneManageQueryById(sceneId);

        List<SceneManage> list = sceneManageMapper.SceneManageQueryAll(sceneManage);
        commonResult.setData(list);
        return commonResult;
    }

    @Override
    public int SceneManageInsert(SceneManage sceneManage) {
        return sceneManageMapper.SceneManageInsert(sceneManage);
    }

    @Override
    public int SceneManageDelete(Integer id) {
        return sceneManageMapper.SceneManageDelete(id);
    }

    @Override
    public List<SceneManage> SceneManageListQueryById(Integer id) {
        List result = new ArrayList();
        SceneManage sceneManage = new SceneManage();
        List<SceneManage> sceneManageList = sceneManageMapper.SceneManageListQueryById(id);

        for(SceneManage scenenManageNewList : sceneManageList){
            String productId = scenenManageNewList.getProductId();
            String appId = scenenManageNewList.getAppId();
            //查询出业务线名称
            JSONObject productList = JSONObject.parseObject(ResourceManagement.queryResBusinessInfoById(productId));
            String productName = String.valueOf(productList.getJSONObject("result").get("businessName"));
            scenenManageNewList.setProductName(productName);

            //查询应用名称
            JSONObject appList = JSONObject.parseObject(ResourceManagement.queryListResAppInfoByAppId(appId));
            String appName = String.valueOf(appList.getJSONArray("result").getJSONObject(0).get("appname"));
            scenenManageNewList.setAppName(appName);

            result.add(scenenManageNewList);
        }


        return result;
    }

    @Override
    public int SceneManageUpdate(SceneManage sceneManage) {
        return sceneManageMapper.SceneManageUpdate(sceneManage);
    }

    private void insert(SceneManage sceneManage, SceneForm.CaseRelation caseRelation, int order) {
        Date date = new Date();

        // 往caseSceneRelation表中插入数据
        CaseSceneRelation caseSceneRelation = new CaseSceneRelation();
        caseSceneRelation.setRunObjectType(1);
        caseSceneRelation.setSceneId(String.valueOf(sceneManage.getId()));
        caseSceneRelation.setCaseId(caseRelation.getCaseId());
        caseSceneRelation.setCaseOrder(order);
        caseSceneRelation.setIsDelete((byte) 0);
        caseSceneRelationMapper.insertSelective(caseSceneRelation);

        // testData表里插入数据
        TestData testData = new TestData();
        testData.setCaseId(caseRelation.getCaseId());
        testData.setCaseDataContent(caseRelation.getCaseDataContent());
        testData.setCreaterName("admin");
        testData.setCreaterTime(date);
        testData.setIsDelete(1L);
        testData.setInputFormat(caseRelation.getInputFormat());
        testData.setSceneId(String.valueOf(sceneManage.getId()));
        testDataMapper.TestDataInsert(testData);

        // assertData表里插入数据
        AssertData assertData = new AssertData();
        assertData.setAssertDes(caseRelation.getAssertDes());
        assertData.setAssertTarget(caseRelation.getAssertTarget());
        assertData.setCreaterName("admin");
        assertData.setCreaterTime(date);
        assertData.setExpectedValue(caseRelation.getExpectedValue());
        assertData.setIsDelete(1);
        assertData.setMatchRules(caseRelation.getMatchRules());
        assertData.setSceneId(String.valueOf(sceneManage.getId()));
        assertDataMapper.AssertDataInsert(assertData);
    }
}
