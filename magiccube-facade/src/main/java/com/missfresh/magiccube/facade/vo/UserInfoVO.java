package com.simon.magiccube.facade.vo;

import com.simon.magiccube.facade.constants.RegexpConstant;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  16:00
 * @File : UserInfoVO
 * @Software: IntelliJ IDEA 2018.3
 */
public class UserInfoVO {

    @NotEmpty(message = "用户名不能为空")
    @Max(value = 19, message = "用户名{common.lengthVerify}19", groups = {RegexpConstant.SUBMIT.class, RegexpConstant.MODIFY.class})
    private String userLoginAccount;

    private String userLoginPassword;

    /**
     * 用户邮箱
     */
    private String emails;

    /**
     * 用户权限
     */
    private String roles;

    /**
     * 用户创建日期
     */
    private Date userCreateTime;

    public UserInfoVO() {
    }

    public UserInfoVO(@NotEmpty(message = "用户名不能为空") @Max(value = 19, message = "用户名{common.lengthVerify}19", groups = {RegexpConstant.SUBMIT.class, RegexpConstant.MODIFY.class}) String userLoginAccount,
                      String emails, long followers, long stars, String roles) {
        this.userLoginAccount = userLoginAccount;
        this.emails = emails;
        this.roles = roles;
    }

    public String getUserLoginAccount() {
        return userLoginAccount;
    }

    public void setUserLoginAccount(String userLoginAccount) {
        this.userLoginAccount = userLoginAccount;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUserLoginPassword() {
        return userLoginPassword;
    }

    public void setUserLoginPassword(String userLoginPassword) {
        this.userLoginPassword = userLoginPassword;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    @Override
    public String toString() {
        return "UserInfoVO{" +
                "userLoginAccount='" + userLoginAccount + '\'' +
                ", userLoginPassword='" + userLoginPassword + '\'' +
                ", emails='" + emails + '\'' +
                ", roles='" + roles + '\'' +
                ", userCreateTime=" + userCreateTime +
                '}';
    }
}
