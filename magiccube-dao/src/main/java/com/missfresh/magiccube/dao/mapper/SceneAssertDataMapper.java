package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.SceneAssertData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 场景用例验证点表(SceneAssertData)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface SceneAssertDataMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SceneAssertData SceneAssertDataQueryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SceneAssertData> SceneAssertDataQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sceneAssertData 实例对象
     * @return 对象列表
     */
    List<SceneAssertData> SceneAssertDataQueryAll(SceneAssertData sceneAssertData);

    /**
     * 新增数据
     *
     * @param sceneAssertData 实例对象
     * @return 影响行数
     */
    int SceneAssertDataInsert(SceneAssertData sceneAssertData);

    /**
     * 修改数据
     *
     * @param sceneAssertData 实例对象
     * @return 影响行数
     */
    int SceneAssertDataUpdate(SceneAssertData sceneAssertData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int SceneAssertDataDeleteById(Integer id);

}