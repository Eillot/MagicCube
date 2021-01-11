package com.simon.magiccube.engine.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.engine.util.HttpClientUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/2/25 11:31 上午
 */
public class ResourceManagement {

    //资源管理host
    private final static String HOST = "http://10.2.4.100:8081";

    /**
     * 调用资源管理-查询业务线列表
     * @return
     */
    public static String queryListResBusinessInfo(){
        String url = HOST + "/platform/business/queryListResBusinessInfo";
        JSONObject paramsJson = new JSONObject();
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendPostRequest(url, String.valueOf(paramsJson));
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }

        return resultString;
    }

    /**
     * 调用资源管理-查询业务线详情（根据业务线id查询）
     * @param productId
     * @return
     */
    public static String queryResBusinessInfoById(String productId){
        String url = HOST + "/platform/business/queryResBusinessInfoById";
        Map paramsMap = new HashMap();
        paramsMap.put("id",productId);
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendGetRequest(url, paramsMap);
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }

        return resultString;
    }

    /**
     * 调用资源管理-查询应用（根据业务线id查询）
     * @param productId
     * @return
     */
    public static String queryListResAppInfo(String productId){
        String url = HOST + "/platform/app/queryListResAppInfo";
        JSONObject paramsJson = new JSONObject();
        paramsJson.put("businessId",productId);
        String resultString = null;
        try {
            resultString = HttpClientUtil.sendPostRequest(url, String.valueOf(paramsJson));
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * 调用资源管理-查询应用（根据应用id查询）
     * @param appId
     * @return
     */
    public static String queryListResAppInfoByAppId(String appId){
        String url = HOST + "/platform/app/queryListResAppInfo";
        JSONObject paramsJson = new JSONObject();
        paramsJson.put("id",appId);
        String resultString = null;
        try {
            resultString = HttpClientUtil.sendPostRequest(url, String.valueOf(paramsJson));
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }
        return resultString;
    }


    /**
     * 调用资源管理-查询数据库详情（根据id）
     * @param databaseId
     * @return
     */
    public static String queryDatabaseDetail(String databaseId){
        String url = HOST + "/platform/data/address/detail";
        JSONObject paramsJson = new JSONObject();
        paramsJson.put("id",databaseId);
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendPostRequest(url, String.valueOf(paramsJson));
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * 调用资源管理-查询数据库列表
     * @return
     */
    public static String queryDatabaseList(){
        String url = HOST + "/platform/data/address/list";
        JSONObject paramsJson = new JSONObject();
        paramsJson.put("type",2);
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendPostRequest(url, String.valueOf(paramsJson));
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * 调用资源管理-查询接口详情（通过id）
     * @param interfaceId
     * @return
     */
    public static String queryResInterfaceInfoDetail(String interfaceId){
        String url = HOST + "/platform/resInterfaceInfo/queryResInterfaceInfoDetail";
        Map paramsMap = new HashMap();
        paramsMap.put("id",interfaceId);
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendGetRequest(url, paramsMap);
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }

        return resultString;
    }

    /**
     * 调用资源管理-查询接口（通过appId）
     * @param appId
     * @return
     */
    public static String queryListResInterfaceInfo(String appId){
        String url = HOST + "/platform/resInterfaceInfo/queryListResInterfaceInfo";
        JSONObject paramsJson = new JSONObject();
        paramsJson.put("appId",appId);
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendPostRequest(url, String.valueOf(paramsJson));
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }

        JSONObject interfaceList = JSONObject.parseObject(resultString);
        JSONArray resultJsonArray = interfaceList.getJSONArray("result");
        for(int i =0;i<resultJsonArray.size();i++){
            JSONObject paramsJsonObject = resultJsonArray.getJSONObject(i).getJSONObject("params");
            String reqOther = paramsJsonObject.getString("reqOther");
            JSONArray jsonArray = new JSONArray();
            if(reqOther.equals("") ){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("dataType","dataTypeNull");
                jsonObject.put("dataName","无基础数据类型入参，无需填写");
                jsonArray.add(jsonObject);
                paramsJsonObject.put("reqOther",String.valueOf(jsonArray));
            }else {
                jsonArray = JSON.parseArray(reqOther);
                paramsJsonObject.put("reqOther",String.valueOf(jsonArray));
            }
        }
        resultString = String.valueOf(interfaceList);


        return resultString;
    }

    /**
     * 调用资源管理-查询接口列表（通过接口名称 或 id）
     * 接口名称可模糊搜索
     * @param interName
     * @return
     */
    public static String queryListResInterfaceInfo(String interName,String interId,String type) {
        String url = HOST + "/platform/resInterfaceInfo/queryListResInterfaceInfo";
        JSONObject paramsJson = new JSONObject();
        paramsJson.put("interName",interName);
        paramsJson.put("id",interId);
        paramsJson.put("type",type);
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendPostRequest(url, String.valueOf(paramsJson));
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * 调用资源管理-查询环境（服务）列表
     *
     * @param serverId 传null则为全部
     * @param serverName 传null则为全部，可模糊
     * @return
     */
    public static String queryListResServerenvInfo(String serverId, String serverName){
        String url = HOST + "/platform/serverenv/queryListResServerenvInfo";
        JSONObject paramsJson = new JSONObject();
        paramsJson.put("serverName",serverName);
        paramsJson.put("id",serverId);
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendPostRequest(url, String.valueOf(paramsJson));
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }

        return resultString;
    }


    /**
     * 调用资源管理-查询环境（服务）明细
     * @param serverId
     * @return
     */
    public static String getResServerInfoById(String serverId){
        String url = HOST + "/platform/serverenv/getResServerInfoById";
        HashMap<String ,String > hashMap = new HashMap<>();
        hashMap.put("id",serverId);
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendGetRequest(url,hashMap);
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }

        return resultString;
    }


    /**
     * 调用资源管理-查询服务器信息列表
     * @return
     */
    public static String queryListResServerenvInfo(){
        String url = HOST + "/platform/serverenv/queryListResServerenvInfo";
        JSONObject paramsJson = new JSONObject();
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendPostRequest(url, String.valueOf(paramsJson));
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }

        return resultString;
    }

    public static String queryListResServerenvInfoById(Integer id){
        String url = HOST + "/platform/serverenv/queryListResServerenvInfo";
        JSONObject paramsJson = new JSONObject();
        paramsJson.put("id",id);
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendPostRequest(url, String.valueOf(paramsJson));
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
        }

        return resultString;
    }
}
