package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.AssertRun;
import com.simon.magiccube.dao.domain.CaseRun;
import com.simon.magiccube.dao.mapper.AssertRunMapper;
import com.simon.magiccube.dao.mapper.CaseRunMapper;
import com.simon.magiccube.engine.IAssertRunEngine;
import com.simon.magiccube.engine.ICaseRunEngine;
import com.simon.magiccube.facade.support.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 *
 * @author simon
 * @date 2020-06-05 18:18:59
 **/

@Component
public class AssertRunEngineImp implements IAssertRunEngine {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private AssertRunMapper assertRunMapper;


    @Override
    public AssertRun selectAssertRunById(Integer id) {
        return assertRunMapper.selectAssertRunById(id);
    }

    @Override
    public int insertAssertRun(AssertRun assertRun) {
        return assertRunMapper.insertAssertRun(assertRun);
    }

    @Override
    public CommonResult insertAssertRunRe(AssertRun assertRun) {
        CommonResult commonResult = new CommonResult();
        try{
            int insertAssertRunRe = assertRunMapper.insertAssertRun(assertRun);
            commonResult.setCode("0");
            commonResult.setData(insertAssertRunRe);
        }catch (Exception e){
            commonResult.setCode("-1");
            commonResult.setData(e);
            return commonResult;
        }
        return commonResult;
    }

    @Override
    public int updateAssertRunByIdSelective(AssertRun assertRun) {
        return assertRunMapper.updateAssertRunByIdSelective(assertRun);
    }

    @Override
    public Long selectAssertRunListPageTotal(AssertRun assertRun) {
        return assertRunMapper.selectAssertRunListPageTotal(assertRun);
    }

    @Override
    public List<AssertRun> selectAssertRunListPage(AssertRun assertRun) {
        return assertRunMapper.selectAssertRunListPage(assertRun);
    }

    @Override
    public List<AssertRun> selectAssertRunList(AssertRun assertRun){
        return assertRunMapper.selectAssertRunList(assertRun);
    }
}
