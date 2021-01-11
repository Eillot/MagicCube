package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.CaseSceneRunRelation;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 3:07 PM
 * @File : ICaseSceneRunRelationEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface ICaseSceneRunRelationEngine {

    /**
     * 通过执行对象id（用例id）查询执行id
     * @return
     */
    List<CaseSceneRunRelation> selectRunIdByCaseId(String caseId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param caseSceneRunRelation 实例对象
     * @return 对象列表
     */
    List<CaseSceneRunRelation> caseSceneRunRelationQueryAll(CaseSceneRunRelation caseSceneRunRelation);


}
