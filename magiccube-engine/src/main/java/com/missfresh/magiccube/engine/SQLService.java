package com.simon.magiccube.engine;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/4/9 2:09 下午
 */
public interface SQLService {

    /**
     * sql执行
     * @param databaseId 数据库id
     * @param sqlParam 前后置id
     * @return
     */
    String sqlFunction(String databaseId,String sqlParam);

    Object sqlByParam(String addressId,String dbSentance,String access);
}
