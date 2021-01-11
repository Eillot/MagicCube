package com.simon.magiccube.service.Imp;

import com.simon.magiccube.engine.IUserEngine;
import com.simon.magiccube.facade.UserFacade;
import com.simon.magiccube.facade.dto.UserDTO;
import com.simon.magiccube.facade.dto.UserInfoDTO;
import com.simon.magiccube.facade.support.CommonResult;
import com.simon.magiccube.facade.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  14:23
 * @File : UserServiceImpl
 * @Software: IntelliJ IDEA 2018.3
 */

@Service
public class UserServiceImpl implements UserFacade {

    @Autowired
    private IUserEngine iUserEngine;

    @Override
    public CommonResult<UserInfoVO> queryUserInfoByAccount(UserInfoDTO userInfoDTO) throws ServletException {

        CommonResult<UserInfoVO> commonResult = iUserEngine.queryUserInfoByAccount(userInfoDTO);
        return commonResult;
    }

    @Override
    public CommonResult userAuthenticated(UserDTO userDTO) throws ServletException {
        CommonResult commonResult = iUserEngine.userAuth(userDTO);
        return commonResult;
    }

    @Override
    public CommonResult userRegister(UserDTO userDTO) throws ServletException {
        CommonResult commonResult = iUserEngine.userRegister(userDTO);
        return commonResult;
    }

    @Override
    public CommonResult insertUser(UserInfoVO userInfoVO) throws ServletException {
        CommonResult commonResult = new CommonResult();
        commonResult = iUserEngine.saveUserInfo(userInfoVO);
        return commonResult;

    }

    @Override
    public CommonResult updateUser(UserInfoVO userInfoVO) throws ServletException {
        CommonResult commonResult = new CommonResult();
        commonResult = iUserEngine.updateUserInfo(userInfoVO);
        return commonResult;
    }

}
