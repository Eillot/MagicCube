package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.User;
import org.springframework.stereotype.Component;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  15:11
 * @File : UserMapper
 * @Software: IntelliJ IntelliJ IDEA 2018.3
 */

@Component
public interface UserMapper {

    /**
     * 根据用户登录账号查询用户信息
     *
     * @param userLoginAccount
     * @return
     */
    User findUserByUserLoginAccount(String userLoginAccount);

    /**
     * 保存用户信息
     *
     * @param user
     */
    void insert(User user);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void update(User user);
}
