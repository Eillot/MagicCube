package com.simon.magiccube.facade;

import com.simon.magiccube.facade.dto.UserDTO;
import com.simon.magiccube.facade.dto.UserInfoDTO;
import com.simon.magiccube.facade.support.CommonResult;
import com.simon.magiccube.facade.vo.UserInfoVO;

import javax.servlet.ServletException;


/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  11:39
 * @File : UserFacade
 * @Software: IntelliJ IDEA 2018.3
 */


public interface UserFacade {

    CommonResult<UserInfoVO> queryUserInfoByAccount(UserInfoDTO userInfoDTO) throws ServletException;

    CommonResult userAuthenticated(UserDTO userDTO) throws ServletException;

    CommonResult userRegister(UserDTO userDTO) throws ServletException;

    /**
     * Insert into a User in DB.
     *
     * @param userInfoVO
     * @return
     */
    //void insertUser(UserInfoVO userInfoVO);
    CommonResult insertUser(UserInfoVO userInfoVO) throws ServletException;

    /**
     * Update a User in DB.
     *
     * @param userInfoVO
     * @return
     */
    // void updateUser(UserInfoVO userInfoVO);
    CommonResult updateUser(UserInfoVO userInfoVO) throws ServletException;

}