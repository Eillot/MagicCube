package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.SceneTestData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 场景用例测试数据表(SceneTestData)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface SceneTestDataMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SceneTestData SceneTestDataQueryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SceneTestData> SceneTestDataQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sceneTestData 实例对象
     * @return 对象列表
     */
    List<SceneTestData> SceneTestDataQueryAll(SceneTestData sceneTestData);

    /**
     * 新增数据
     *
     * @param sceneTestData 实例对象
     * @return 影响行数
     */
    int SceneTestDataInsert(SceneTestData sceneTestData);

    /**
     * 修改数据
     *
     * @param sceneTestData 实例对象
     * @return 影响行数
     */
    int SceneTestDataUpdate(SceneTestData sceneTestData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int SceneTestDataDeleteById(Integer id);

}