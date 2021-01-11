package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.CaseParameterizationRelation;
import com.simon.magiccube.dao.mapper.CaseParameterizationRelationMapper;
import com.simon.magiccube.engine.ICaseParameterizationRelationEngine;
import com.simon.magiccube.engine.util.DTOtoEntity;
import com.simon.magiccube.engine.util.JsonUtil;
import com.simon.magiccube.facade.dto.CaseParameterizationRelationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain : 用例与参数关联接口
 * @contact:
 * @Time : 2019/12/28 4:20 PM
 * @File : CaseParameterizationRelationEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */

@Component
public class CaseParameterizationRelationEngineImp implements ICaseParameterizationRelationEngine {

    @Autowired
    private CaseParameterizationRelationMapper caseParameterizationRelationMapper;

    private CaseParameterizationRelation caseParameter = new CaseParameterizationRelation();

    @Override
    public  CaseParameterizationRelation selectCaseParameterizationRelationByID(Integer id) {
        caseParameter = caseParameterizationRelationMapper.CaseParameterizationRelationQueryById(id);
        return caseParameter;
    }

    @Override
    public List<CaseParameterizationRelation> selectAllCaseParameterizationRelation() {
        List<CaseParameterizationRelation> caseListList = caseParameterizationRelationMapper.CaseParameterizationRelationQueryAll();
        return caseListList;
    }

    @Override
    public int createCaseParameterizationRelation(CaseParameterizationRelationDTO caseParameterizationRelationDTO) {
        DTOtoEntity.transalte(caseParameterizationRelationDTO, caseParameter);
        int reslutcode = caseParameterizationRelationMapper.CaseParameterizationRelationInsert(caseParameter);
        return reslutcode;
    }

    @Override
    public int updateCaseParameterizationRelation(CaseParameterizationRelationDTO caseParameterizationRelationDTO) {
        DTOtoEntity.transalte(caseParameterizationRelationDTO, caseParameter);
        int reslutcode = caseParameterizationRelationMapper.CaseParameterizationRelationUpdate(caseParameter);
        return reslutcode;
    }

    @Override
    public int deleteCaseParameterizationRelation(Integer id) {
        int reslutcode = caseParameterizationRelationMapper.CaseParameterizationRelationDeleteById(id);
        return reslutcode;
    }
}