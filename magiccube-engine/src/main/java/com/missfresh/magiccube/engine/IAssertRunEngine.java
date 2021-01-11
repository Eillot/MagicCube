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
public interface IAssertRunEngine {

    AssertRun selectAssertRunById(Integer id);

    int insertAssertRun(AssertRun assertRun);

    CommonResult insertAssertRunRe(AssertRun assertRun) ;


    int updateAssertRunByIdSelective(AssertRun assertRun);

    Long selectAssertRunListPageTotal(AssertRun assertRun);

    List<AssertRun> selectAssertRunListPage(AssertRun assertRun);

    List<AssertRun> selectAssertRunList(AssertRun assertRun);


}
