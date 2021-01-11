package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.AssertRun;
import com.simon.magiccube.dao.domain.CaseRun;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用例表(CaseManage)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface AssertRunMapper {

    AssertRun selectAssertRunById(Integer id);

    int insertAssertRun(AssertRun assertRun);

    int updateAssertRunByIdSelective(AssertRun assertRun);

    Long selectAssertRunListPageTotal(AssertRun assertRun);

    List<AssertRun> selectAssertRunListPage(AssertRun assertRun);

    List<AssertRun> selectAssertRunList(AssertRun assertRun);

}