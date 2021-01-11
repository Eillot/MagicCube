package com.simon.magiccube.facade.dto;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  15:58
 * @File : UserDTO
 * @Software: IntelliJ IDEA 2018.3
 */
public class UserDTO extends CommonDTO {


    /**
     * 用户登录账号
     */
    private String userLoginAccount;
    /**
     * 用户登录密码
     */
    private String userLoginPassword;

    public String getUserLoginAccount() {
        return userLoginAccount;
    }

    public void setUserLoginAccount(String userLoginAccount) {
        this.userLoginAccount = userLoginAccount;
    }

    public String getUserLoginPassword() {
        return userLoginPassword;
    }

    public void setUserLoginPassword(String userLoginPassword) {
        this.userLoginPassword = userLoginPassword;
    }

}
