package com.simon.magiccube.engine.util;

import org.springframework.context.ApplicationContext;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  15:51
 * @File : ApplicationContextUtil
 * @Software: IntelliJ IDEA 2018.3
 */
public class ApplicationContextUtil {


    private ApplicationContextUtil() {
    }

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
