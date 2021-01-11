package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.CaseManage;
import com.simon.magiccube.dao.domain.ReportManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 报告表(ReportManage)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface ReportManageMapper {


    /**
     * 查询单个，根据caseId
     * @param
     * @return
     */
    ReportManage ReportManageQueryByCaseId(Integer id);

    /**
     * 根据caseID查询最新的一条记录
     * @param id
     * @return
     */
    ReportManage ReportManageQueryByCaseIdNew(Integer id);

    /**
     * 查询单个
     * @param
     * @return
     */
    List ReportManageQueryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param reportManage 实例对象
     * @return 对象列表
     */
    List<ReportManage> ReportManageQueryAll(ReportManage reportManage);

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
    int ReportManageInsert(ReportManage reportManage);

    /**
     * 仅更新表中，assert数据
     *
     * @param reportManage 实例对象
     * @return 影响行数
     */
    int ReportManageUpdateAssertByCaseId(ReportManage reportManage);

}