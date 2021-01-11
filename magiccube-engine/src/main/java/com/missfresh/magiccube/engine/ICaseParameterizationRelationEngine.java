package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.CaseParameterizationRelation;
import com.simon.magiccube.facade.dto.CaseParameterizationRelationDTO;
import javax.servlet.ServletException;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 2:50 PM
 * @File : IParameterParameterizationRelationEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface ICaseParameterizationRelationEngine {

    /**
     * 使用主键id查询唯一
     *
     * @param id
     * @return
     * @throws ServletException
     */
    CaseParameterizationRelation selectCaseParameterizationRelationByID(Integer id);

    /**
     * 查询并获取全部
     *
     * @param
     * @return
     * @throws ServletException
     */
    List<CaseParameterizationRelation> selectAllCaseParameterizationRelation();

    /**
     * 创建
     */
    int createCaseParameterizationRelation(CaseParameterizationRelationDTO caseParameterizationRelationDTO);


    /**
     * 更新数据
     *
     * @param caseParameterizationRelationDTO
     * @return
     * @throws ServletException
     */
    int updateCaseParameterizationRelation(CaseParameterizationRelationDTO caseParameterizationRelationDTO);

    /**
     * 使用主键id来删指定数据
     *
     * @param id
     * @return
     * @throws ServletException
     */
    int deleteCaseParameterizationRelation(Integer id);
}