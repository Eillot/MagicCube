package com.simon.magiccube.engine.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.dao.domain.PreOrPosAction;
import com.simon.magiccube.dao.mapper.PreOrPosActionMapper;
import com.simon.magiccube.engine.IPreOrPosActionEngine;
import com.simon.magiccube.engine.common.ResourceManagement;
import com.simon.magiccube.engine.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 4:21 PM
 * @File : PreOrPosActionEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */

@Component
public class PreOrPosActionEngineImp implements IPreOrPosActionEngine {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //资源管理host
    private final static String HOST = "http://10.2.4.100:8079";

    @Autowired
    private PreOrPosActionMapper preOrPosActionMapper;

    @Override
    public PreOrPosAction PreOrPosActionQueryById(Integer id) {
        PreOrPosAction preOrPosAction = preOrPosActionMapper.PreOrPosActionQueryById(id);
        return preOrPosAction;
    }

    @Override
    public List<PreOrPosAction> PreOrPosActionListQueryAll() {
        List<PreOrPosAction> preOrPosActionList = preOrPosActionMapper.PreOrPosActionListQueryAll();
        return preOrPosActionList;
    }

    @Override
    public List<PreOrPosAction> PreOrPosActionQueryAll(PreOrPosAction preOrPosAction) {
        //获取前后置表所有数据
        List<PreOrPosAction> preOrPosActionList = preOrPosActionMapper.PreOrPosActionQueryAll(preOrPosAction);

        List result = new ArrayList();

        //遍历前后置数据，取出productId
        for(PreOrPosAction preOrPosActionData : preOrPosActionList){
            String productId = preOrPosActionData.getProductId();
            JSONObject productList = JSONObject.parseObject(ResourceManagement.queryResBusinessInfoById(productId));
            String productName = String.valueOf(productList.getJSONObject("result").get("businessName"));
            preOrPosActionData.setProductName(productName);
            result.add(preOrPosActionData);

        }
        logger.info("result:"+result);
        return result;
    }

    @Override
    public int PreOrPosActionInsert(PreOrPosAction preOrPosAction) {
        int preOrPosActionInsert = preOrPosActionMapper.PreOrPosActionInsert(preOrPosAction);
        return preOrPosActionInsert;
    }

    @Override
    public int PreOrPosActionUpdate(PreOrPosAction preOrPosAction) {
        int preOrPosActionUpdate = preOrPosActionMapper.PreOrPosActionUpdate(preOrPosAction);
        return preOrPosActionUpdate;
    }

    @Override
    public int PreOrPosActionDeleteToIsDeleteById(PreOrPosAction preOrPosAction) {
        int preOrPosActionDeleteToIsDeleteById = preOrPosActionMapper.PreOrPosActionDeleteToIsDeleteById(preOrPosAction);
        return preOrPosActionDeleteToIsDeleteById;
    }
}
