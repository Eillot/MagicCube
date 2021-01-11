package com.simon.magiccube.engine;

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
 * @Time : 2019-12-13  15:53
 * @File : IUserEngine
 * @Software: IntelliJ IDEA 2018.3
 */

public interface IUserEngine {

    CommonResult userRegister(UserDTO userDTO) throws ServletException;

    CommonResult userAuth(UserDTO userDTO) throws ServletException;

    CommonResult<UserInfoVO> queryUserInfoByAccount(UserInfoDTO userInfoDTO) throws ServletException;

    CommonResult updateUserInfo(UserInfoVO userInfoVO) throws ServletException;

    CommonResult saveUserInfo(UserInfoVO userInfoVO) throws ServletException;

}
