package com.simon.magiccube.web.response;

import com.simon.magiccube.web.constants.HttpResultConstants;
import com.simon.magiccube.web.constants.MessageConstants;

import java.util.HashMap;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2020/2/19 3:03 PM
 * @File : CommonResponse
 * @Software: IntelliJ IDEA 2018.1.8
 */
public class CommonResponse<T> {

    /**
     * 通用返回结果初始化
     */
    public CommonResponse commonResponse = new CommonResponse();

    /**
     * 前端请求响应码，0：成功，-1：失败
     */
    private String status;
    /**
     * 接口响应状态码（可选）
     */
    private String code;
    /**
     * 接口响应msg
     */
    private String msg;
    /**
     * 接口响应内容
     */
    private T data;

    public CommonResponse() {

    }

    public CommonResponse(String status, String code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public CommonResponse(String status, String code, String msg, T data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "commonResponse{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
    
    /**
     *  list作为返回结果的http请求（常用于创建)
     * @param objects
     * @return
     */
    public CommonResponse requestByList(String restates, String reams, Object[] objects){

        commonResponse.setStatus(restates);
        commonResponse.setMsg(reams);
        commonResponse.setData(objects);
        return commonResponse;

    }

    /**
     *  map作为返回结果的http请求（常用于创建)
     *
     * @param hashMap
     * @return
     */
    public CommonResponse requestByMap(String states, String reams, HashMap hashMap){

        commonResponse.setStatus(states);
        commonResponse.setMsg(reams);
        commonResponse.setData(hashMap);
        return commonResponse;

    }

    /**
     *  map作为返回结果的http请求（常用于创建)
     *
     * @param data
     * @return
     */
    public CommonResponse requestByMap(String states, String reams, Object data){

        commonResponse.setStatus(states);
        commonResponse.setMsg(reams);
        commonResponse.setData(data);
        return commonResponse;

    }


    /**
     *  json作为返回结果的http请求（常用于查询拥有唯一标识的指定数据)
     * @param objects
     * @return
     */
    public CommonResponse requestByObj(String restates, String reams, Object objects){

        commonResponse.setStatus(restates);
        commonResponse.setMsg(reams);
        commonResponse.setData(objects);
        return commonResponse;

    }

    /**
     *  增加（创建）成功后，json格式返回结果
     * @param map
     * @return
     */
    public CommonResponse createcommonResponseSuccess(HashMap map){
        commonResponse.requestByMap(HttpResultConstants.SUCCESS_CODE,MessageConstants.CREATE_SUCCESS,map);
        return commonResponse;
    }

    /**
     *  增加（创建）成功后，json格式返回结果
     * @param map
     * @return
     */
    public CommonResponse createcommonResponseSuccess(Object map){
        commonResponse.requestByMap(HttpResultConstants.SUCCESS_CODE,MessageConstants.CREATE_SUCCESS,map);
        return commonResponse;
    }

    /**
     * 增加（创建）失败后，json格式返回结果
     * @param map
     * @return
     */
    public CommonResponse createcommonResponseFailed(HashMap map){
        commonResponse.requestByMap(HttpResultConstants.SERVER_ERR_CODE,MessageConstants.CREATE_FAILED,map);
        return commonResponse;
    }

    /**
     * 查询所有成功后，json格式返回结果
     *
     * @param list
     * @return
     */
    public CommonResponse selectAllcommonResponseSuccess(Object[] list){
        commonResponse.requestByList(HttpResultConstants.SUCCESS_CODE,MessageConstants.SELECT_SUCCESS,list);
        return commonResponse;
    }

    /**
     * 查询所有失败后，json格式返回结果
     *  @TODO 请求失败的情况比较多，后续使用switch语句做判断
     *
     * @param list
     * @return
     */
    public CommonResponse selectAllcommonResponseFailed(Object[] list){
        commonResponse.requestByList(HttpResultConstants.SERVER_ERR_CODE,MessageConstants.SELECT_FAILED,list);
        return commonResponse;
    }


    /**
     * 查询指定成功后，json格式返回结果
     *
     * @param object
     * @return
     */
    public CommonResponse selectcommonResponseSuccess(Object object){
        commonResponse.requestByObj(HttpResultConstants.SUCCESS_CODE,MessageConstants.SELECT_SUCCESS,object);
        return commonResponse;
    }

    /**
     * 查询指定失败后，json格式返回结果
     * @TODO 请求失败的情况比较多，后续使用switch语句做判断
     *
     * @param object
     * @return
     */
    public CommonResponse selectcommonResponseFailed(Object object){
        commonResponse.requestByObj(HttpResultConstants.SERVER_ERR_CODE,MessageConstants.SELECT_FAILED,object);
        return commonResponse;
    }


    /**
     * 修改指定成功后，json格式返回结果
     *
     * @param object
     * @return
     */
    public CommonResponse modifycommonResponseSuccess(Object object){
        commonResponse.requestByObj(HttpResultConstants.SUCCESS_CODE,MessageConstants.SELECT_SUCCESS,object);
        return commonResponse;
    }

    /**
     * 修改指定失败后，json格式返回结果
     * @TODO 请求失败的情况比较多，后续使用switch语句做判断
     *
     * @param object
     * @return
     */
    public CommonResponse modifycommonResponseFailed(Object object){
        commonResponse.requestByObj(HttpResultConstants.SERVER_ERR_CODE,MessageConstants.SELECT_FAILED,object);
        return commonResponse;
    }

    /**
     * 删除指定成功后，json格式返回结果
     *
     * @param object
     * @return
     */
    public CommonResponse deletecommonResponseSuccess(Object object){
        commonResponse.requestByObj(HttpResultConstants.SUCCESS_CODE,MessageConstants.SELECT_SUCCESS,object);
        return commonResponse;
    }

    /**
     * 删除指定失败后，json格式返回结果
     * @TODO 请求失败的情况比较多，后续使用switch语句做判断
     *
     * @param object
     * @return
     */
    public CommonResponse deletecommonResponseFailed(Object object){
        commonResponse.requestByObj(HttpResultConstants.SERVER_ERR_CODE,MessageConstants.SELECT_FAILED,object);
        return commonResponse;
    }

}
