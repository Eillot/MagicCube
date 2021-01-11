package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.ParameterizationData;
import com.simon.magiccube.facade.dto.ParameterizationDataDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 3:08 PM
 * @File : IParameterizationDataEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface IParameterizationDataEngine {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ParameterizationData parameterizationDataQueryById(Integer id);


    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ParameterizationData> parameterizationDataQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<ParameterizationData> parameterizationDataQueryAll();


    /**
     * 新增数据
     *
     * @return 影响行数
     */
    int parameterizationDataInsert(ParameterizationDataDTO parameterizationDataDTO);

    /**
     * 修改数据
     *
     * @return 影响行数
     */
    int parameterizationDataUpdate(ParameterizationData parameterizationData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int parameterizationDataDeleteById(Integer id);

}
