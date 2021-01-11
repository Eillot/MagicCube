package com.simon.magiccube.engine.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.engine.IResourceManagement;
import com.simon.magiccube.engine.common.ResourceManagement;
import org.springframework.stereotype.Component;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2020/3/22 9:52 PM
 * @File : ResourceManagementEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */

@Component
public class ResourceManagementEngineImp implements IResourceManagement {


    @Override
    public JSONObject getInterfaceRes(String interfaceId) {

        JSONObject jsonObject = JSON.parseObject(ResourceManagement.queryResInterfaceInfoDetail(interfaceId));
        return jsonObject;
    }

    @Override
    public JSONObject getServerIp(String serverId) {
        JSONObject jsonObject = JSON.parseObject(ResourceManagement.getResServerInfoById(serverId));
        return jsonObject;
    }
}
