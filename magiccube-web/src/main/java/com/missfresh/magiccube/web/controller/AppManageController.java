package com.simon.magiccube.web.controller;

import com.simon.magiccube.engine.impl.AppManageEngineImp;
import com.simon.magiccube.facade.dto.AppManageDTO;
import com.simon.magiccube.facade.support.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/30 6:12 PM
 * @File : AppManageController
 * @Software: IntelliJ IDEA 2018.1.8
 */
@RestController
@RequestMapping("/v1/app")
public class AppManageController {

    @Autowired
    private AppManageEngineImp appManageEngineImp;

    @RequestMapping(value = "/appmanage/create", method = RequestMethod.POST)
    public CommonResult appManage(@RequestBody() AppManageDTO appManageDTO) throws ServletException {
        CommonResult commonResult = new CommonResult();
        int resultcode = appManageEngineImp.createApp(appManageDTO);
        commonResult.setData(resultcode);
        commonResult.setMsg("数据创建成功");
        commonResult.setStatus("200");
        return commonResult;
    }

}
