package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.TestData;
import com.simon.magiccube.facade.support.CommonResult;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 3:10 PM
 * @File : ITestDataEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface ITestDataEngine {

    /**
     * 通过实体作为筛选条件查询--返回结果
     *
     * @param caseid 实例对象
     * @return 对象列表
     */
    CommonResult TestDataQueryByCaseIdRe(String caseid);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param caseid 实例对象
     * @return 对象列表
     */
    TestData TestDataQueryByCaseId(String caseid);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param testData 实例对象
     * @return 对象列表
     */
    List<TestData> TestDataQueryAll(TestData testData);

    /**
     * 新增数据
     *
     * @param testData 实例对象
     * @return 影响行数
     */
    int TestDataInsert(TestData testData);

    /**
     * 修改数据
     *
     * @param testData 实例对象
     * @return 影响行数
     */
    int TestDataUpdate(TestData testData);

    /**
     * 通过caseId修改数据
     * @param testData
     * @return
     */
    int TestDataUpdateByCaseId(TestData testData);

    List TestDataQueryListById(TestData testData);

}
