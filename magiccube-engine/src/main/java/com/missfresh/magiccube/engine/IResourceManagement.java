package com.simon.magiccube.engine;

import com.alibaba.fastjson.JSONObject;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2020/3/22 9:47 PM
 * @File : IResourceManagement
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface IResourceManagement {

    public JSONObject getInterfaceRes(String interfaceId);

    public JSONObject getServerIp(String serverId);

}
