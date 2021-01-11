package com.simon.magiccube.engine.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * <p></p>
 *
 * @author ：simon
 * @date ：Created in 2020-05-12 15:16
 */
@Configuration
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.applicationContext = applicationContext;
    }

    public static Object getBeanByName(String beanName) {
        if (null == applicationContext) {
            return null;
        }
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    public static <T> Map<String, T> beansOfTypeIncludingAncestors(Class<T> type) {
        return BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, type, false, false);
    }
}
