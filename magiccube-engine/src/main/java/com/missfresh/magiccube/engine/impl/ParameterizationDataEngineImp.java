package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.ParameterizationData;
import com.simon.magiccube.dao.mapper.ParameterizationDataMapper;
import com.simon.magiccube.engine.IParameterizationDataEngine;
import com.simon.magiccube.engine.util.DTOtoEntity;
import com.simon.magiccube.facade.dto.ParameterizationDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain : 【配置管理】-【参数化管理】
 * @contact:
 * @Time : 2019/12/28 4:21 PM
 * @File : ParameterizationDataEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */

@Component
public class ParameterizationDataEngineImp implements IParameterizationDataEngine {


    @Autowired
    private ParameterizationDataMapper parameterizationDataMapper;

    private ParameterizationData prd = new ParameterizationData();

    @Override
    public ParameterizationData parameterizationDataQueryById(Integer id) {
        prd = parameterizationDataMapper.ParameterizationDataQueryById(id);
        return prd;
    }

    @Override
    public List<ParameterizationData> parameterizationDataQueryAllByLimit(int offset, int limit) {
        return null;
    }


    @Override
    public List<ParameterizationData> parameterizationDataQueryAll() {
        List<ParameterizationData> caseListList = parameterizationDataMapper.ParameterizationDataQueryAll();
        return caseListList;
    }

    @Override
    public int parameterizationDataInsert(ParameterizationDataDTO parameterizationDataDTO) {
        DTOtoEntity.transalte(parameterizationDataDTO, prd);
        int reslutcode = parameterizationDataMapper.ParameterizationDataInsert(prd);
        return reslutcode;
    }

    @Override
    public int parameterizationDataUpdate(ParameterizationData id) {
        int reslutcode = parameterizationDataMapper.ParameterizationDataUpdate(id);
        return reslutcode;
    }

    @Override
    public int parameterizationDataDeleteById(Integer id) {
        int reslutcode = parameterizationDataMapper.ParameterizationDataDeleteById(id);
        return reslutcode;
    }
}
