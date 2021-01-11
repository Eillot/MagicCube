package com.simon.magiccube.engine.reporter;

import org.testng.ITestResult;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/6/9 12:11 下午
 */
public class TestResultSort implements Comparable<ITestResult> {
    private Long order;
    @Override
    public int compareTo(ITestResult arg0) {
        // TODO Auto-generated method stub
        return this.order.compareTo( arg0.getStartMillis());//按test开始时间排序
    }

}
