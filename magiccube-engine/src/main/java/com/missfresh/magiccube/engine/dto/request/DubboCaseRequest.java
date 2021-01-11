package com.simon.magiccube.engine.dto.request;

import lombok.Data;

/**
 * <p>dubbo用例入参请求类</p>
 *
 * @author ：simon
 * @date ：Created in 2020-05-13 11:04
 */
@Data
public class DubboCaseRequest {

    /**
     * zk地址
     */
    private String address;

    private String registryGroup = "dubbo";

    /**
     * 组
     */
    private String group;

    /**
     * 版本
     */
    private String version;

    /**
     * 超时时间,默认3000
     */
    private Integer timeout = 3000;

    /**
     * 服务全限定名
     */
    private String interfaceName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 入参类型
     */
    private String[] paramTypes;

    /**
     * 入参值
     */
    private Object[] paramObjs;


}
