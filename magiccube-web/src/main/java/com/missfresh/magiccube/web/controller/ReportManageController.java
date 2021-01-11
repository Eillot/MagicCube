package com.simon.magiccube.web.controller;

import com.simon.magiccube.dao.domain.ParameterizationData;
import com.simon.magiccube.dao.domain.ReportManage;
import com.simon.magiccube.engine.impl.ReportManageEngineImp;
import com.simon.magiccube.facade.support.CommonResult;
import com.simon.magiccube.web.constants.HttpResultConstants;
import com.simon.magiccube.web.constants.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2020/4/2 7:31 PM
 * @File : ReportManageController
 * @Software: IntelliJ IDEA 2018.1.8
 */

@RestController
@RequestMapping("/v1/ReportManage")
public class ReportManageController {

    @Autowired
    private ReportManageEngineImp reportManageEngineImp;


    @RequestMapping(value = "/updateBycaseId", method = RequestMethod.POST)
    public CommonResult findParameterizationDataById(@RequestBody() ReportManage reportManage) {

        //@TODO 请求失败的check待统一封装完毕后在check
        int result = reportManageEngineImp.ReportManageUpdateAssertByCaseId(reportManage);
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.SELECT_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }
}
