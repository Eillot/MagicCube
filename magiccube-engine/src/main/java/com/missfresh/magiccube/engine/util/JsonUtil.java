package com.simon.magiccube.engine.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  18:45
 * @File : JsonUtil
 * @Software: IntelliJ IDEA 2018.3
 */
public class JsonUtil {


    public static JSONObject jsonresponse = null;

    /**
     *  string 转 map
     * @param str_json
     * @return
     */
    public static Map<String, String> json2map(String str_json) {
        Map<String, String> res = null;
        try {
            Gson gson = new Gson();
            res = gson.fromJson(str_json, new TypeToken<Map<String, Object>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
        }
        return res;
    }

    /**
     * string 转换为 json
     *
     * @param str
     */
    public static JSONObject str2json(String str) {
        jsonresponse = JSONObject.parseObject(str);
        return jsonresponse;
    }

    /**
     * string 转换为 jsonString
     *
     * @param str
     */
    public static String str2jsonStr(String str) {
        String result = JSONObject.toJSONString(str);
        return result;
    }

    /**
     * 根据json key获取值
     *
     * @param obj
     * @param key
     * @return
     */
    public static final String getString(com.alibaba.fastjson.JSONObject obj, String key) {
        if (obj == null) {
            return "";
        }
        Object value = obj.get(key);
        return value == null ? "" : value.toString();
    }

    /**
     * 根据json数据结构获取值
     *
     * @param obj
     * @param keyPath
     * @return
     */
    public static final String getJsonPathValue(com.alibaba.fastjson.JSONObject obj, String keyPath) {
        if (obj == null) {
            return "";
        }
        Object value = JSONPath.eval(obj, keyPath);
        return value == null ? "" : value.toString();
    }

    /**
     * 从普通的Bean转换为字符串
     */
    public static String getJson(Object o) {
        net.sf.json.JSONObject jo = net.sf.json.JSONObject.fromObject(o);
        return jo.toString();
    }

    /**
     * 从列表转换为字符串
     */
    public static String getJson(List list) {
        net.sf.json.JSONArray ja = net.sf.json.JSONArray.fromObject(list);
        return ja.toString();
    }

    /**
     * 从Java对象数组转换为字符串
     */
    public static String getJson(Object[] arry) {
        net.sf.json.JSONArray ja = net.sf.json.JSONArray.fromObject(arry);
        return ja.toString();
    }

    /**
     * 从json格式的字符串转换为Map对象
     */
    public static Map getObject(String s) {
        return net.sf.json.JSONObject.fromObject(s);
    }

    /**
     * Map<String,Object>转Map<String,String>
     */
    public static Map<String,String> object2Str(Map<String,Object> s) {
        String jsonObject = JSONObject.toJSONString(s);
        Map<String,String> stringMap = new HashMap<>();
        stringMap = (Map<String, String>) JSON.parse(jsonObject);
        return stringMap;
    }


    /**
     * 从json格式的字符串转换为List
     */
    public static List getArray(String s) {
        return net.sf.json.JSONArray.fromObject(s);
    }

    /**
     * 从json格式的字符串转换为某个Bean
     */
    public static Object getObject(String s, Class cls) {
        net.sf.json.JSONObject jo = net.sf.json.JSONObject.fromObject(s);
        return net.sf.json.JSONObject.toBean(jo, cls);
    }

    /**
     * 从json格式的字符串转换为某个Bean
     */
    public static Object getObject(String s, Class cls, Map<String, Class> map) {
        net.sf.json.JSONObject jo = net.sf.json.JSONObject.fromObject(s);
        return net.sf.json.JSONObject.toBean(jo, cls, map);
    }

    /**
     * 从json格式的字符串转换为某类对象的数组
     */
    public static Object getArray(String s, Class cls) {
        net.sf.json.JSONArray ja = new net.sf.json.JSONArray();
        if (StringUtils.isNotBlank(s)) {
            ja = net.sf.json.JSONArray.fromObject(s);
        }
        return net.sf.json.JSONArray.toArray(ja, cls);
    }

    /**
     * 把一个json数组串转换成集合，且集合里存放的为实例Bean
     */
    public static List getListFromJsonArrStr(String jsonArrStr, Class clazz) {
        net.sf.json.JSONArray jsonArr = net.sf.json.JSONArray.fromObject(jsonArrStr);
        List list = new ArrayList();
        for (int i = 0; i < jsonArr.size(); i++) {
            list.add(net.sf.json.JSONObject.toBean(jsonArr.getJSONObject(i), clazz));
        }
        return list;
    }

    /**
     * 把一个json数组串转换成集合，且集合里的对象的属性含有另外实例Bean
     *
     * @param jsonArrStr e.g. [{'data':[{'name':'get'}]},{'data':[{'name':'set'}]}]
     * @param clazz      e.g. MyBean.class
     * @param classMap   e.g. classMap.put("data", Person.class)
     * @return List
     */
    public static List getListFromJsonArrStr(String jsonArrStr, Class clazz, Map classMap) {
        net.sf.json.JSONArray jsonArr = net.sf.json.JSONArray.fromObject(jsonArrStr);
        List list = new ArrayList();
        for (int i = 0; i < jsonArr.size(); i++) {
            list.add(net.sf.json.JSONObject.toBean(jsonArr.getJSONObject(i), clazz, classMap));
        }
        return list;
    }


    /**
     * 功能描述：把JSON数据转换成对象
     *
     * @param jsonData JSON数据
     * @param type     对象类型
     * @return
     */
    public static <T> T json2Obj(String jsonData, Type type) {
        return JSON.parseObject(jsonData, type);
    }

    /**
     * 解析json文件并根据key找到value
     *
     * @param json    json字符串
     * @param jsonkey 与json中的key相对应
     * @return
     */
    public static String getJsonValue(String json, String jsonkey) {
        String jsonvalue = "";
        if (json == null || json.trim().length() < 1) {
            return null;
        }

        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(json);
        jsonvalue = jsonObject.getString(jsonkey);

        return jsonvalue;
    }

}
