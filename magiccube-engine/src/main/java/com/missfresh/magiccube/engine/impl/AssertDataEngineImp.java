package com.simon.magiccube.engine.impl;

import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.dao.domain.AssertData;
import com.simon.magiccube.dao.mapper.AssertDataMapper;
import com.simon.magiccube.engine.IAssertDataEngine;
import com.simon.magiccube.facade.support.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 4:20 PM
 * @File : AssertDataEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */

@Component
public class AssertDataEngineImp implements IAssertDataEngine {
    private static final Logger logger = LoggerFactory.getLogger(AssertDataEngineImp.class);

    @Autowired
    private AssertDataMapper assertDataMapper;

    @Override
    public AssertData AssertDataQueryBytestDataId(String testdataid) {
        AssertData assertData = assertDataMapper.AssertDataQueryBytestDataId(testdataid);
        return assertData;
    }

    @Override
    public List<AssertData> AssertDataListQueryByTestDataId(String testdataid) {
        return assertDataMapper.AssertDataListQueryByTestDataId(testdataid);
    }

    @Override
    public CommonResult AssertDataQueryBytestDataIdRe(String testdataid) {
        CommonResult commonResult = new CommonResult();
//        AssertData assertData = new AssertData();
        List<AssertData> assertDataList = new ArrayList<>();
        if(testdataid == null || testdataid == ""){
            logger.info("testdataId为空");
            commonResult.setCode("-1");
            commonResult.setMsg("testdataId为空");
            commonResult.setData(testdataid);
            return commonResult;
        }
        try{
            assertDataList = assertDataMapper.AssertDataListQueryByTestDataId(testdataid);
            logger.info("断言查询结果："+assertDataList);
            if(!assertDataList.equals(null)){
                commonResult.setCode("0");
                commonResult.setData(assertDataList);
            }
        }catch (Exception e){
            commonResult.setCode("-1");
            commonResult.setData(e);
            return commonResult;
        }
        return commonResult;
    }

    @Override
    public List<AssertData> AssertDataQueryAll(AssertData assertData) {
        List<AssertData> assertDataList = assertDataMapper.AssertDataQueryAll(assertData);
        return assertDataList;
    }

    @Override
    public List AssertDataQueryAllList(AssertData assertData) {
        return assertDataMapper.AssertDataQueryAllList(assertData);
    }

    @Override
    public List AssertDataQueryById(AssertData assertData) {
        List assertDataQueryById = assertDataMapper.AssertDataQueryById(assertData);
        return assertDataQueryById;
    }

    @Override
    public int AssertDataInsert(AssertData assertData) {
        int assertDataInserter = assertDataMapper.AssertDataInsert(assertData);
        return assertDataInserter;
    }

    @Override
    public int AssertDataUpdateByTestDataId(AssertData assertData) {
        int assertDataUpdateByTestDataId = assertDataMapper.AssertDataUpdateByTestDataId(assertData);
        return assertDataUpdateByTestDataId;
    }

    @Override
    public int AssertDataUpdate(AssertData assertData) {
        int assertDataUpdate = assertDataMapper.AssertDataUpdate(assertData);
        return assertDataUpdate;
    }


}
