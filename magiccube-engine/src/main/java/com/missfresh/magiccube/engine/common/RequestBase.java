package com.simon.magiccube.engine.common;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.alibaba.fastjson.JSON;
import com.simon.magiccube.engine.common.assertResultCommon.GenericInvoke;
import com.simon.magiccube.engine.dto.request.DubboCaseRequest;
import com.simon.magiccube.engine.util.DubboUtil;
import com.simon.magiccube.engine.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/6/7 10:59 上午
 */
public class RequestBase extends AbstractTestNGSpringContextTests {
    private static final Logger logger = LoggerFactory.getLogger(RequestBase.class);

    /**
     * 注册地址与执行器映射关系
     */
    private static Map<String, GenericInvoke> genericInvokeMap = new ConcurrentHashMap<>();

    //post请求
    public static String doPost(String path,String paramsJson){
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendPostRequest(path, paramsJson);
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
            logger.info("接口调用失败，请检查接口url：",e);
            return e.getMessage();
        }

        return resultString;
    }


    //get请求
    public static String doGet(String path)  {
        String resultString = "";
        try {
            resultString = HttpClientUtil.sendGetRequest(path);
        } catch (HttpClientUtil.HttpUtilException e) {
            e.printStackTrace();
            logger.info("get请求失败："+e);
        }

        return resultString;
    }


    private static String genLocalCacheKey(String ip) {
        return "zookeeper://" + ip;
    }

    //dubbo请求
    public static String dubboCaseInvoke(DubboCaseRequest request) {
        String registerUrl = genLocalCacheKey(request.getAddress());
        GenericInvoke genericInvoke = genericInvokeMap.get(registerUrl);
        if (Objects.isNull(genericInvoke)) {
            try {
                URL url = URL.valueOf(registerUrl);
                if (StringUtils.isNotEmpty(request.getRegistryGroup())) {
                    url = url.addParameter(Constants.GROUP_KEY, request.getGroup());
                }
                RegistryFactory registryFactory = ExtensionLoader
                        .getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
                Registry registry = registryFactory.getRegistry(url);
                genericInvoke = new GenericInvoke();
                genericInvoke.setRegistry(registry);
                genericInvoke.init();
                genericInvokeMap.put(registerUrl, genericInvoke);
            } catch (Exception e) {
                logger.error("init GenericInvoke error. url:" + registerUrl, e);
                throw e;
            }
        }

        DubboUtil dubboUtil = new DubboUtil();
        Object result = dubboUtil.excute(request.getInterfaceName(),request.getMethodName(),request.getAddress(),request.getVersion(),request.getGroup(), request.getParamTypes() ,request.getParamObjs(),"1");
//        Object result = genericInvoke.genericCall(request.getInterfaceName(), request.getGroup(), request.getVersion(), request.getMethodName(), request.getParamTypes(), request.getParamObjs(), 50000);
        logger.info("dubbo接口结果："+result);
        return JSON.toJSONString(result);
    }

}
