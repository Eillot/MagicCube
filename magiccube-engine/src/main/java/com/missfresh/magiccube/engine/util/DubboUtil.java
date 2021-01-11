package com.simon.magiccube.engine.util;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;

import java.util.Map;
import java.util.Objects;

/**
 * Created by mac on 2020/4/11.
 */
public class DubboUtil {

    public RegistryConfig registry = new RegistryConfig();
    public ReferenceConfig<GenericService> reference = new ReferenceConfig();
    public GenericService genericService;


    public Object excuteFormOrJson(String interfaceName, String method, String zkAddress, String group, String zkVersion, String[] paramType, Object[] param, String applicationName) {

        Object result = excute(interfaceName, method, zkAddress, zkVersion, group, paramType, param, applicationName);

        return result;

    }

    public Object excuteDto(String interfaceName, String method, String zkAddress, String group, String zkVersion, String paramDtoClass, String param, String applicationName) {

        Map maps = (Map) JSON.parse(param);
        maps.put("class", paramDtoClass);

        Object result = excute(interfaceName, method, zkAddress, zkVersion, group, new String[]{paramDtoClass}, new Object[]{maps}, applicationName);
        return result;
    }

    public Object excute(String interfaceName, String method, String zkAddress, String zkVersion,String group, String[] paramType, Object[] param, String applicationName) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName(applicationName);
        this.registry.setAddress("zookeeper://" + zkAddress);
        this.reference.setInterface(interfaceName);
        this.reference.setGeneric(true);
        this.reference.setApplication(application);
        if(null!=zkVersion&&!zkVersion.isEmpty()){
            this.reference.setVersion(zkVersion);
        }
        if(null!=group&&!group.isEmpty()){
            this.reference.setGroup(group);
        }
        this.reference.setTimeout(50000);
        this.reference.setRegistry(this.registry);
        this.genericService = (GenericService) this.reference.get();

        Object result = null;
        if(!Objects.isNull(genericService)){
            result = genericService.$invoke(method, paramType,param);
        }

        return result;
    }

}
