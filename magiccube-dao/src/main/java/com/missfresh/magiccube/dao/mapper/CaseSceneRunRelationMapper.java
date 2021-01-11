package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.CaseSceneRunRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用例场景执行关系表(CaseSceneRunRelation)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface CaseSceneRunRelationMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CaseSceneRunRelation CaseSceneRunRelationQueryById(Integer id);

    /**
     * 通过执行对象id（用例id）查询执行id
     * @param runObjectId
     * @return
     */
    List<CaseSceneRunRelation> CaseSceneRunRelationQueryByCaseId(String runObjectId);

    /**
     * 通过执行对象id（场景id）查询所有执行id
     * @param runObjectId
     * @return
     */
    List<CaseSceneRunRelation> CaseSceneRunRelationQueryBySceneId(String runObjectId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<CaseSceneRunRelation> CaseSceneRunRelationQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param caseSceneRunRelation 实例对象
     * @return 对象列表
     */
    List<CaseSceneRunRelation> CaseSceneRunRelationQueryAll(CaseSceneRunRelation caseSceneRunRelation);

    /**
     * 新增数据
     *
     * @param caseSceneRunRelation 实例对象
     * @return 影响行数
     */
    int CaseSceneRunRelationQInsert(CaseSceneRunRelation caseSceneRunRelation);

    /**
     * 修改数据
     *
     * @param caseSceneRunRelation 实例对象
     * @return 影响行数
     */
    int CaseSceneRunRelationUpdate(CaseSceneRunRelation caseSceneRunRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int CaseSceneRunRelationDeleteById(Integer id);

}