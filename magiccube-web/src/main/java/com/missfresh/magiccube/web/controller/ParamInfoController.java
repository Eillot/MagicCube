package com.simon.magiccube.web.controller;
/*
*@author  simon
*@data 2020/7/24 11:09
*/

import com.simon.magiccube.dao.domain.ParamInfoEntity;
import com.simon.magiccube.dao.domain.res.ParamInfoListRes;
import com.simon.magiccube.engine.ParamInfoService;
import com.simon.magiccube.facade.dto.ParamInfoDTO;
import com.simon.magiccube.facade.support.CommonResult;
import com.simon.magiccube.web.constants.HttpResultConstants;
import com.simon.magiccube.web.constants.MessageConstants;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class ParamInfoController {

    @Resource
    private ParamInfoService paramInfoService;

    @RequestMapping(value = "/save/paraminfo",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public CommonResult saveParamInfo(@RequestBody ParamInfoDTO paramInfoDTO){
        CommonResult commonResult = new CommonResult();
        String result = paramInfoService.saveParamInfo(paramInfoDTO);
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.REQUEST_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }

    @RequestMapping(value = "/update/paraminfo",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public CommonResult updateParamInfo(@RequestBody ParamInfoDTO paramInfoDTO){
        CommonResult commonResult = new CommonResult();
        String result = paramInfoService.updateParamInfo(paramInfoDTO);
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.REQUEST_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }


    @RequestMapping(value = "/query/paramdetail",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult queryParamDetail(int id){
        CommonResult commonResult = new CommonResult();
        List<ParamInfoEntity> result = paramInfoService.searchById(id);
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.SELECT_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }

    @RequestMapping(value = "/del/paraminfo",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delParamInfo(int id){
        CommonResult commonResult = new CommonResult();
        String result = paramInfoService.delParamInfo(id);
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.REQUEST_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }

    @RequestMapping(value = "/query/list/paraminfo",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public CommonResult queryParamByList(@RequestBody ParamInfoDTO paramInfoDTO){
        CommonResult commonResult = new CommonResult();
        ParamInfoListRes result = paramInfoService.searchParamByList(paramInfoDTO);
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.SELECT_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }

    @RequestMapping(value = "/do/paramActive",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult doParamActive(int id){
        CommonResult commonResult = new CommonResult();
        Object result = paramInfoService.doParam(id);
        commonResult.setStatus(MessageConstants.STATUS_SUCCESS);
        commonResult.setCode(HttpResultConstants.SUCCESS_CODE);
        commonResult.setMsg(MessageConstants.REQUEST_SUCCESS);
        commonResult.setData(result);
        return commonResult;
    }
}
