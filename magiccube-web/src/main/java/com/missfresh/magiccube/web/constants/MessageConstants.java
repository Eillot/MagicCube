package com.simon.magiccube.web.constants;

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
     * 通用请求常量
     */
    public static final String CREATE_SUCCESS = "创建成功";
    public static final String CREATE_FAILED = "创建失败";
    public static final String SELECT_SUCCESS = "查询成功";
    public static final String SELECT_FAILED = "查询失败";
    public static final String DELETE_SUCCESS = "删除成功";
    public static final String DELETE_FAILED = "删除失败";
    public static final String UPDATE_SUCCESS = "修改成功";
    public static final String UPDATE_FAILED = "修改失败";
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAILED = "failed";
    public static final String REQUEST_SUCCESS = "请求成功";

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
