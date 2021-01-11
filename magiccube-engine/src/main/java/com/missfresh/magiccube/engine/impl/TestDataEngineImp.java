package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.TestData;
import com.simon.magiccube.dao.mapper.TestDataMapper;
import com.simon.magiccube.engine.ITestDataEngine;
import com.simon.magiccube.facade.support.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 4:23 PM
 * @File : TestDataEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */
@Component
public class TestDataEngineImp implements ITestDataEngine {
    private static final Logger logger = LoggerFactory.getLogger(TestDataEngineImp.class);

    @Autowired
    private TestDataMapper testDataMapper;

    @Override
    public CommonResult TestDataQueryByCaseIdRe(String caseid) {
        CommonResult commonResult = new CommonResult();
        TestData testData = new TestData();
        if (caseid == null || caseid == "") {
            logger.info("caseId为空");
            commonResult.setCode("-1");
            commonResult.setMsg("caseId为空");
            commonResult.setData(caseid);
            return commonResult;
        }
        try {
            testData = testDataMapper.TestDataQueryByCaseId(caseid);
            if (!testData.equals(null)) {
                commonResult.setCode("0");
                commonResult.setData(testData);
            }
        } catch (Exception e) {
            commonResult.setCode("-1");
            commonResult.setData(e);
            return commonResult;
        }
        return commonResult;
    }

    @Override
    public TestData TestDataQueryByCaseId(String caseid) {
        return testDataMapper.TestDataQueryByCaseId(caseid);
    }

    @Override
    public List<TestData> TestDataQueryAll(TestData testData) {
        List<TestData> testDataList = testDataMapper.TestDataQueryAll(testData);
        return testDataList;
    }

    @Override
    public int TestDataInsert(TestData testData) {
        int testDataInserter = testDataMapper.TestDataInsert(testData);
        return testDataInserter;
    }

    @Override
    public int TestDataUpdate(TestData testData) {
        int testDataUpdate = testDataMapper.TestDataUpdate(testData);
        return testDataUpdate;
    }

    @Override
    public int TestDataUpdateByCaseId(TestData testData) {
        int testDataUpdateByCaseId = testDataMapper.TestDataUpdateByCaseId(testData);
        return testDataUpdateByCaseId;
    }

    @Override
    public List TestDataQueryListById(TestData testData) {
        return testDataMapper.TestDataQueryListById(testData);
    }
}
