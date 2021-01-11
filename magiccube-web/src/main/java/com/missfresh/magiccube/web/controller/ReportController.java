package com.simon.magiccube.web.controller;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/3/25 8:38 下午
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.dao.domain.AssertData;
import com.simon.magiccube.dao.domain.CaseManage;
import com.simon.magiccube.dao.domain.ReportManage;
import com.simon.magiccube.dao.domain.SceneRun;
import com.simon.magiccube.engine.IAssertDataEngine;
import com.simon.magiccube.engine.ICaseManageEngine;
import com.simon.magiccube.engine.IReportManageEngine;
import com.simon.magiccube.engine.ISceneRunEngine;
import com.simon.magiccube.facade.support.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * 报告Controller
 */
@RestController
@CrossOrigin
@RequestMapping("/report")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private IReportManageEngine iReportManageEngine;

    @Autowired
    private ISceneRunEngine iSceneRunEngine;

    @Autowired
    private ICaseManageEngine iCaseManageEngine;

    @Autowired
    private IAssertDataEngine iAssertDataEngine;

    @GetMapping(value = "/detail")
    public CommonResult reportQuery(@RequestBody() @RequestParam  @Valid Integer caseId) {
        CommonResult commonResult = new CommonResult();
        HashMap hashMap = new HashMap();

        ReportManage reportManage = new ReportManage();
        reportManage.setCaseId(String.valueOf(caseId));
        reportManage.setIsDelete(1);
        List<ReportManage> reportManageList = iReportManageEngine.ReportManageQueryAllNew(reportManage);
        logger.info("报告查询结果："+reportManageList);


        hashMap.put("reportManageList",reportManageList);
        commonResult.setCode("0");
        commonResult.setData(hashMap);
        commonResult.setStatus("200");
        return commonResult;
    }


    /**
     * 场景报告
     * @param sceneId
     * @return
     */
    @GetMapping(value = "/sceneReport")
    public CommonResult sceneReport(@RequestBody() @RequestParam  @Valid Integer sceneId){
        CommonResult commonResult = new CommonResult();
        HashMap hashMap = new HashMap();

        //查询场景执行表
        SceneRun sceneRun = new SceneRun();
        sceneRun.setSceneId(String.valueOf(sceneId));
        List<SceneRun> sceneRunList = iSceneRunEngine.selectSceneRunByNew(sceneRun);
        logger.info("场景执行记录："+sceneRunList);
        hashMap.put("sceneRunList",sceneRunList);

        //根据场景id查询用例列表
        CaseManage caseManage = new CaseManage();
        caseManage.setSceneId(String.valueOf(sceneId));
        caseManage.setIsDelete(1);
        List<CaseManage> caseManageList = iCaseManageEngine.selectAllCaseBySceneId(caseManage);
        logger.info("场景下的用例列表："+caseManageList);
        hashMap.put("caseManageList",caseManageList);


        commonResult.setCode("0");
        commonResult.setData(hashMap);
        commonResult.setStatus("200");
        return commonResult;
    }




}
