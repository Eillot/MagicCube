package com.simon.magiccube.engine.common.assertResultCommon;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.engine.common.CaseAssertResult;
import com.simon.magiccube.engine.common.GeneratePostCaseScript;
import com.simon.magiccube.engine.common.assertResultCommon.resultDeal.AssertResultDeal;
import com.simon.magiccube.engine.dto.request.DubboCaseRequest;
import com.simon.magiccube.engine.dto.request.HttpCaseRequest;
import com.simon.magiccube.engine.enums.MatchRulesTypeEnum;
import com.simon.magiccube.engine.util.HttpClientUtil;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p></p>
 * 该方法没有用到
 * @author ：simon
 * @date ：Created in 2020-05-12 11:36
 */
@Component
public class AssertResultProcess {

    private static final Logger logger = LoggerFactory.getLogger(AssertResultProcess.class);

    @Autowired
    private AssertResultDeal assertResultDeal;

    @Autowired
    private CaseAssertResult caseAssertResult;

    /**
     * 期望规则对应方法关系
     */
    private static HashMap<Integer, String> methodsMap = new HashMap<>();

    /**
     * 注册地址与执行器映射关系
     */
    private static Map<String, GenericInvoke> genericInvokeMap = new ConcurrentHashMap<>();

    static {
        Reflections reflections = new Reflections("com.simon.magiccube.engine.common.assertResultCommon.resultDeal", new MethodAnnotationsScanner());
        Set<Method> methodSet = reflections.getMethodsAnnotatedWith(MatchRulesCode.class);
        for (Method method : methodSet) {
            MatchRulesCode annotation = method.getAnnotation(MatchRulesCode.class);
            methodsMap.put(annotation.typeCode(), method.getName());
        }
    }

    public void deal(HttpCaseRequest httpCaseRequest, DubboCaseRequest request, String expectedValue, int matchRules, String assertDes, int casid, int requestType) throws Exception {
        String jsonString = null;
        String assertResult = null;
        try {
            //判断请求方式(get==0|post==1|dubbo ==2),该值最好设置成枚举
            if (requestType == 2) {
                jsonString = dubboCaseInvoke(request);
            } else {
                jsonString = httpCaseSend(requestType, httpCaseRequest.getInterfaceUrl(),
                        httpCaseRequest.getPostJsonRequest());
            }
            Integer typeCode = MatchRulesTypeEnum.getEnumByCode(matchRules).getCode();//断言匹配规则
            String methodName = methodsMap.get(typeCode);
            Class<? extends AssertResultDeal> clazz = assertResultDeal.getClass();
            Method method = clazz.getMethod(methodName, String.class, String.class);
            assertResult = (String) method.invoke(assertResultDeal, jsonString, expectedValue);
            //更新结果
            int updateAssert = caseAssertResult.assertResult(String.valueOf(casid), assertDes, assertResult);
            logger.info("入参 {" + "断言成功后结果更新成功" + "}" + updateAssert);
        } catch (HttpClientUtil.HttpUtilException e) {
            assertResult = "2";
            int updateAssert = caseAssertResult.assertResult(String.valueOf(casid), assertDes, assertResult);
            logger.info("入参 {" + "断言结果失败后结果更新成功" + "}" + updateAssert);
            e.printStackTrace();
        }
    }

    /**
     * dubbo用例测试
     */
    public String dubboCaseInvoke(DubboCaseRequest request) {
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
        Object o = genericInvoke.genericCall(request.getInterfaceName(), request.getGroup(), request.getVersion(), request.getMethodName(), request.getParamTypes(), request.getParamObjs(), request.getTimeout());
        return JSON.toJSONString(o);
    }

    private static String genLocalCacheKey(String ip) {
//        return "zookeeper://" + ip;
        return "zookeeper://10.2.4.68:2181";
    }

    /**
     * http用例测试
     */
    public String httpCaseSend(int requestType, String interfaceUrl, String request) throws HttpClientUtil.HttpUtilException {
        String responnse = null;
        if (requestType == 1) {
            responnse = HttpClientUtil.doPostRequest(interfaceUrl, request);
        } else {
            responnse = HttpClientUtil.sendGetRequest(interfaceUrl);
        }
        JSONObject jsonres = JSON.parseObject(responnse);
        logger.info("接口返回结果:" + jsonres.toString());
        return jsonres.toString();
    }
}
