package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.ReportManage;
import com.simon.magiccube.dao.mapper.ReportManageMapper;
import com.simon.magiccube.engine.IReportManageEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/3/25 9:36 下午
 */
@Component
public class ReportManageEngineImp implements IReportManageEngine {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReportManageMapper reportManageMapper;


    @Override
    public List reportManageQueryById(Integer id) {
        List reportManageQueryById = reportManageMapper.ReportManageQueryById(id);
        return reportManageQueryById;
    }

    @Override
    public ReportManage ReportManageQueryByCaseIdNew(Integer id) {
        return reportManageMapper.ReportManageQueryByCaseIdNew(id);
    }

    @Override
    public List<ReportManage> reportManageQueryAll(ReportManage reportManage) {
        List<ReportManage>  reportManageList = reportManageMapper.ReportManageQueryAll(reportManage);
        return reportManageList;
    }

    @Override
    public List<ReportManage> ReportManageQueryAllNew(ReportManage reportManage) {
        return reportManageMapper.ReportManageQueryAllNew(reportManage);
    }

    @Override
    public int reportManageInsert(ReportManage reportManage) {
        int reportManageInsert = reportManageMapper.ReportManageInsert(reportManage);
        return reportManageInsert;
    }

    @Override
    public int ReportManageUpdateAssertByCaseId(ReportManage reportManage) {
        int reportManageUpdate = reportManageMapper.ReportManageUpdateAssertByCaseId(reportManage);
        return reportManageUpdate;
    }
}
