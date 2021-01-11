package com.simon.magiccube.web.controller;
/*
*@author  simon
*@data 2019/11/14 20:04
* 探活接口
*/

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(value = "/system/checkServerHealth",method = RequestMethod.GET)
    @ResponseBody
    public String checkServerHealth(){
        return "ok";

    }
}
