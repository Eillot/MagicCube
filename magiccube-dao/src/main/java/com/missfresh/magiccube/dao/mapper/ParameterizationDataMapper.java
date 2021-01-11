package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.ParameterizationData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 参数化数据表(ParameterizationData)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface ParameterizationDataMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ParameterizationData ParameterizationDataQueryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ParameterizationData> ParameterizationDataQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<ParameterizationData> ParameterizationDataQueryAll();


    /**
     * 新增数据
     *
     * @param parameterizationData 实例对象
     * @return 影响行数
     */
    int ParameterizationDataInsert(ParameterizationData parameterizationData);

    /**
     * 修改数据
     *
     * @param
     * @return 影响行数
     */
    int ParameterizationDataUpdate(ParameterizationData parameterizationData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int ParameterizationDataDeleteById(Integer id);

}