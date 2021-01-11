package com.simon.magiccube.engine.constants;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  16:38
 * @File : MessageConstants
 * @Software: IntelliJ IDEA 2018.3
 */

public class MessageConstants {

    /**
     * 接口返回提示信息
     */

    public static final String PARAMS_ERR = "参数异常";
    public static final String SERVER_ERR = "服务器端错误";
    public static final String DATABASE_ERR = "数据库错误";
    public static final String UNKNOWN_ERR = "未知错误";
    public static final String NODATA = "无数据";

    public static final String DATAEXISTS = "记录已存在";
    public static final String DELETEERR = "删除失败";
    public static final String UPDATEERR = "修改失败";

    public static final String NOTLOGIN = "未登录";
    public static final String LOGIN = "已登录";
    public static final String REGISTER = "注册成功";


    /**
     * 用户访问后端服务返回提示信息
     */

    //用户输入的用户名与密码错误
    public static final String USERNAME_PASSWORD_ERR = "用户名或密码错误";

    //发起非法请求抛出用户名或token无效
    public static final String USERNAME_TOKEN_ERR = "用户名或token错误";

    //用户名已经存在不允许用户注册相同的用户名
    public static final String USERNAME_REFUSE_CODE = "用户名已被注册";

    //用户访问后端服务时鉴权成功
    public static final String AUTH_USERNAME_SUCCESS_CODE = "用户认证成功";

    //用户访问后端服务时鉴权失败
    public static final String AUTH_USERNAME_FAILED_CODE = "用户认证失败";

    /**
     * 与token相关
     */
    public static final String USERTOKEN_EXISTED_CODE = "用户token不存在";

}
