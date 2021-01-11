package com.simon.magiccube.service;

import com.simon.magiccube.dao.domain.User;
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
 * @Time : 2019-12-13  14:18
 * @File : UserService
 * @Software: IntelliJ IDEA 2018.3
 */


public interface UserService {

    CommonResult<UserInfoVO> queryUserInfoByAccount(UserInfoDTO userInfoDTO) throws ServletException;

    CommonResult userAuthenticated(UserDTO userDTO) throws ServletException;

    CommonResult userRegister(UserDTO userDTO) throws ServletException;

    CommonResult insertUser(UserInfoVO userInfoVO) throws ServletException;

    CommonResult updateUser(UserInfoVO userInfoVO) throws ServletException;

    /**
     * Find a User from Ignite DB with given name.
     *
     * @param name the given name
     * @return The User found in  DB
     */
    User findPersonByUsername(String name);

    /**
     * Insert into a User in DB.
     *
     * @param user
     * @return
     */
    void insertUser(User user);

    /**
     * Update a User in DB.
     *
     * @param user
     * @return
     */
    void updateUser(User user);
}
