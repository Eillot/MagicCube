package com.simon.magiccube.web.controller;

import com.simon.magiccube.dao.domain.CaseParameterizationRelation;
import com.simon.magiccube.engine.impl.CaseParameterizationRelationEngineImp;
import com.simon.magiccube.facade.dto.CaseParameterizationRelationDTO;
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
 * @Explain : 供应链接口测试平台--【用例与参数化数据相关联】模块
 * @contact:
 * @Time : 2020/2/23 9:10 PM
 * @File : CaseParameterizationRelationController
 * @Software: IntelliJ IDEA 2018.1.8
 */

@RestController
@RequestMapping("/v1/caseParameterizationRelation")
public class CaseParameterizationRelationController {

    /**
     * 供应链接口测试平台--【用例与参数化数据相关联】模块(无前端页面)
     *
     */

    @Autowired
    private CaseParameterizationRelationEngineImp caseParameterizationRelationEngineImp;


    /**
     * 位置：【用例与参数化数据相关联】模块
     * 功能： 使用id查询（单个）用例与参数化数据相关联
     *
     * @param caseParameterizationRelation
     * @return
     *
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public CommonResult findCaseParameterizationRelationById(@RequestBody() CaseParameterizationRelation caseParameterizationRelation) {

        //@TODO 请求失败的check待统一封装完毕后在check
        CommonResult commonResult = new CommonResult();
        CaseParameterizationRelation result = caseParameterizationRelationEngineImp.selectCaseParameterizationRelationByID((int) caseParameterizationRelation.getId());
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.SELECT_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }

    /**
     * 位置：【用例与参数化数据相关联】模块
     * 功能： 查询所有用例与参数化数据相关联
     *
     * @param
     * @return
     *
     */
    @RequestMapping(value = "/queryAllById", method = RequestMethod.POST)
    public CommonResult findAllCaseParameterizationRelationById() {

        //@TODO 请求失败的check待统一封装完毕后在check
        CommonResult commonResult = new CommonResult();
        List<CaseParameterizationRelation> result = caseParameterizationRelationEngineImp.selectAllCaseParameterizationRelation();
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.SELECT_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }

    /**
     * 位置：【用例与参数化数据相关联】模块
     * 功能： 创建用例与参数化数据相关联
     *
     * @param caseParameterizationRelationDTO
     * @return
     *
     */
    @RequestMapping(value = "/createCaseParameterizationRelation", method = RequestMethod.POST)
    public CommonResult createCaseParameterizationRelation(@RequestBody() CaseParameterizationRelationDTO caseParameterizationRelationDTO) {

        //@TODO 请求失败的check待统一封装完毕后在check
        CommonResult commonResult = new CommonResult();
        int result = caseParameterizationRelationEngineImp.createCaseParameterizationRelation(caseParameterizationRelationDTO);
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.CREATE_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }

    /**
     * 位置：【用例与参数化数据相关联】模块
     * 功能： 修改用例与参数化数据相关联
     *
     * @param caseParameterizationRelationDTO
     * @return
     *
     */
    @RequestMapping(value = "/updateCaseParameterizationRelation", method = RequestMethod.POST)
    public CommonResult modifyCaseParameterizationRelation(@RequestBody() CaseParameterizationRelationDTO caseParameterizationRelationDTO) {

        //@TODO 请求失败的check待统一封装完毕后在check
        CommonResult commonResult = new CommonResult();
        int result = caseParameterizationRelationEngineImp.updateCaseParameterizationRelation(caseParameterizationRelationDTO);
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.UPDATE_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }

    /**
     * 位置：【用例与参数化数据相关联】模块
     * 功能： 删除用例与参数化数据相关联
     *
     * @TODO 暂时不开放次接口,慎重使用删除无法恢复,待加入角色，权限控制后，方可开放
     *
     * @param caseParameterizationRelationDTO
     * @return
     *
     */
    @RequestMapping(value = "/deleteCaseParameterizationRelationById", method = RequestMethod.POST)
    public CommonResult deleteCaseParameterizationRelation(@RequestBody() CaseParameterizationRelationDTO caseParameterizationRelationDTO) {

        //@TODO 请求失败的check待统一封装完毕后在check
        CommonResult commonResult = new CommonResult();
        int result = caseParameterizationRelationEngineImp.deleteCaseParameterizationRelation((int)caseParameterizationRelationDTO.getId());
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.DELETE_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }

}
