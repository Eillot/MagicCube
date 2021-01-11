package com.simon.magiccube.facade.dto;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  16:00
 * @File : UserInfoDTO
 * @Software: IntelliJ IDEA 2018.3
 */
public class UserInfoDTO extends CommonDTO {

    private static final long serialVersionUID = 682802119259960871L;

    /**
     * user access token
     */
    private String accessToken;

    /**
     * user login account
     */
    private String userLoginAccount;


    public UserInfoDTO() {

    }

    public UserInfoDTO(String accessToken, String userLoginAccount) {
        this.accessToken = accessToken;
        this.userLoginAccount = userLoginAccount;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserLoginAccount() {
        return userLoginAccount;
    }

    public void setUserLoginAccount(String userLoginAccount) {
        this.userLoginAccount = userLoginAccount;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "accessToken='" + accessToken + '\'' +
                ", userLoginAccount='" + userLoginAccount + '\'' +
                '}';
    }
}
