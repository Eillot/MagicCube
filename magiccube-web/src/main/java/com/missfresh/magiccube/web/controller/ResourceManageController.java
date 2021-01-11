package com.simon.magiccube.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.engine.common.ResourceManagement;
import com.simon.magiccube.facade.support.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/6/19 10:44 上午
 */
@RestController
@CrossOrigin
@RequestMapping("/resourceManage")
public class ResourceManageController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceManageController.class);


    /**
     * 查询业务线
     *
     * @return
     */
    @RequestMapping(value = "/productQuery", method = RequestMethod.POST)
    public CommonResult productQuery() {
        CommonResult commonResult = new CommonResult();
        //查询业务线
        JSONObject productList = JSONObject.parseObject(ResourceManagement.queryListResBusinessInfo());
        logger.info("查询业务线列表: " + productList);
        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(productList);
        commonResult.setStatus("200");
        return commonResult;

    }

    /**
     * 根据业务线查询应用
     *
     * @return
     */
    @GetMapping(value = "/appQuery")
    public CommonResult appQuery(@RequestBody() @RequestParam(required = false) String productId) {
        CommonResult commonResult = new CommonResult();

        //查询业务线
        JSONObject appList = JSONObject.parseObject(ResourceManagement.queryListResAppInfo(productId));
        logger.info("根据业务线查询应用列表: " + appList);
        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(appList);
        commonResult.setStatus("200");
        return commonResult;

    }

    /**
     * 用例执行-选择执行环境
     *
     * @return
     */
    @RequestMapping(value = "/environmentSelect", method = RequestMethod.POST)
    public CommonResult environmentSelect() {
        CommonResult commonResult = new CommonResult();
        JSONObject environmentList = JSONObject
                .parseObject(ResourceManagement.queryListResServerenvInfo());
        logger.info("运行环境返回结果：" + environmentList);
        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(environmentList);
        commonResult.setStatus("200");
        return commonResult;

    }

    /**
     * 根据接口id查询接口
     * @param interfaceId
     * @return
     */
    @GetMapping(value = "/interfaceSelect")
    public CommonResult interfaceSelect(@RequestBody() @RequestParam(required = false) String interfaceId){
        CommonResult commonResult = new CommonResult();
        JSONObject interfaceList = JSONObject.parseObject(ResourceManagement.queryResInterfaceInfoDetail(interfaceId));
        logger.info("查询接口返回结果：" + interfaceList);
        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(interfaceList);
        commonResult.setStatus("200");
        return commonResult;
    }

}
