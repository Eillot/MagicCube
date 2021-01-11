package com.simon.magiccube.facade.dto;

import java.io.Serializable;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  16:10
 * @File : CommonDTO
 * @Software: IntelliJ IDEA 2018.3
 */
public class CommonDTO implements Serializable {

    private static final long serialVersionUID = -1306745857511257905L;

    /**
     * 用户token
     */
    private String userToken;

    /**
     * 访问api口令
     */
    private String usserAppkey;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUsserAppkey() {
        return usserAppkey;
    }

    public void setUsserAppkey(String usserAppkey) {
        this.usserAppkey = usserAppkey;
    }

    @Override
    public String toString() {
        return "CommonDTO{" +
                "userToken='" + userToken + '\'' +
                ", usserAppkey='" + usserAppkey + '\'' +
                '}';
    }
}
