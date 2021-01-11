package com.simon.magiccube.web.exception;

import com.simon.magiccube.facade.support.CommonResult;

import javax.servlet.ServletException;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  18:40
 * @File : UserException
 * @Software: IntelliJ IDEA 2018.3
 */
public class UserException extends ServletException {


    /**
     * 用于封装用户自定义的异常返回结果
     */
    private static final long serialVersionUID = -8885705298516251618L;

    /**
     * 初始化通用的处理返回结果
     */
    public static CommonResult commonResult = new CommonResult();


    public UserException() {
    }

    /**
     * 复写方法
     *
     * @param message
     */
    public UserException(String message) {
    }

    public UserException(CommonResult commonResult) {
    }

    public UserException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public UserException(Throwable rootCause) {
        super(rootCause);
    }


    public CommonResult exceptionResult(String status, String httpCode, String msg) {

        //前端接口响应码
        commonResult.setStatus(status);
        //接口请求响应码
        commonResult.setCode(httpCode);
        //接口返回响应消息
        commonResult.setMsg(msg);
        return commonResult;
    }

    public static CommonResult universalExResult(String status, String httpCode, String msg) {

        ServletException se = new UserException();
        UserException ue = (UserException) se;
        commonResult = ue.exceptionResult(status, httpCode, msg);

        return commonResult;

    }

    /**
     *
     * public static void main(String[] args) {
     *
     *
     *        ServletException u = new UserException();
     *
     *        System.out.println(((UserException) u).exceptionResult("11","22","55"));
     *         //UserException userException =new UserException();
     *
     *        // System.out.println(userException.show("777"));
     *         commonResult = universalExResult("55","66","99");
     *         System.out.println(commonResult);
     *     }
     *
     */

}
