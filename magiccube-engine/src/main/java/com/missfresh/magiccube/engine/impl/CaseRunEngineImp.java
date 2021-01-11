package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.CaseRun;
import com.simon.magiccube.dao.mapper.CaseRunMapper;
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
public class CaseRunEngineImp implements ICaseRunEngine {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private CaseRunMapper caseRunMapper;


    @Override
    public CaseRun selectCaseRunById(Integer id) {
        return caseRunMapper.selectCaseRunById(id);
    }

    @Override
    public CaseRun selectCaseRunByNew(CaseRun caseRun) {
        return caseRunMapper.selectCaseRunByNew(caseRun);
    }


    @Override
    public List selectCaseRunListByNew(CaseRun caseRun) {
        return caseRunMapper.selectCaseRunListByNew(caseRun);
    }

    @Override
    public CaseRun selectCaseRunByCaseId(String caseId) {
        return caseRunMapper.selectCaseRunByCaseId(caseId);
    }

    @Override
    public CaseRun selectCaseRunByCaseIdNew(String caseId) {
        return caseRunMapper.selectCaseRunByCaseIdNew(caseId);
    }

    @Override
    public int insertCaseRun(CaseRun caseRun) {
        return caseRunMapper.insertCaseRun(caseRun);
    }

    @Override
    public CommonResult insertCaseRunRe(CaseRun caseRun) {
        CommonResult commonResult = new CommonResult();
        try{
            int insertCaseRunRe = caseRunMapper.insertCaseRun(caseRun);
            commonResult.setCode("0");
            commonResult.setData(insertCaseRunRe);
        }catch (Exception e){
            commonResult.setCode("-1");
            commonResult.setData(e);
            return commonResult;
        }
        return commonResult;    }

    @Override
    public int updateCaseRunByIdSelective(CaseRun caseRun) {
        return caseRunMapper.updateCaseRunByIdSelective(caseRun);
    }

    @Override
    public Integer selectCaseRunListPageTotal(CaseRun caseRun) {
        return caseRunMapper.selectCaseRunListPageTotal(caseRun);
    }

    @Override
    public List<CaseRun> selectCaseRunListPage(CaseRun caseRun) {
        return caseRunMapper.selectCaseRunListPage(caseRun);
    }

    @Override
    public List<CaseRun> selectCaseRunList(CaseRun caseRun) {
        return caseRunMapper.selectCaseRunList(caseRun);
    }
}
