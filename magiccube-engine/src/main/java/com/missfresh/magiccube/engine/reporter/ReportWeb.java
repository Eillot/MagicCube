package com.simon.magiccube.engine.reporter;

import com.simon.magiccube.dao.domain.CaseRun;
import com.simon.magiccube.dao.domain.ReportManage;
import com.simon.magiccube.dao.domain.SceneRun;
import com.simon.magiccube.engine.ICaseRunEngine;
import com.simon.magiccube.engine.IReportManageEngine;
import com.simon.magiccube.engine.ISceneRunEngine;
import com.simon.magiccube.engine.common.assertResultCommon.AssertBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/6/10 10:46 上午
 */
@Component
public class ReportWeb {
    private static final Logger logger = LoggerFactory.getLogger(ReportWeb.class);

    @Autowired
    private IReportManageEngine iReportManageEngine;

    @Autowired
    private ICaseRunEngine iCaseRunEngine;

    @Autowired
    private ISceneRunEngine iSceneRunEngine;

    /**
     * web报告组装--用例执行
     */
    public void reportAssembleByCaseRun(String caseId,String caseName,String interfaceUrl,String serverName,int statusCode,String caseData,String assertId,String assertResultAndDes,String caseResponseResult,String testDataId){
        CaseRun caseRunList = iCaseRunEngine.selectCaseRunByCaseIdNew(caseId);
        int runResult = caseRunList.getRunResult();

        ReportManage reportManage = new ReportManage();
        reportManage.setCaseId(caseId);
        reportManage.setCaseName(caseName);
        reportManage.setInterfaceUrl(interfaceUrl);
        reportManage.setEnvironment(serverName);
        reportManage.setRunResult(runResult);//用例执行结果-前端用不到
        reportManage.setResponseTime(1);//耗时先预留
        reportManage.setStatusCode(statusCode);
        reportManage.setAssertResultAndDes(assertResultAndDes);//断言返回结果及描述
        reportManage.setAssertId(assertId);
        reportManage.setCaseResponseResult(caseResponseResult);//用例返回结果
        reportManage.setTestDataId(testDataId);
        reportManage.setCaseDataContent(caseData);
        reportManage.setCreaterTime(new Date());
        reportManage.setIsDelete(1);
        iReportManageEngine.reportManageInsert(reportManage);
        logger.info("用例报告数据插入成功");


    }


    public void reportAssembleBySceneRun(String sceneId,String serverName,int caseTotal,int caseSuccessTotal,int caseFailTotal,int sceneRunResult){

        SceneRun sceneRun = new SceneRun();
        sceneRun.setSceneRunResult(sceneRunResult);
        sceneRun.setSceneId(sceneId);
        sceneRun.setCaseTotal(caseTotal);
        sceneRun.setCaseSuccessTotal(caseSuccessTotal);
        sceneRun.setCaseFailTotal(caseFailTotal);
        sceneRun.setEnvironmentId(serverName);
        sceneRun.setResponseTime(1);//耗时先预留
//        sceneRun.setResponseResult("");
        sceneRun.setCreaterTime(new Date());
        sceneRun.setIsDelete(1);
        iSceneRunEngine.insertSceneRun(sceneRun);
        logger.info("场景报告数据插入成功");


    }





}
