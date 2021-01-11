package com.simon.magiccube.engine.constants;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  16:36
 * @File : HttpResultConstants
 * @Software: IntelliJ IDEA 2018.3
 */
public class HttpResultConstants {

    /**
     * 自定义http请求结果状态码
     */

    //接口请求成功并返回正确的结果
    public static final String SUCCESS_CODE = "200";

    //请求出现错误
    public static final String REQUEST_CODE = "400";

    //没有提供认证信息,比如:请求的时候没有带上 Token 等
    public static final String AUTH_FAILED_CODE = "401";

    //请求的资源拒绝访问。比如:你使用普通用户的 Token 去请求管理员才能访问的资源
    public static final String REQUEST_REFUSE_CODE = "403";

    //请求的内容不存在
    public static final String RESPONSE_NULL_CODE = "404";

    //服务器内部错误
    public static final String SERVER_ERR_CODE = "500";

    //网络请求超时
    public static final String HTTP_TIMEOUT_ERR = "501";

    //网关错误
    public static final String HTTP_GATEWAY_ERR = "502";
}
