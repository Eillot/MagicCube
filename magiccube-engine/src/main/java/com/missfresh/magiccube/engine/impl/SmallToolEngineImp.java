package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.SmallTool;
import com.simon.magiccube.dao.mapper.SmallToolMapper;
import com.simon.magiccube.engine.ISmallToolEngine;
import com.simon.magiccube.engine.util.DubboUtil;
import com.simon.magiccube.engine.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 4:23 PM
 * @File : SmallToolEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */
@Component
public class SmallToolEngineImp implements ISmallToolEngine {
    @Autowired
    private SmallToolMapper smallToolMapper;

    @Override
    public SmallTool SmallToolQueryById(Integer id) {

        return smallToolMapper.SmallToolQueryById(id);
    }

    @Override
    public List<SmallTool> SmallToolQueryAll(SmallTool smallTool) {

        return smallToolMapper.SmallToolQueryAll(smallTool);
    }

    @Override
    public int SmallToolInsert(SmallTool smallTool) {

        return smallToolMapper.SmallToolInsert(smallTool);
    }

    @Override
    public int SmallToolUpdate(SmallTool smallTool) {

        return smallToolMapper.SmallToolUpdate(smallTool);
    }

    @Override
    public int SmallToolDeleteById(Integer id) {

        return smallToolMapper.SmallToolDeleteById(id);
    }

    @Override
    public Object excuteHttpSmallTool(String requestType,String runEnv,String interUrl, String parameter) {
        Object response = null;
        String url = runEnv + interUrl;
        try {
            if(requestType.equalsIgnoreCase("2")) {//post
                response = HttpClientUtil.sendPostRequest(url,parameter);
            }else if(requestType.equalsIgnoreCase("1")) {//get
                response = HttpClientUtil.sendGetRequest(url + "?" + parameter);
            }else{
                return null;
            }
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }
        return response;
    }

    //todo
    @Override
    public Object excuteDubboSmallTool(String interUrl,String method,String zkAddress,String zkVertion,String group, String[] paramTypes,Object[] params,String applicationName) {
        DubboUtil dubboUtil = new DubboUtil();
        Object result = dubboUtil.excute(interUrl,method,zkAddress,zkVertion,group,paramTypes,params,applicationName);
        return result;
    }
}
