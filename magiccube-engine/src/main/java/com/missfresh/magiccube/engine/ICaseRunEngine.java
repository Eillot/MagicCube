package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.AssertRun;
import com.simon.magiccube.dao.domain.CaseRun;
import com.simon.magiccube.facade.support.CommonResult;

import java.util.List;

/**
 *
 * @author simon
 * @date 2020-06-05 18:18:59
 **/
public interface ICaseRunEngine {

    CaseRun selectCaseRunById(Integer id);

    /**
     * 查询最新的一条数据
     *
     */
    CaseRun selectCaseRunByNew(CaseRun caseRun);

    List selectCaseRunListByNew(CaseRun caseRun);


    CaseRun selectCaseRunByCaseId(String caseId);

    CaseRun selectCaseRunByCaseIdNew(String caseId);

    int insertCaseRun(CaseRun caseRun);

    CommonResult insertCaseRunRe(CaseRun caseRun) ;


    int updateCaseRunByIdSelective(CaseRun caseRun);

    Integer selectCaseRunListPageTotal(CaseRun caseRun);

    List<CaseRun> selectCaseRunListPage(CaseRun caseRun);

    List<CaseRun> selectCaseRunList(CaseRun caseRun);


}
