package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.CaseSceneRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/6/16 11:23 上午
 */
@Component
public interface CaseSceneRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaseSceneRelation record);

    int insertSelective(CaseSceneRelation record);

    CaseSceneRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseSceneRelation record);

    int updateByPrimaryKey(CaseSceneRelation record);

    int deleteBySceneIdAndCaseId(@Param("sceneId") Long sceneId, @Param("caseId") Integer caseId);

    List<CaseSceneRelation> findAllBySceneId(@Param("sceneId") Long sceneId);

    /**
     * 根据场景ID查询用例ID
     *
     * @param sceneId
     * @return
     */
    List<String> listCaseIdBySceneId(@Param("sceneId") String sceneId);
}