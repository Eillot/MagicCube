package com.simon.magiccube.engine.common.assertResultCommon;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>泛化调用封装</p>
 *
 * @author ：simon
 * @date ：Created in 2020-05-12 20:54
 */
public class GenericInvoke {

    private static Logger logger = LoggerFactory.getLogger(GenericInvoke.class);

    private ApplicationConfig applicationConfig;
    private volatile AtomicBoolean init = new AtomicBoolean(false);
    private ConcurrentHashMap<String, ReferenceConfig<GenericService>> cachedConfig = new ConcurrentHashMap<>();

    private Registry registry;

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public void init() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(registry.getUrl().getProtocol() + "://" + registry.getUrl().getAddress());
        registryConfig.setGroup(registry.getUrl().getParameter(Constants.GROUP_KEY));
        applicationConfig = new ApplicationConfig();
        applicationConfig.setName("magiccube");
        applicationConfig.setRegistry(registryConfig);
        // 初始化成功，修改标识位
        init.compareAndSet(false, true);
    }

    public Object genericCall(String interfaceName,
                              String group,
                              String version,
                              String methodName,
                              String[] paramTypes,
                              Object[] paramObjs,
                              int timeout) {
        if (init.compareAndSet(false, true)) {
            init();
        }
        ReferenceConfig<GenericService> reference = addNewReference(interfaceName, group, version, timeout);
        GenericService svc = reference.get();
        logger.info("dubbo generic invoke, service is {}, method is {} , paramTypes is {} , paramObjs is {} , svc is {}.",
                interfaceName, methodName, paramTypes, paramObjs, svc);
        if (null == svc) {
            reference = initReference(interfaceName, group, version, timeout);
            svc = reference.get();
        }
        Object result = svc.$invoke(methodName, paramTypes, paramObjs);
        return result;
    }

    private ReferenceConfig<GenericService> addNewReference(String interfaceName, String group, String version, int timeout) {
        ReferenceConfig<GenericService> reference;
        String cachedKey = interfaceName + group + version;
        reference = cachedConfig.get(cachedKey);
        if (reference == null) {
            ReferenceConfig<GenericService> newReference = initReference(interfaceName, group, version, timeout);
            ReferenceConfig<GenericService> oldReference = cachedConfig.putIfAbsent(cachedKey, newReference);
            if (oldReference != null) {
                reference = oldReference;
            } else {
                reference = newReference;
            }
        }
        return reference;
    }

    private ReferenceConfig<GenericService> initReference(String interfaceName, String group, String version, int timeout) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setGeneric(true);
        reference.setApplication(applicationConfig);
        reference.setGroup(group);
        reference.setVersion(version);
        reference.setInterface(interfaceName);
        reference.setTimeout(timeout);
        return reference;
    }
}
