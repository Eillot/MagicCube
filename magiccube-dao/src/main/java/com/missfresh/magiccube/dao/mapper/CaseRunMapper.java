package com.simon.magiccube.dao.mapper;

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
public interface CaseRunMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CaseRun selectCaseRunById(Integer id);

    /**
     * 查询最新的一条数据
     *
     */
    CaseRun selectCaseRunByNew(CaseRun caseRun);

    List selectCaseRunListByNew(CaseRun caseRun);


    /**
     * 通过caseId查询数据
     * @param caseId
     * @return
     */
    CaseRun selectCaseRunByCaseId(String caseId);

    /**
     * 通过caseId查询数据最新数据
     * @param caseId
     * @return
     */
    CaseRun selectCaseRunByCaseIdNew(String caseId);

    /**
     * 插入数据
     * @param caseRun
     * @return
     */
    int insertCaseRun(CaseRun caseRun);

    /**
     * 更新数据
     * @param caseRun
     * @return
     */
    int updateCaseRunByIdSelective(CaseRun caseRun);

    /**
     * 统计数量
     * @param caseRun
     * @return
     */
    Integer selectCaseRunListPageTotal(CaseRun caseRun);

    /**
     * 查询用例执行列表
     * @param caseRun
     * @return
     */
    List<CaseRun> selectCaseRunListPage(CaseRun caseRun);

    List<CaseRun> selectCaseRunList(CaseRun caseRun);



}