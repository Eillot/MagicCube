package com.simon.magiccube.web.exception;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  17:10
 * @File : CommonException
 * @Software: IntelliJ IDEA 2018.3
 */
public class CommonException extends Exception {

    /**
     * 标记jdk版本解决兼容性问题
     */
    private static final long serialVersionUID = -737856418890579621L;

    public CommonException(String obj) {
    }

    private void checkIsNull(Object object){

        if (object==null){
            new CommonException("parameter is not null .");
        }
    }

}
