package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.CaseManage;
import com.simon.magiccube.dao.domain.ReportManage;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 2:48 PM
 * @File : IRepoertManageEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface IReportManageEngine {

    /**
     * 查询单个
     * @param
     * @return
     */
    List reportManageQueryById(Integer id);

    /**
     * 根据caseID查询最新的一条记录
     * @param id
     * @return
     */
    ReportManage ReportManageQueryByCaseIdNew(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param reportManage 实例对象
     * @return 对象列表
     */
    List<ReportManage> reportManageQueryAll(ReportManage reportManage);

    /**
     * 通过实体作为筛选条件查询--最新数据
     * @param reportManage
     * @return
     */
    List<ReportManage> ReportManageQueryAllNew(ReportManage reportManage);

    /**
     * 新增数据
     *
     * @param reportManage 实例对象
     * @return 影响行数
     */
    int reportManageInsert(ReportManage reportManage);

    /**
     * 仅更新assert数据
     *
     * @param reportManage 实例对象
     * @return 影响行数
     */
    int  ReportManageUpdateAssertByCaseId(ReportManage reportManage);
}
