package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.CaseSceneRunRelation;
import com.simon.magiccube.dao.mapper.CaseSceneRunRelationMapper;
import com.simon.magiccube.engine.ICaseSceneRunRelationEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 4:21 PM
 * @File : CaseSceneRunRelationEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */
@Component
public class CaseSceneRunRelationEngineImp implements ICaseSceneRunRelationEngine {
    @Autowired
    private CaseSceneRunRelationMapper caseSceneRunRelationMapper;

    @Override
    public List<CaseSceneRunRelation> selectRunIdByCaseId(String runObjectId) {
        List<CaseSceneRunRelation> runIdList =caseSceneRunRelationMapper.CaseSceneRunRelationQueryByCaseId(runObjectId);
        return runIdList;
    }

    @Override
    public List<CaseSceneRunRelation> caseSceneRunRelationQueryAll(CaseSceneRunRelation caseSceneRunRelation) {
        List<CaseSceneRunRelation> caseSceneRunRelationList = caseSceneRunRelationMapper.CaseSceneRunRelationQueryAll(caseSceneRunRelation);
        return caseSceneRunRelationList;
    }
}
