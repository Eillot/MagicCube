package com.simon.magiccube.web.controller;

import com.simon.magiccube.dao.domain.ParameterizationData;
import com.simon.magiccube.engine.impl.ParameterizationDataEngineImp;
import com.simon.magiccube.facade.dto.ParameterizationDataDTO;
import com.simon.magiccube.facade.support.CommonResult;
import com.simon.magiccube.web.constants.HttpResultConstants;
import com.simon.magiccube.web.constants.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain : 供应链接口测试平台--【配置管理】-【参数化管理】模块
 * @contact:
 * @Time : 2020/2/22 4:59 PM
 * @File : ParameterizationDataController
 * @Software: IntelliJ IDEA 2018.1.8
 */

@RestController
@RequestMapping("/v1/parameterizationDataManage")
public class ParameterizationDataController {

    /**
     * 供应链接口测试平台--【配置管理】
     */
    @Autowired
    private ParameterizationDataEngineImp parameterizationDataEngineImp;

    /**
     * 位置：【配置管理】-【参数化管理】模块
     * 功能： 使用参数化管理id查询（单个）参数化管理
     *
     * @param parameterizationData
     * @return
     *
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public CommonResult findParameterizationDataById(@RequestBody() ParameterizationData parameterizationData) {

        //@TODO 请求失败的check待统一封装完毕后在check
        ParameterizationData result = parameterizationDataEngineImp.parameterizationDataQueryById((int) parameterizationData.getId());
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.SELECT_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }

    /**
     * 位置：【配置管理】-【参数化管理】模块
     * 功能： 查询所有参数化管理
     *
     * @return
     *
     */
    @RequestMapping(value = "/queryAllById", method = RequestMethod.POST)
    public CommonResult findAllparameterizationDataById() {
        //@TODO 请求失败的check待统一封装完毕后在check
        CommonResult commonResult = new CommonResult();
        List<ParameterizationData> dataList = parameterizationDataEngineImp.parameterizationDataQueryAll();
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.SELECT_SUCCESS);
        commonResult.setData(dataList);
        return commonResult;
    }

    /**
     * 位置：【配置管理】-【参数化管理】模块
     * 功能： 创建参数化管理
     *
     * @param parameterizationDataDTO
     * @return
     *
     */
    @RequestMapping(value = "/createParameterizationData", method = RequestMethod.POST)
    public CommonResult createParameterizationData(@RequestBody() ParameterizationDataDTO parameterizationDataDTO) {
        //@TODO 请求失败的check待统一封装完毕后在check
        CommonResult commonResult = new CommonResult();
        int result = parameterizationDataEngineImp.parameterizationDataInsert(parameterizationDataDTO);
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.CREATE_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }


    /**
     * 位置：【配置管理】-【参数化管理】模块
     * 功能： 修改参数化管理
     *
     * @return
     *
     */
    @RequestMapping(value = "/updateParameterizationData", method = RequestMethod.POST)
    public CommonResult modifyParameterizationData(@RequestBody() ParameterizationData parameterizationData) {

        //@TODO 请求失败的check待统一封装完毕后在check
        CommonResult commonResult = new CommonResult();
        int result = parameterizationDataEngineImp.parameterizationDataUpdate(parameterizationData);
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.UPDATE_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }


    /**
     * 位置：【配置管理】-【参数化管理】模块
     * 功能： 删除参数化管理
     *
     * @param parameterizationDataDTO
     * @return
     *
     */
    @RequestMapping(value = "/deleteParameterizationData", method = RequestMethod.POST)
    public CommonResult deleteParameterizationData(@RequestBody() ParameterizationDataDTO parameterizationDataDTO) {

        //@TODO 请求失败的check待统一封装完毕后在check
        CommonResult commonResult = new CommonResult();
        int result = parameterizationDataEngineImp.parameterizationDataDeleteById((int)parameterizationDataDTO.getId());
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.DELETE_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }


}
