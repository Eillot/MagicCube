package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.User;
import com.simon.magiccube.dao.mapper.UserMapper;
import com.simon.magiccube.engine.IUserEngine;
import com.simon.magiccube.engine.exception.UserException;
import com.simon.magiccube.facade.dto.UserDTO;
import com.simon.magiccube.facade.dto.UserInfoDTO;
import com.simon.magiccube.facade.support.CommonResult;
import com.simon.magiccube.facade.vo.UserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  18:41
 * @File : UserEngineImp
 * @Software: IntelliJ IDEA 2018.3
 */

@Component
public class UserEngineImp implements IUserEngine {

    private static final Logger logger = LoggerFactory.getLogger(UserEngineImp.class);

    @Autowired
    private UserMapper userMapper;

    private UserException userException = new UserException();
    /**
     * add token manager.
     */
    @Autowired
    private TokenEngineImp tokenEngineImp;

    /**
     * add redis manager.
     * @TODO 暂不接入redis
     */
    //@Autowired private RedisEngineImp redisEngineImp;

    /**
     * user register
     * @param userDTO
     * @return
     * @TODO 暂不接入redis
     */

    /*@Override
    public CommonResult userRegister(UserDTO userDTO) throws ServletException{

        // Check if username and password is null
        if (userDTO.getUserLoginAccount() == "" || userDTO.getUserLoginAccount() == null
                || userDTO.getUserLoginPassword() == "" || userDTO.getUserLoginPassword() == null) {

            //日志规范@TODO
            throw new ServletException("Username or Password invalid!");
            //logger.info("Check username and password info{} :"+"Username or Password invalid!");
            *//**
     CommonResult commonResult = new CommonResult();
     commonResult.setStatus(REQUEST_CODE);
     commonResult.setMsg(USERNAME_PASSWORD_ERR);*//*

        }
        // Check if the username is used
        if (userMapper.findUserByUserLoginAccount(userDTO.getUserLoginAccount()) != null){

            //日志规范@TODO
            throw new ServletException("Username is used!");
            //logger.info("Check username and password info{} :"+"Username is used!");
            *//**
     CommonResult commonResult = new CommonResult();
     commonResult.setStatus(SUCCESS_CODE);
     commonResult.setMsg(USERNAME_REFUSE_CODE);*//*
        }
        //create a user token
        Map<String,Object> claims = new HashMap<>();
        claims.put("roles", MEMBER);
        String userToken = tokenEngineImp.doGenrateToken(claims,userDTO.getUserLoginAccount(), 604800l);
        // Create a user in DB @TODO 修改mysql时区
        userMapper.insert(new User(userDTO.getUserLoginAccount(), userDTO.getUserLoginPassword(),DateUtil.getCurrentDate(),"MEMBER",userToken));
        //手动将token 同步到 redis
        redisEngineImp.set(userDTO.getUserLoginAccount(),userToken);
        //封装返回结果
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus("200");
        commonResult.setMsg("register success");
        commonResult.setCode("200");

        Map<String ,Object> map = new HashMap<>();
        map.put("appToken",userToken);
        map.put("appKey","api token");
        commonResult.setData(map);

        return commonResult;
    }
*/

    /**
     * user auth
     *
     * @param userDTO
     * @return
     * @throws ServletException
     * @TODO 暂不接入redis
     */
   /* @Override
    public CommonResult userAuth(UserDTO userDTO) throws ServletException {

        // Check if username and token is null
        if (userDTO.getUserLoginAccount() == "" || userDTO.getUserLoginAccount() == null
                || userDTO.getUserToken() == "" || userDTO.getUserToken() == null) {
            //日志规范@TODO
            throw new ServletException("Username or Token invalid!");
            //logger.info("Check username and password info{} :"+"Username or Password invalid!");
        }

        //check redis token is or not null.(false is null ,true is not null)
        if (false == redisEngineImp.isKeyExisted(userDTO.getUserLoginAccount())){
            throw new ServletException("User's Token is not existed !");
        }

        // redis expriredate more than system current time,means 'token' is not exprire.
        long min = redisEngineImp.getExpire(userDTO.getUserLoginAccount());
        long sub = System.currentTimeMillis() / 1000;
        if (0>=(min-sub)){

            logger.info("check user token is expire info {} :"+"User's Token is expired !");
            String userRefreshToken = (String) redisEngineImp.get(userDTO.getUserLoginAccount());
            String userNewToken = tokenEngineImp.refreshToken(userRefreshToken,68100l);
            userMapper.update(new User(userDTO.getUserLoginAccount(),userNewToken));

            //@TODO redis同步mysql数据
            //手动将mysql保存的user token 同步到redis.
            redisEngineImp.set(userDTO.getUserLoginAccount(),userNewToken);
            logger.info("refreshToken is now info {} :"+userNewToken);
        }

        String userRedisToken = (String) redisEngineImp.get(userDTO.getUserLoginAccount());
        Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("appToken",userRedisToken);
        tokenMap.put("appKey","api token");

        CommonResult commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setStatus("200");
        commonResult.setMsg("User Authentication is Successfully !");
        commonResult.setData(tokenMap);

        return commonResult;
    }*/
    @Override
    public CommonResult userRegister(UserDTO userDTO) throws ServletException {
        return null;
    }

    @Override
    public CommonResult userAuth(UserDTO userDTO) throws ServletException {

        CommonResult commonResult = new CommonResult();
        // Check if username and token is null
        if (userDTO.getUserLoginAccount() == "" || userDTO.getUserLoginAccount() == null) {
            //日志规范@TODO
            throw new ServletException("Username or Token invalid!");
            //logger.info("Check username and password info{} :"+"Username or Password invalid!");
        }

        if (userDTO.getUserLoginAccount().equals("admin") && userDTO.getUserLoginPassword().equals("admin")) {
            commonResult.setCode("200");
            commonResult.setStatus("200");
            commonResult.setMsg("User Login is Successfully !");
        }
        return commonResult;
    }

    /**
     * query user info
     *
     * @param userInfoDTO
     * @return
     * @throws ServletException
     */
    @Override
    public CommonResult<UserInfoVO> queryUserInfoByAccount(UserInfoDTO userInfoDTO) throws ServletException {

        // Check if username and access_token is null
        if (userInfoDTO.getUserLoginAccount() == "" || userInfoDTO.getUserLoginAccount() == null
                || userInfoDTO.getAccessToken() == "" || userInfoDTO.getAccessToken() == null) {
            throw new ServletException("Username or AccessToken invalid!");
        }

        CommonResult commonResult = new CommonResult<>();
        UserInfoVO userInfoVO = new UserInfoVO();

        // PO 转 VO
        User user = userMapper.findUserByUserLoginAccount(userInfoDTO.getUserLoginAccount());
        userInfoVO.setEmails(user.getEmails());
        userInfoVO.setRoles(user.getRoles());

        //装载VO数据
        Map<String, Object> map = new HashMap<>();
        map.put("emails", userInfoVO.getEmails());
        map.put("roles", userInfoVO.getRoles());

        commonResult.setStatus("200");
        commonResult.setMsg("查询成功");
        commonResult.setCode("200");
        commonResult.setData(map);

        //@TODO 请求失败状态码

        return commonResult;
    }

    /**
     * update user info
     *
     * @param userInfoVO
     */
    @Override
    public CommonResult updateUserInfo(UserInfoVO userInfoVO) throws ServletException {
        // user PO
        User user = new User();
        //user VO 转 PO
        user.setUserLoginAccount(userInfoVO.getUserLoginAccount());
        user.setUserLoginPassword(userInfoVO.getUserLoginPassword());
        Date curTime = new Date(System.currentTimeMillis());
        user.setUserCreateTime(curTime);
        user.setRoles(userInfoVO.getRoles());
        user.setEmails(userInfoVO.getEmails());
        userMapper.update(user);
        logger.info("update user info {}" + " successfully .");

        CommonResult commonResult = new CommonResult();
        commonResult.setStatus("200");
        commonResult.setMsg("update user info {} successfully .");
        commonResult.setCode("200");

        //@TODO 请求失败状态码

        return commonResult;
    }

    /**
     * save user info
     *
     * @param userInfoVO
     */
    @Override
    public CommonResult saveUserInfo(UserInfoVO userInfoVO) throws ServletException {
        // user PO
        User user = new User();
        //user VO 转 PO
        // Check if the username is used
        if (userMapper.findUserByUserLoginAccount(userInfoVO.getUserLoginAccount()) != null) {

            //日志规范@TODO
            throw new ServletException("Username is used!");
            //logger.info("Check username and password info{} :"+"Username is used!");
        }
        user.setUserLoginAccount(userInfoVO.getUserLoginAccount());
        user.setUserLoginPassword(userInfoVO.getUserLoginPassword());
        Date curTime = new Date(System.currentTimeMillis());
        user.setUserCreateTime(curTime);
        user.setRoles(userInfoVO.getRoles());
        user.setEmails(userInfoVO.getEmails());
        userMapper.insert(user);
        logger.info("save user info {}" + " successfully .");

        CommonResult commonResult = new CommonResult();
        commonResult.setStatus("200");
        commonResult.setMsg("save user info {} successfully .");
        commonResult.setCode("200");

        //@TODO 请求失败状态码

        return commonResult;

    }
}
